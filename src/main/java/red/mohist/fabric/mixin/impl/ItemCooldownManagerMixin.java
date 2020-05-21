package red.mohist.fabric.mixin.impl;

import net.minecraft.entity.player.ItemCooldownManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import red.mohist.fabric.mixin.IItemCooldownManagerMixin;

@Mixin(ItemCooldownManager.class)
public class ItemCooldownManagerMixin implements IItemCooldownManagerMixin {

    @Shadow
    private int tick;

    @Override
    public int getTick() {
        return tick;
    }

}
