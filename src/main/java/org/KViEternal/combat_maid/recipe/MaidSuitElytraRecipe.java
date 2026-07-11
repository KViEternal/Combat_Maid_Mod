package org.KViEternal.combat_maid.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.world.World;
import org.KViEternal.combat_maid.item.Combat_Maid_Suit_Item;

public class MaidSuitElytraRecipe extends SpecialCraftingRecipe {

    public MaidSuitElytraRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(net.minecraft.recipe.input.CraftingRecipeInput inventory, World world) {
        boolean hasMaidSuit = false;
        boolean hasElytra = false;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    if (hasMaidSuit) return false;
                    hasMaidSuit = true;
                } else if (stack.isOf(Items.ELYTRA)) {
                    if (hasElytra) return false;
                    hasElytra = true;
                } else if (stack.isOf(Items.PINK_DYE) || stack.isOf(Items.WHITE_DYE)) {
                    return false;
                } else if (stack.contains(net.minecraft.component.DataComponentTypes.EQUIPPABLE)) {
                    return false;
                } else {
                    return false;
                }
            }
        }
        return hasMaidSuit && hasElytra;
    }

    @Override
    public ItemStack craft(net.minecraft.recipe.input.CraftingRecipeInput inventory, net.minecraft.registry.RegistryWrapper.WrapperLookup registryManager) {
        ItemStack maidSuit = ItemStack.EMPTY;
        ItemStack elytra = ItemStack.EMPTY;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    maidSuit = stack;
                } else if (stack.isOf(Items.ELYTRA)) {
                    elytra = stack;
                }
            }
        }

        if (maidSuit.isEmpty() || elytra.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack result = new ItemStack(org.KViEternal.combat_maid.Combat_Maid.Elytra_Maid_Suit_Item);
        
        result.applyComponentsFrom(maidSuit.getComponents());
        
        net.minecraft.component.type.ItemEnchantmentsComponent maidEnchants = maidSuit.getOrDefault(net.minecraft.component.DataComponentTypes.ENCHANTMENTS, net.minecraft.component.type.ItemEnchantmentsComponent.DEFAULT);
        net.minecraft.component.type.ItemEnchantmentsComponent elytraEnchants = elytra.getOrDefault(net.minecraft.component.DataComponentTypes.ENCHANTMENTS, net.minecraft.component.type.ItemEnchantmentsComponent.DEFAULT);

        if (maidEnchants.isEmpty() && !elytraEnchants.isEmpty()) {
            result.set(net.minecraft.component.DataComponentTypes.ENCHANTMENTS, elytraEnchants);
            if (elytra.contains(net.minecraft.component.DataComponentTypes.REPAIR_COST)) {
                result.set(net.minecraft.component.DataComponentTypes.REPAIR_COST, elytra.get(net.minecraft.component.DataComponentTypes.REPAIR_COST));
            }
        }

        return result;
    }

    

    @Override
    public RecipeSerializer<MaidSuitElytraRecipe> getSerializer() {
        return ModRecipes.MAID_SUIT_ELYTRA;
    }
}

