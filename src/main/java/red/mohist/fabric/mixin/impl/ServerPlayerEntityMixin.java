package red.mohist.fabric.mixin.impl;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import red.mohist.fabric.mixin.IServerPlayerEntityMixin;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements IServerPlayerEntityMixin {

    @Shadow
    public abstract void method_14241();

    @Override
    public void updateCursorStack() {
        method_14241();
    }
}
