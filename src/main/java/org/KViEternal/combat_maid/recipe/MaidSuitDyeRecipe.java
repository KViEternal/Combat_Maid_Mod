package org.KViEternal.combat_maid.recipe;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;
import org.KViEternal.combat_maid.item.Combat_Maid_Suit_Item;

import net.minecraft.util.Identifier;

public class MaidSuitDyeRecipe extends SpecialCraftingRecipe {

    public MaidSuitDyeRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        boolean hasMaidSuit = false;
        boolean hasPinkDye = false;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    if (hasMaidSuit) return false;
                    hasMaidSuit = true;
                } else if (stack.isOf(Items.PINK_DYE)) {
                    if (hasPinkDye) return false;
                    hasPinkDye = true;
                } else {
                    return false;
                }
            }
        }
        return hasMaidSuit && hasPinkDye;
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        ItemStack maidSuit = ItemStack.EMPTY;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    maidSuit = stack;
                    break;
                }
            }
        }

        if (maidSuit.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack result = maidSuit.copy();
        result.setCount(1);

        result.getOrCreateNbt().putString("maid_suit_color", "pink");

        return result;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MAID_SUIT_DYE;
    }
}
