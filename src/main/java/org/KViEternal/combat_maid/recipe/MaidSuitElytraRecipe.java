package org.KViEternal.combat_maid.recipe;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;
import org.KViEternal.combat_maid.Combat_Maid;
import org.KViEternal.combat_maid.item.Combat_Maid_Suit_Item;
import org.KViEternal.combat_maid.item.Elytra_Combat_Maid_Suit_Item;

import net.minecraft.util.Identifier;

public class MaidSuitElytraRecipe extends SpecialCraftingRecipe {

    public MaidSuitElytraRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        boolean hasMaidSuit = false;
        boolean hasElytra = false;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item && !(stack.getItem() instanceof Elytra_Combat_Maid_Suit_Item)) {
                    if (hasMaidSuit) return false;
                    hasMaidSuit = true;
                } else if (stack.isOf(Items.ELYTRA)) {
                    if (hasElytra) return false;
                    hasElytra = true;
                } else {
                    return false;
                }
            }
        }
        return hasMaidSuit && hasElytra;
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

        // Convert to Elytra variant
        ItemStack result = new ItemStack(Combat_Maid.Elytra_Maid_Suit_Item);
        
        if (maidSuit.hasNbt()) {
            result.setNbt(maidSuit.getNbt().copy());
        }

        return result;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MAID_SUIT_ELYTRA;
    }
}
