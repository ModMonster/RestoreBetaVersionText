package ca.modmonster.beta_text_restorer.mixin;

import net.minecraft.SharedConstants;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hud.class)
public class GuiInGameMixin {
	@Final
	@Shadow
	private Minecraft minecraft;

	@Inject(method = "extractRenderState", at = @At("TAIL"))
	public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
		if (!minecraft.getDebugOverlay().showDebugScreen()) {
			graphics.text(minecraft.font, "Minecraft " + SharedConstants.getCurrentVersion().name(), 2, 2, 0xFFFFFFFF);
		}
	}
}
