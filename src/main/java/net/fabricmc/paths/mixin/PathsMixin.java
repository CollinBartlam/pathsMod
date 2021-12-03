package net.fabricmc.paths.mixin;

import net.fabricmc.paths.PathsMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.registry.Registry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShovelItem.class)
public class PathsMixin {
	@Inject(at = @At("HEAD"), method = "useOnBlock()V")
	private void useOnBlock(net.minecraft.item.ItemUsageContext context, CallbackInfoReturnable ci) {
		PlayerEntity player = context.getPlayer();
		Hand activeHand = player.getActiveHand();
		ItemStack activeStack = player.getStackInHand(activeHand);
		if (player.getWorld().getBlockState(context.getBlockPos()).getBlock().toString().equals("Block{minecraft:sand}")) {
			player.playSound(SoundEvents.ITEM_SHOVEL_FLATTEN, 1.0F, 1.0F);
			player.getWorld().setBlockState(context.getBlockPos(), Registry.BLOCK.get(PathsMod.SAND_PATH_IDENTIFIER).getDefaultState());
			player.swingHand(activeHand);
			activeStack.damage(1, player, playerEnt -> {
				playerEnt.sendToolBreakStatus(activeHand);
			});
		}
	}
}
