package org.KViEternal.combat_maid.materials;

import com.google.common.base.Suppliers;
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

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.minecraft.registry.Registry.register;

public class Combat_Maid_Suit_Material {

    public static final RegistryEntry<ArmorMaterial> COMBAT_MAID_SUIT = registerMaterial("combat_maid_suit", Map.of(
            ArmorItem.Type.CHESTPLATE, 0
    ), 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.ofItems(net.minecraft.item.Items.BLACK_WOOL), 0.0f, 0.0f, false);

    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, Map<ArmorItem.Type, Integer> defensePoints, int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(Identifier.of("combat_maid", id), "", dyeable)
        );

        ArmorMaterial material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance);
        material = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of("combat_maid", id), material);

        return RegistryEntry.of(material);
    }
}
