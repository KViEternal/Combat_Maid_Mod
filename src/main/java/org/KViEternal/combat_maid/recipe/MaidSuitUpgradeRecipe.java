package org.KViEternal.combat_maid.recipe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;
import org.KViEternal.combat_maid.item.Combat_Maid_Suit_Item;

import net.minecraft.util.Identifier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.registry.tag.ItemTags;

public class MaidSuitUpgradeRecipe extends SpecialCraftingRecipe {

    public MaidSuitUpgradeRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(net.minecraft.recipe.input.CraftingRecipeInput inventory, World world) {
        boolean hasMaidSuit = false;
        boolean hasChestplate = false;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    if (hasMaidSuit) return false;
                    hasMaidSuit = true;
                } else if (stack.isIn(ItemTags.CHEST_ARMOR)) {
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
    public ItemStack craft(net.minecraft.recipe.input.CraftingRecipeInput inventory, net.minecraft.registry.RegistryWrapper.WrapperLookup registryManager) {
        ItemStack maidSuit = ItemStack.EMPTY;
        ItemStack chestplate = ItemStack.EMPTY;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    maidSuit = stack;
                } else if (stack.isIn(ItemTags.CHEST_ARMOR)) {
                    chestplate = stack;
                }
            }
        }

        if (maidSuit.isEmpty() || chestplate.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack result = maidSuit.copy();
        result.setCount(1);

        AttributeModifiersComponent chestModifiers = chestplate.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT);
        
        double baseArmor = 0;
        double baseToughness = 0;
        double baseKnockback = 0;

        for (AttributeModifiersComponent.Entry entry : chestModifiers.modifiers()) {
            if (entry.attribute().equals(EntityAttributes.ARMOR)) {
                baseArmor += entry.modifier().value();
            } else if (entry.attribute().equals(EntityAttributes.ARMOR_TOUGHNESS)) {
                baseToughness += entry.modifier().value();
            } else if (entry.attribute().equals(EntityAttributes.KNOCKBACK_RESISTANCE)) {
                baseKnockback += entry.modifier().value();
            }
        }

        int totalArmor = (int) Math.round(baseArmor * 2.5);
        float totalToughness = (float) (baseToughness * 4.0);
        float totalKnockback = (float) (baseKnockback * 4.0);

        Identifier modifierId = Identifier.of("combat_maid", "maid_suit_upgrade");
        AttributeModifiersComponent.Builder modifiers = AttributeModifiersComponent.builder();
        
        modifiers.add(EntityAttributes.ARMOR, new EntityAttributeModifier(modifierId, totalArmor, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.CHEST);
        
        if (totalToughness > 0) {
            modifiers.add(EntityAttributes.ARMOR_TOUGHNESS, new EntityAttributeModifier(modifierId, totalToughness, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.CHEST);
        }
        
        if (totalKnockback > 0) {
            modifiers.add(EntityAttributes.KNOCKBACK_RESISTANCE, new EntityAttributeModifier(modifierId, totalKnockback, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.CHEST);
        }
        
        result.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, modifiers.build());

        String path = Registries.ITEM.getId(chestplate.getItem()).getPath();
        String materialName = path.replace("_chestplate", "");
        
        NbtComponent customData = result.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = customData.copyNbt();
        nbt.putString("combat_maid_base_material", materialName);
        result.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));

        ItemEnchantmentsComponent maidEnchants = maidSuit.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        ItemEnchantmentsComponent chestEnchants = chestplate.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        
        if (maidEnchants.isEmpty() && !chestEnchants.isEmpty()) {
            result.set(DataComponentTypes.ENCHANTMENTS, chestEnchants);
            if (chestplate.contains(DataComponentTypes.REPAIR_COST)) {
                result.set(DataComponentTypes.REPAIR_COST, chestplate.get(DataComponentTypes.REPAIR_COST));
            }
        }

        return result;
    }

    

    @Override
    public RecipeSerializer<MaidSuitUpgradeRecipe> getSerializer() {
        return ModRecipes.MAID_SUIT_UPGRADE;
    }
}

