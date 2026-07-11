package org.KViEternal.combat_maid.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.KViEternal.combat_maid.Combat_Maid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hides the player skin's second layer (overlay) when wearing the maid suit,
 * so clothing-style skins don't clip through the custom armor model.
 */
@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityModel.class)
public class PlayerEntityModelMixin {

    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
    private void combat_maid$hideOverlayLayers(LivingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        var chestStack = entity.getEquippedStack(EquipmentSlot.CHEST);
        if (!chestStack.isEmpty() && (chestStack.getItem() == Combat_Maid.Maid_Suit_Item || chestStack.getItem() == Combat_Maid.Elytra_Maid_Suit_Item)) {
            PlayerEntityModel<?> self = (PlayerEntityModel<?>) (Object) this;
            // Hide body overlay (jacket) — clips through the maid suit torso
            self.jacket.visible = false;
            // Hide arm overlays (sleeves) — clips through the maid suit arm covers
            self.leftSleeve.visible = false;
            self.rightSleeve.visible = false;
        }
    }
}
