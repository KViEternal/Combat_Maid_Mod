package org.KViEternal.combat_maid;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
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
    public static final Item Maid_Suit_Item = register(new Combat_Maid_Suit_Item(new Combat_Maid_Suit_Material(), ArmorItem.Type.CHESTPLATE), "combat_maid_suit");
    public static final Item Improved_Maid_Suit_Item = register(new Improved_Combat_Maid_Suit_Item(new Combat_Maid_Suit_Material(), ArmorItem.Type.CHESTPLATE), "improved_combat_maid_suit");

    // Define the registry key for your custom creative tab
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            new Identifier("combat_maid", "combat_maid_tab")
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Combat Maids mod successfully initialized!");
        Registry.register(Registries.ITEM_GROUP,
                CUSTOM_ITEM_GROUP,
                FabricItemGroup.builder().displayName(Text.of("Combat Maid Mod")).icon(() -> new ItemStack(Maid_Suit_Item)).entries((context, entries) -> {
                    entries.add(Reinforced_Cloth);
                    entries.add(Maid_Suit_Item);
                    entries.add(Improved_Maid_Suit_Item);
                }).build());
    }

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of("combat_maid", id);
        return Registry.register(Registries.ITEM, itemID, item);
    }
}
