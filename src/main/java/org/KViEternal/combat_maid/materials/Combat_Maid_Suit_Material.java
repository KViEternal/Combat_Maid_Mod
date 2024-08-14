package org.KViEternal.combat_maid.materials;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class Combat_Maid_Suit_Material implements ArmorMaterial {
    @Override
    public int getDurability(ArmorItem.Type type) {
        return -1;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return 25;
    }

    @Override
    public int getEnchantability() {
        return 20;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Override
    public String getName() {
        return "combat_maid_suit";
    }

    @Override
    public float getToughness() {
        return 20;
    }

    @Override
    public float getKnockbackResistance() {
        return 1.5f;
    }
}
