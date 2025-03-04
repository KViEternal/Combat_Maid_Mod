package org.KViEternal.combat_maid.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.KViEternal.combat_maid.Combat_Maid.Improved_Maid_Suit_Item;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow public abstract PlayerAbilities getAbilities();

    @Shadow public abstract boolean isCreative();

    @Shadow public abstract boolean isSpectator();

    @Shadow public abstract void sendAbilitiesUpdate();

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci) {
        ItemStack itemStack = this.getEquippedStack(EquipmentSlot.CHEST);

        if (!this.isCreative() && !this.isSpectator()) {
            boolean isWearingImprovedMaidSuit = itemStack.getItem() == Improved_Maid_Suit_Item;

            if (isWearingImprovedMaidSuit) {
                if (!this.getAbilities().allowFlying) {
                    this.getAbilities().allowFlying = true;
                    this.sendAbilitiesUpdate();
                }
            } else {
                if (this.getAbilities().allowFlying) {
                    this.getAbilities().allowFlying = false;
                    this.sendAbilitiesUpdate();
                }
            }
        }
    }
}
