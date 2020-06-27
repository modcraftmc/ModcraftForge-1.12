package red.mohist.common.async.loops;

import net.minecraft.server.MinecraftServer;

public class Loopbase implements Runnable {

    protected MinecraftServer server = MinecraftServer.getServerInst();

    @Override
    public void run() {

    }
}
