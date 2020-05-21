package red.mohist.fabric.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;

public interface IBasicInventory {

    public DefaultedList<ItemStack> getContent();

}
