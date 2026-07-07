package ca.modmonster.beta_text_restorer.mixin;

import net.minecraft.SharedConstants;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiInGameMixin {
	@Final
	@Shadow
	private Minecraft minecraft;

	@Inject(method = "render", at = @At("TAIL"))
	public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
		if (!minecraft.getDebugOverlay().showDebugScreen()) {
			guiGraphics.drawString(minecraft.font, "Minecraft " + SharedConstants.getCurrentVersion().getName(), 2, 2, 16777215);
		}
	}
}
