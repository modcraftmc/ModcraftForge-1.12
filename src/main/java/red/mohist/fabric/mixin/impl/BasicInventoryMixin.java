package red.mohist.fabric.mixin.impl;

import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import red.mohist.fabric.mixin.IBasicInventory;

@Mixin(BasicInventory.class)
public class BasicInventoryMixin implements IBasicInventory {

    @Shadow
    private DefaultedList<ItemStack> stackList;

    @Override
    public DefaultedList<ItemStack> getContent() {
        return stackList;
    }

}
