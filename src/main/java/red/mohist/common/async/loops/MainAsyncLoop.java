package red.mohist.common.async.loops;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.FMLLog;
import red.mohist.common.async.thread.NamedThreadFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainAsyncLoop extends Loopbase {

    private static final int TICK_TIME = 1000000000 / 20;
    public static long curTime;
    public static long wait;
    private final List<Thread> threads = new ArrayList<>();
    public long lastTick = System.nanoTime();
    public long catchupTime = 0;
    public long tickSection = lastTick;

    public MainAsyncLoop() {

        server = MinecraftServer.getServerInst();
        FMLLog.info("initializing syncronised loops");


        threads.add(new NamedThreadFactory("ServerManagementAsyncLoop").newThread(new ServerManagementAsyncLoop()));
        threads.add(new NamedThreadFactory("WorldTickAsyncLoop").newThread(new WorldTickAsyncLoop()));


    }


    @Override
    public void run() {

        for (Thread thread : threads) {
            thread.start();
        }

        while (server.isServerRunning()) {

            curTime = System.nanoTime();
            wait = TICK_TIME - (curTime - lastTick) - catchupTime;
            if (wait > 0) {
                try {
                    Thread.sleep(wait / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catchupTime = 0;
                continue;
            } else {
                catchupTime = Math.min(1000000000, Math.abs(wait));
            }

            server.serverRunning = true;

            long startTime = System.nanoTime();

            server.executePendingCommands();

            if (startTime - server.nanoTimeSinceStatusRefresh >= 5000000000L) {
                server.nanoTimeSinceStatusRefresh = startTime;
                server.statusResponse.setPlayers(new ServerStatusResponse.Players(server.getMaxPlayers(), server.getCurrentPlayerCount()));
                GameProfile[] agameprofile = new GameProfile[Math.min(server.getCurrentPlayerCount(), org.spigotmc.SpigotConfig.playerSample)];
                int j = MathHelper.getInt(server.random, 0, server.getCurrentPlayerCount() - agameprofile.length);
                for (int k = 0; k < agameprofile.length; ++k) {
                    agameprofile[k] = server.getPlayerList().getPlayers().get(j + k).getGameProfile();
                }

                Collections.shuffle(Arrays.asList(agameprofile));
                server.statusResponse.getPlayers().setPlayers(agameprofile);
                server.statusResponse.invalidateJson();
            }


            server.profiler.startSection("tallying");
            server.tickTimeArray[server.tickCounter % 100] = System.nanoTime() - startTime;
            server.profiler.endSection();
            server.profiler.startSection("snooper");


            server.profiler.endSection();
            server.profiler.endSection();
            net.minecraftforge.fml.common.FMLCommonHandler.instance().onPostServerTick();
            org.spigotmc.WatchdogThread.tick(); // Spigot


            lastTick = curTime;

        }
    }
}
