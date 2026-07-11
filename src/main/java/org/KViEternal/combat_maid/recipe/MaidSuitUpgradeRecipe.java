package org.KViEternal.combat_maid.recipe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;
import org.KViEternal.combat_maid.item.Combat_Maid_Suit_Item;

import net.minecraft.util.Identifier;
import java.util.Map;
import java.util.UUID;

public class MaidSuitUpgradeRecipe extends SpecialCraftingRecipe {

    public MaidSuitUpgradeRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        boolean hasMaidSuit = false;
        boolean hasChestplate = false;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    if (hasMaidSuit) return false;
                    hasMaidSuit = true;
                } else if (stack.getItem() instanceof ArmorItem armorItem && armorItem.getType() == ArmorItem.Type.CHESTPLATE) {
                    if (hasChestplate) return false;
                    hasChestplate = true;
                } else if (stack.isOf(Items.ELYTRA)) {
                    return false;
                } else if (stack.isOf(Items.PINK_DYE)) {
                    return false;
                } else {
                    return false;
                }
            }
        }
        return hasMaidSuit && hasChestplate;
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        ItemStack maidSuit = ItemStack.EMPTY;
        ItemStack chestplate = ItemStack.EMPTY;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    maidSuit = stack;
                } else if (stack.getItem() instanceof ArmorItem armorItem && armorItem.getType() == ArmorItem.Type.CHESTPLATE) {
                    chestplate = stack;
                }
            }
        }

        if (maidSuit.isEmpty() || chestplate.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack result = maidSuit.copy();
        result.setCount(1);

        ArmorItem armorItem = (ArmorItem) chestplate.getItem();
        ArmorMaterial material = armorItem.getMaterial();

        int totalArmor = material.getProtection(ArmorItem.Type.HELMET)
                + material.getProtection(ArmorItem.Type.CHESTPLATE)
                + material.getProtection(ArmorItem.Type.LEGGINGS)
                + material.getProtection(ArmorItem.Type.BOOTS);

        float totalToughness = material.getToughness() * 4.0f;
        float totalKnockback = material.getKnockbackResistance() * 4.0f;

        // Clear existing modifiers if any, then add new ones
        result.getOrCreateNbt().remove("AttributeModifiers");

        UUID modifierId = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B");
        
        result.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(modifierId, "Maid Suit Armor", totalArmor, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.CHEST);
        
        if (totalToughness > 0) {
            result.addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(modifierId, "Maid Suit Toughness", totalToughness, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.CHEST);
        }
        
        if (totalKnockback > 0) {
            result.addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(modifierId, "Maid Suit Knockback", totalKnockback, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.CHEST);
        }

        String path = Registries.ITEM.getId(chestplate.getItem()).getPath();
        String materialName = path.replace("_chestplate", "");
        result.getOrCreateNbt().putString("maid_suit_material", materialName);

        Map<Enchantment, Integer> maidEnchants = EnchantmentHelper.get(maidSuit);
        Map<Enchantment, Integer> chestEnchants = EnchantmentHelper.get(chestplate);
        
        if (maidEnchants.isEmpty() && !chestEnchants.isEmpty()) {
            EnchantmentHelper.set(chestEnchants, result);
            if (chestplate.hasNbt() && chestplate.getNbt().contains("RepairCost")) {
                result.getOrCreateNbt().putInt("RepairCost", chestplate.getNbt().getInt("RepairCost"));
            }
        }

        return result;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MAID_SUIT_UPGRADE;
    }
}
