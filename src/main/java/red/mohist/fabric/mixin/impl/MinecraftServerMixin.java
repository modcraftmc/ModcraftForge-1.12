package red.mohist.fabric.mixin.impl;

import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.scheduler.CraftScheduler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

	@Shadow
	private int ticks;
	
    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info) {
    	((CraftScheduler)Bukkit.getServer().getScheduler()).mainThreadHeartbeat(ticks + 1);
    }

    @Inject(at = @At("HEAD"), method = "getServerModName", cancellable=true)
    public String getServerModName(CallbackInfoReturnable<String> ret) {
    	ret.setReturnValue("FabricBukkit");
    	return "FabricBukkit";
    }
    
}
