package red.mohist.common.async.loops;

public class ServerManagementAsyncLoop extends Loopbase {

    private static final int TICK_TIME = 1000000000 / 20;
    public static long curTime;
    public static long wait;
    public long lastTick = System.nanoTime();
    public long catchupTime = 0;
    public long tickSection = lastTick;

    @Override
    public void run() {

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


            server.getNetworkSystem().networkTick();
            server.getPlayerList().onTick();
            server.getFunctionManager().update();

            lastTick = curTime;

        }

    }
}
