package org.KViEternal.combat_maid.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static final RecipeSerializer<MaidSuitUpgradeRecipe> MAID_SUIT_UPGRADE = 
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of("combat_maid", "maid_suit_upgrade"),
            new SpecialRecipeSerializer<>(MaidSuitUpgradeRecipe::new));

    public static final RecipeSerializer<MaidSuitElytraRecipe> MAID_SUIT_ELYTRA = 
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of("combat_maid", "maid_suit_elytra"),
            new SpecialRecipeSerializer<>(MaidSuitElytraRecipe::new));

    public static final RecipeSerializer<MaidSuitDyeRecipe> MAID_SUIT_DYE = 
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of("combat_maid", "maid_suit_dye"),
            new SpecialRecipeSerializer<>(MaidSuitDyeRecipe::new));

    public static void registerRecipes() {
        // Just calling this method will load the class and register the serializers
    }
}
