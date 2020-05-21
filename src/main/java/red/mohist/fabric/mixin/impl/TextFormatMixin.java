package red.mohist.fabric.mixin.impl;

import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import red.mohist.fabric.mixin.ITextFormatMixin;

@Mixin(Formatting.class)
public class TextFormatMixin implements ITextFormatMixin {

    @Shadow
    @Final
    private char code;

    @Override
    public char getSectionSignCode() {
        return code;
    }
}
