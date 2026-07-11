package org.KViEternal.combat_maid.materials;

import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;
import org.KViEternal.combat_maid.Combat_Maid;

import java.util.EnumMap;
import java.util.Map;

public class Combat_Maid_Suit_Material {

    public static final RegistryKey<EquipmentAsset> COMBAT_MAID_SUIT_ASSET = RegistryKey.of(net.minecraft.item.equipment.EquipmentAssetKeys.REGISTRY_KEY, Identifier.of("combat_maid", "combat_maid_suit"));

    public static final ArmorMaterial COMBAT_MAID_SUIT = registerMaterial(
            "combat_maid_suit",
            Map.of(EquipmentType.CHESTPLATE, 0),
            0,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            ItemTags.WOOL, // Using WOOL tag since it requires a TagKey<Item>
            0.0f,
            0.0f
    );

    public static ArmorMaterial registerMaterial(String id, Map<EquipmentType, Integer> defensePoints, int enchantability, RegistryEntry<SoundEvent> equipSound, TagKey<Item> repairIngredient, float toughness, float knockbackResistance) {
        return new ArmorMaterial(
                0, // durability (not explicitly used for unbreakable items usually, but set 0)
                defensePoints,
                enchantability,
                equipSound,
                toughness,
                knockbackResistance,
                repairIngredient,
                COMBAT_MAID_SUIT_ASSET
        );
    }
}
