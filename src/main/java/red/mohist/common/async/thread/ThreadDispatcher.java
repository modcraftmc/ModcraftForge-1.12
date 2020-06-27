package red.mohist.common.async.thread;

public class ThreadDispatcher {

    public static ThreadDispatcher instance;

    private static Thread worldThread;


    public static void addToPendingTask(Object arg, Runnable runnable) {


        try {
            worldThread = new Thread(runnable);
            worldThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addToPendingTask(Runnable runnable) {

        MohistThreadBox.NMS_ENTITY_MOVE.submit(runnable);

    }


    public static ThreadDispatcher getInstance() {
        return instance == null ? new ThreadDispatcher() : instance;
    }
}
