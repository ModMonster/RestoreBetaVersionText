package ca.modmonster.beta_text_restorer.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameGui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameGui.class)
public class GuiInGameMixin {
	@Shadow
	private Minecraft minecraft;

	@Unique
	private final static String version;

	static {
		String raw = FabricLoader.getInstance().getRawGameVersion();
		if (raw.startsWith("b")) {
			version = "Beta " + raw.substring(1);
		} else {
			version = raw;
		}
	}

	@Inject(method = "render", at = @At("TAIL"))
	public void render(float tickDelta, boolean screenOpen, int mouseX, int mouseY, CallbackInfo ci) {
		if (!minecraft.options.debugEnabled) {
			minecraft.textRenderer.drawWithShadow("Minecraft " + version, 2, 2, 16777215);
		}
	}
}
