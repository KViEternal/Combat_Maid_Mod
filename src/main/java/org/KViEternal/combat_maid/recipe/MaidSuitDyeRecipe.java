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

    public MaidSuitDyeRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(net.minecraft.recipe.input.CraftingRecipeInput inventory, World world) {
        boolean hasMaidSuit = false;
        boolean hasPinkDye = false;
        boolean hasWhiteDye = false;

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    if (hasMaidSuit) return false;
                    hasMaidSuit = true;
                } else if (stack.isOf(Items.PINK_DYE)) {
                    if (hasPinkDye || hasWhiteDye) return false;
                    hasPinkDye = true;
                } else if (stack.isOf(Items.WHITE_DYE)) {
                    if (hasWhiteDye || hasPinkDye) return false;
                    hasWhiteDye = true;
                } else {
                    return false;
                }
            }
        }
        return hasMaidSuit && (hasPinkDye || hasWhiteDye);
    }

    @Override
    public ItemStack craft(net.minecraft.recipe.input.CraftingRecipeInput inventory, net.minecraft.registry.RegistryWrapper.WrapperLookup registryManager) {
        ItemStack maidSuit = ItemStack.EMPTY;
        boolean isPink = false;

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    maidSuit = stack;
                } else if (stack.isOf(Items.PINK_DYE)) {
                    isPink = true;
                }
            }
        }

        if (maidSuit.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack result = maidSuit.copy();
        result.setCount(1);
        
        net.minecraft.component.type.NbtComponent customData = result.getOrDefault(net.minecraft.component.DataComponentTypes.CUSTOM_DATA, net.minecraft.component.type.NbtComponent.DEFAULT);
        net.minecraft.nbt.NbtCompound nbt = customData.copyNbt();
        nbt.putString("maid_suit_color", isPink ? "pink" : "white");
        result.set(net.minecraft.component.DataComponentTypes.CUSTOM_DATA, net.minecraft.component.type.NbtComponent.of(nbt));

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
