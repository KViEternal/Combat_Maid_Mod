package org.KViEternal.combat_maid.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.KViEternal.combat_maid.client.entity.models.Combat_Maid_Suit_Model;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.KViEternal.combat_maid.Combat_Maid.Improved_Maid_Suit_Item;
import static org.KViEternal.combat_maid.Combat_Maid.Maid_Suit_Item;

@Environment(EnvType.CLIENT)
@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin {

    @Unique
    private final Combat_Maid_Suit_Model maid_suit_model = new Combat_Maid_Suit_Model(Combat_Maid_Suit_Model.getTexturedModelData().createModel());

    @Inject(method = "render", at = @At("HEAD"))
    private void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, LivingEntity livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo info) {
        ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
        if (!(itemStack.isEmpty()) && (itemStack.getItem() == Maid_Suit_Item || itemStack.getItem() == Improved_Maid_Suit_Item)) {
            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider, this.maid_suit_model.getLayer(Identifier.of("combat_maid", "textures/entity/combat_maid_suit.png")), false);

            maid_suit_model.LeftArm.setTransform(((BipedEntityModel<?>) ((ArmorFeatureRenderer) (Object) this).getContextModel()).leftArm.getTransform());
            maid_suit_model.RightArm.setTransform(((BipedEntityModel<?>) ((ArmorFeatureRenderer) (Object) this).getContextModel()).rightArm.getTransform());

            matrixStack.push();
            matrixStack.translate(0.0D, 0.0D, 0.0D);
            matrixStack.scale(1.0F, 1.0F, 1.0F);
            ((BipedEntityModel<?>) ((ArmorFeatureRenderer) (Object) this).getContextModel()).body.rotate(matrixStack);
            this.maid_suit_model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, Colors.WHITE);
            matrixStack.pop();

            matrixStack.push();
            matrixStack.translate(0.0D, 0.0D, 0.0D);
            matrixStack.scale(1.0F, 1.0F, 1.0F);

            if(((BipedEntityModel<?>) ((ArmorFeatureRenderer) (Object) this).getContextModel()).sneaking){
                maid_suit_model.Skirt.pivotZ = -1f;
                matrixStack.translate(0.0D, 0.2D, 0.21D);
                maid_suit_model.Skirt.pitch = 0.2f;
            }

            this.maid_suit_model.renderSkirt(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, Colors.WHITE);

            if(((BipedEntityModel<?>) ((ArmorFeatureRenderer) (Object) this).getContextModel()).sneaking){
                maid_suit_model.Skirt.pivotZ = 0f;
                maid_suit_model.Skirt.pitch = 0f;
            }
            matrixStack.pop();

            matrixStack.push();
            matrixStack.translate(0.0D, 0.0D, 0.0D);
            matrixStack.scale(1.0F, 1.0F, 1.0F);
            this.maid_suit_model.renderArm(matrixStack, true, vertexConsumer, i, OverlayTexture.DEFAULT_UV, Colors.WHITE);
            this.maid_suit_model.renderArm(matrixStack, false, vertexConsumer, i, OverlayTexture.DEFAULT_UV, Colors.WHITE);
            matrixStack.pop();
        }
    }
}
