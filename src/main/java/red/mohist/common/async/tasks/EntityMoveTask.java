package red.mohist.common.async.tasks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import red.mohist.common.async.AsyncProperties;

import java.awt.*;

public class EntityMoveTask implements Runnable {

    public final AsyncProperties asyncProperties;
    public final Entity entity;
    public final MoverType moverType;
    public final double x;
    public final double y;
    public final double z;
    public final long time;

    public EntityMoveTask(AsyncProperties asyncProperties, Entity entity, MoverType moverType, double x, double y, double z, long time) {
        this.asyncProperties = asyncProperties;
        this.entity = entity;
        this.moverType = moverType;
        this.x = x;
        this.y = y;
        this.z = z;
        this.time = time;
    }


    @Override
    public void run() {

        System.out.println("Asyncproperties : " + asyncProperties.toString());
        System.out.println("Entity : " + entity.toString());

        try {
            if (System.currentTimeMillis() - this.time > 100)
                return;
            if (this.entity.world.unloadedEntitySet.contains(this.entity))
                return;

            this.entity.moveEntity(this.moverType, this.x, this.y, this.z, true, asyncProperties.getAsyncType());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
