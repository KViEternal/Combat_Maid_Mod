package org.KViEternal.combat_maid;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.KViEternal.combat_maid.item.Combat_Maid_Suit_Item;
import org.KViEternal.combat_maid.item.Improved_Combat_Maid_Suit_Item;
import org.KViEternal.combat_maid.item.Reinforced_Cloth;
import org.KViEternal.combat_maid.materials.Combat_Maid_Suit_Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Combat_Maid implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("combat_maid");
    public static final Item Reinforced_Cloth = register(new Reinforced_Cloth(), "reinforced_cloth");
    public static final Item Maid_Suit_Item = register(new Combat_Maid_Suit_Item(Combat_Maid_Suit_Material.Combat_Maid_Suit_Material, ArmorItem.Type.CHESTPLATE), "combat_maid_suit");
    public static final Item Improved_Maid_Suit_Item = register(new Improved_Combat_Maid_Suit_Item(Combat_Maid_Suit_Material.Combat_Maid_Suit_Material, ArmorItem.Type.CHESTPLATE), "improved_combat_maid_suit");

    @Override
    public void onInitialize() {
        LOGGER.info("Combat Maids mod successfully initialized!");
    }

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of("combat_maid", id);
        return Registry.register(Registries.ITEM, itemID, item);
    }
}
