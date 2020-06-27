package red.mohist.common.async.loops;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.chunkio.ChunkIOExecutor;

public class WorldTickAsyncLoop extends Loopbase {

    private static final int TICK_TIME = 1000000000 / 20;
    public static long curTime;
    public static long wait;
    private final MinecraftServer server = MinecraftServer.getServerInst();
    public long lastTick = System.nanoTime();
    public long catchupTime = 0;
    public long tickSection = lastTick;

    @Override
    public void run() {

        while (server.isServerRunning()) {

            ChunkIOExecutor.tick();

            for (WorldServer worldServer : server.worldServerList) {

                worldServer.tickWithEvents();

            }

        }

    }
}
