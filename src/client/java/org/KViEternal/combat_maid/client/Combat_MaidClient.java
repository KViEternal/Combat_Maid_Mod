package org.KViEternal.combat_maid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.KViEternal.combat_maid.Combat_Maid;
import org.KViEternal.combat_maid.client.entity.models.Combat_Maid_Suit_Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Combat_MaidClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("combat_maid");
    private static Combat_Maid_Suit_Model maid_suit_model;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Combat Maids mod successfully initialized! (Client-Side)");

        maid_suit_model = new Combat_Maid_Suit_Model(Combat_Maid_Suit_Model.getTexturedModelData().createModel());

        net.minecraft.client.item.ModelPredicateProviderRegistry.register(Combat_Maid.Maid_Suit_Item, new Identifier("combat_maid", "is_pink"), (stack, world, entity, seed) -> {
            return (stack.hasNbt() && stack.getNbt().contains("maid_suit_color") && stack.getNbt().getString("maid_suit_color").equals("pink")) ? 1.0f : 0.0f;
        });
        net.minecraft.client.item.ModelPredicateProviderRegistry.register(Combat_Maid.Elytra_Maid_Suit_Item, new Identifier("combat_maid", "is_pink"), (stack, world, entity, seed) -> {
            return (stack.hasNbt() && stack.getNbt().contains("maid_suit_color") && stack.getNbt().getString("maid_suit_color").equals("pink")) ? 1.0f : 0.0f;
        });

        ArmorRenderer renderer = (matrices, vertexConsumers, stack, entity, slot, light, contextModel) -> {
            boolean isPink = stack.hasNbt() && stack.getNbt().contains("maid_suit_color") && stack.getNbt().getString("maid_suit_color").equals("pink");
            Identifier texture = new Identifier("combat_maid", isPink
                    ? "textures/entity/combat_maid_suit_pink.png"
                    : "textures/entity/combat_maid_suit_white.png");

            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(
                    vertexConsumers, maid_suit_model.getLayer(texture), false, stack.hasGlint());

            BipedEntityModel<?> bipedModel = (BipedEntityModel<?>) contextModel;

            // ========================================
            // Get per-entity animation state
            // ========================================
            MaidSuitAnimator.AnimationState animState = MaidSuitAnimator.getOrCreate(entity);

            // Tick the physics simulation once per game tick (not per frame)
            long currentAge = entity.age;
            if (currentAge != animState.lastTickAge) {
                animState.lastTickAge = currentAge;
                float legSwingAmount = Math.abs(bipedModel.rightLeg.pitch) + Math.abs(bipedModel.leftLeg.pitch);
                animState.tick(entity, bipedModel.sneaking, legSwingAmount);
            }

            // ========================================
            // Copy base transforms from the player model
            // ========================================
            maid_suit_model.Body.copyTransform(bipedModel.body);
            maid_suit_model.LeftArm.copyTransform(bipedModel.leftArm);
            maid_suit_model.RightArm.copyTransform(bipedModel.rightArm);

            // ========================================
            // Chest physics animation
            // ========================================
            float baseChestPivotY = 1.5f;
            float chestOffsetY = animState.getChestOffsetY();
            float chestPitchOffset = animState.getChestPitchOffset();

            maid_suit_model.Chest.pivotY = baseChestPivotY + chestOffsetY;
            maid_suit_model.Chest.pitch = chestPitchOffset;

            // ========================================
            // Skirt animation: front/back follow legs, sides stretch to fill gaps
            // ========================================
            float rightLegPitch = bipedModel.rightLeg.pitch;
            float leftLegPitch = bipedModel.leftLeg.pitch;

            // Front follows whichever leg pushes forward most (negative pitch = forward)
            float frontKick = Math.min(rightLegPitch, leftLegPitch);
            // Back follows whichever leg pushes backward most (positive pitch = backward)
            float backKick = Math.max(rightLegPitch, leftLegPitch);

            // Only apply when the leg is actually going in that direction
            float frontPitch = Math.min(0.0f, frontKick) * 0.7f;
            float backPitch = Math.max(0.0f, backKick) * 0.7f;

            // Total angular spread between front and back panels
            float totalSpread = Math.abs(backPitch - frontPitch);

            // --- Front ---
            // New pivot: (0, 8, -1.75) relative to Skirt — hinges at the waist line.
            // Pitching from the waist means no vertical gap forms at the top.
            float baseFrontPivotY = 8.0f;
            float baseFrontPivotZ = -1.75f;
            maid_suit_model.Front.pivotY = baseFrontPivotY;
            maid_suit_model.Front.pivotZ = baseFrontPivotZ;
            maid_suit_model.Front.pitch = frontPitch;

            // --- Back ---
            // New pivot: (-5, 8, 1.75) relative to Skirt — also hinges at the waist line.
            // Shift backward when pitching to clear the body.
            float baseBackPivotY = 8.0f;
            float baseBackPivotZ = 1.75f;
            maid_suit_model.Back.pivotY = baseBackPivotY;
            maid_suit_model.Back.pivotZ = baseBackPivotZ + backPitch * 0.8f;
            maid_suit_model.Back.pitch = backPitch;

            // --- Sides ---
            // New pivot: (0, 8, 0) relative to Skirt — centered at the waist.
            // Stretch on Z to bridge the gap between front and back as they spread apart.
            maid_suit_model.Sides.zScale = 1.0f + totalSpread * 1.75f;
            maid_suit_model.Sides.xScale = 1.0f - totalSpread * 0.05f;
            maid_suit_model.Sides.yScale = 1.0f - totalSpread * 0.125f;
            maid_suit_model.Sides.roll = 0.0f;

            // ========================================
            // Apply sneaking adjustments
            // ========================================
            if (bipedModel.sneaking) {
                // Body tilts forward when sneaking. Move skirt down with the body:
                // Vanilla body drops to 3.2f, and legs shift back.
                maid_suit_model.Skirt.pivotY = 3.2f;
                maid_suit_model.Skirt.pivotZ = 3.0f;
                maid_suit_model.Skirt.pitch = 0.20f;

                // Back needs extra clearance + extra backward shift
                maid_suit_model.Back.pitch += 0.35f;
                maid_suit_model.Back.pivotZ += 0.35f * 0.8f;

                // Front tucks in slightly
                maid_suit_model.Front.pitch -= 0.1f;

                // Extra Z-stretch for sides during sneaking
                maid_suit_model.Sides.zScale += 0.35f;
            } else {
                maid_suit_model.Skirt.pivotY = 1.0f;
                maid_suit_model.Skirt.pivotZ = 0.0f;
                maid_suit_model.Skirt.pitch = 0.0f;
            }

            // ========================================
            // Render everything in a single matrix scope
            // ========================================
            matrices.push();

            // Render body (includes chest as child)
            maid_suit_model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

            if (slot == net.minecraft.entity.EquipmentSlot.CHEST) {
                maid_suit_model.renderArm(matrices, true, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
                maid_suit_model.renderArm(matrices, false, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
                maid_suit_model.renderSkirt(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            }
            matrices.pop();
        };

        ArmorRenderer.register(renderer, Combat_Maid.Maid_Suit_Item, Combat_Maid.Elytra_Maid_Suit_Item);
    }
}
