package red.mohist.common.async.thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadDispatcher {

    public static ThreadDispatcher instance;

    public static final Queue<Runnable> runnableList = new ConcurrentLinkedQueue();


    public static void addToPendingTask(Object arg, Runnable runnable) {


        try {
            runnableList.add(runnable);
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
