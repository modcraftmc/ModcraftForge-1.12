package red.mohist.fabric.mixin.impl;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import red.mohist.fabric.mixin.IPlayerEntityMixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements IPlayerEntityMixin {


}
