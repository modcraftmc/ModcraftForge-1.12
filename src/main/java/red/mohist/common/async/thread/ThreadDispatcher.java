package red.mohist.common.async.thread;

import com.google.common.util.concurrent.Futures;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;

import java.util.concurrent.Executors;

public class ThreadDispatcher {

    public static ThreadDispatcher instance;


    public static void addToPendingTask(Object arg, Runnable runnable) {

        if (arg instanceof WorldServer) {
            try {
                Futures.immediateFuture(Executors.callable(runnable));
            } catch (Exception exception) {
                Futures.immediateFailedCheckedFuture(exception);
            }

        } else if (arg instanceof ITickable) {
            MohistThreadBox.NMS_ENTITY_MOVE.submit(runnable);
        }
    }

    public static void addToPendingTask(Runnable runnable) {

        MohistThreadBox.NMS_ENTITY_MOVE.submit(runnable);

    }


    public static ThreadDispatcher getInstance() {
        return instance == null ? new ThreadDispatcher() : instance;
    }
}
