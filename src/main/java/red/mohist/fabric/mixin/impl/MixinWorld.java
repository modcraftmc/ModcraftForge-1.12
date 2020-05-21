package red.mohist.fabric.mixin.impl;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import red.mohist.fabric.mixin.IWorldMixin;

@Mixin(World.class)
public abstract class MixinWorld implements IWorldMixin {

	@Override
	public void updateWeather() {
		initWeatherGradients();
	}
    
	@Shadow
	abstract void initWeatherGradients();

}