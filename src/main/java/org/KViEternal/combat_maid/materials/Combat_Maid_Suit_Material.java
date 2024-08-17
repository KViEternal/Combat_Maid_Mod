package org.KViEternal.combat_maid.materials;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.KViEternal.combat_maid.Combat_Maid;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Combat_Maid_Suit_Material {

    public static final RegistryEntry<ArmorMaterial> Combat_Maid_Suit_Material = registerMaterial("combat_maid_suit", Map.of(
            ArmorItem.Type.CHESTPLATE, 25
    ), 20, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.ofItems(Combat_Maid.Reinforced_Cloth), 20, 1.5f, false);

    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, Map<ArmorItem.Type, Integer> defensePoints, int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(Identifier.of("combat_maid", id), "", dyeable)
        );

        ArmorMaterial material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance);
        material = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of("combat_maid", id), material);

        return RegistryEntry.of(material);
    }
}
