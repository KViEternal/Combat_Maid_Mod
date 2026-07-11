package org.KViEternal.combat_maid.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.KViEternal.combat_maid.Combat_Maid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(ElytraFeatureRenderer.class)
public class ElytraFeatureRendererMixin {

    @Redirect(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z")
    )
    private boolean combat_maid$allowElytraMaidSuitRender(ItemStack instance, Item item) {
        // Vanilla checks if it's an Elytra to render the wings. We also allow the Elytra Maid Suit.
        return instance.isOf(Items.ELYTRA) || instance.isOf(Combat_Maid.Elytra_Maid_Suit_Item);
    }
}
