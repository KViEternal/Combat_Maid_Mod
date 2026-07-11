package org.KViEternal.combat_maid;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.KViEternal.combat_maid.item.Combat_Maid_Suit_Item;
import org.KViEternal.combat_maid.materials.Combat_Maid_Suit_Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Combat_Maid implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("combat_maid");
    public static final Item Maid_Suit_Item = register(new Combat_Maid_Suit_Item(Combat_Maid_Suit_Material.COMBAT_MAID_SUIT, ArmorItem.Type.CHESTPLATE), "combat_maid_suit");
    public static final Item Elytra_Maid_Suit_Item = register(new org.KViEternal.combat_maid.item.Elytra_Combat_Maid_Suit_Item(Combat_Maid_Suit_Material.COMBAT_MAID_SUIT, ArmorItem.Type.CHESTPLATE), "elytra_combat_maid_suit");

    @Override
    public void onInitialize() {
        LOGGER.info("Combat Maids mod successfully initialized!");
        org.KViEternal.combat_maid.recipe.ModRecipes.registerRecipes();
    }

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of("combat_maid", id);
        return Registry.register(Registries.ITEM, itemID, item);
    }
}
