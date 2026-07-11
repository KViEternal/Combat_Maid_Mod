package org.KViEternal.combat_maid.recipe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;
import org.KViEternal.combat_maid.item.Combat_Maid_Suit_Item;

import net.minecraft.util.Identifier;
import java.util.Map;
import java.util.UUID;

public class MaidSuitUpgradeRecipe extends SpecialCraftingRecipe {

    public MaidSuitUpgradeRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(net.minecraft.recipe.input.CraftingRecipeInput inventory, World world) {
        boolean hasMaidSuit = false;
        boolean hasChestplate = false;

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    if (hasMaidSuit) return false;
                    hasMaidSuit = true;
                } else if (stack.getItem() instanceof ArmorItem armorItem && armorItem.getType() == ArmorItem.Type.CHESTPLATE) {
                    if (hasChestplate) return false;
                    hasChestplate = true;
                } else if (stack.isOf(Items.ELYTRA)) {
                    return false;
                } else if (stack.isOf(Items.PINK_DYE)) {
                    return false;
                } else {
                    return false;
                }
            }
        }
        return hasMaidSuit && hasChestplate;
    }

    @Override
    public ItemStack craft(net.minecraft.recipe.input.CraftingRecipeInput inventory, net.minecraft.registry.RegistryWrapper.WrapperLookup registryManager) {
        ItemStack maidSuit = ItemStack.EMPTY;
        ItemStack chestplate = ItemStack.EMPTY;

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Combat_Maid_Suit_Item) {
                    maidSuit = stack;
                } else if (stack.getItem() instanceof ArmorItem armorItem && armorItem.getType() == ArmorItem.Type.CHESTPLATE) {
                    chestplate = stack;
                }
            }
        }

        if (maidSuit.isEmpty() || chestplate.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack result = maidSuit.copy();
        result.setCount(1);

        ArmorItem armorItem = (ArmorItem) chestplate.getItem();
        net.minecraft.registry.entry.RegistryEntry<ArmorMaterial> material = armorItem.getMaterial();

        int totalArmor = material.value().defense().getOrDefault(ArmorItem.Type.HELMET, 0)
                + material.value().defense().getOrDefault(ArmorItem.Type.CHESTPLATE, 0)
                + material.value().defense().getOrDefault(ArmorItem.Type.LEGGINGS, 0)
                + material.value().defense().getOrDefault(ArmorItem.Type.BOOTS, 0);

        float totalToughness = material.value().toughness() * 4.0f;
        float totalKnockback = material.value().knockbackResistance() * 4.0f;

        Identifier modifierId = Identifier.of("combat_maid", "maid_suit_upgrade");
        net.minecraft.component.type.AttributeModifiersComponent.Builder modifiers = net.minecraft.component.type.AttributeModifiersComponent.builder();
        
        modifiers.add(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(modifierId, totalArmor, EntityAttributeModifier.Operation.ADD_VALUE), net.minecraft.component.type.AttributeModifierSlot.CHEST);
        
        if (totalToughness > 0) {
            modifiers.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(modifierId, totalToughness, EntityAttributeModifier.Operation.ADD_VALUE), net.minecraft.component.type.AttributeModifierSlot.CHEST);
        }
        
        if (totalKnockback > 0) {
            modifiers.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(modifierId, totalKnockback, EntityAttributeModifier.Operation.ADD_VALUE), net.minecraft.component.type.AttributeModifierSlot.CHEST);
        }
        
        result.set(net.minecraft.component.DataComponentTypes.ATTRIBUTE_MODIFIERS, modifiers.build());

        String path = Registries.ITEM.getId(chestplate.getItem()).getPath();
        String materialName = path.replace("_chestplate", "");
        
        net.minecraft.component.type.NbtComponent customData = result.getOrDefault(net.minecraft.component.DataComponentTypes.CUSTOM_DATA, net.minecraft.component.type.NbtComponent.DEFAULT);
        NbtCompound nbt = customData.copyNbt();
        nbt.putString("combat_maid_base_material", materialName);
        result.set(net.minecraft.component.DataComponentTypes.CUSTOM_DATA, net.minecraft.component.type.NbtComponent.of(nbt));

        net.minecraft.component.type.ItemEnchantmentsComponent maidEnchants = maidSuit.getOrDefault(net.minecraft.component.DataComponentTypes.ENCHANTMENTS, net.minecraft.component.type.ItemEnchantmentsComponent.DEFAULT);
        net.minecraft.component.type.ItemEnchantmentsComponent chestEnchants = chestplate.getOrDefault(net.minecraft.component.DataComponentTypes.ENCHANTMENTS, net.minecraft.component.type.ItemEnchantmentsComponent.DEFAULT);
        
        if (maidEnchants.isEmpty() && !chestEnchants.isEmpty()) {
            result.set(net.minecraft.component.DataComponentTypes.ENCHANTMENTS, chestEnchants);
            if (chestplate.contains(net.minecraft.component.DataComponentTypes.REPAIR_COST)) {
                result.set(net.minecraft.component.DataComponentTypes.REPAIR_COST, chestplate.get(net.minecraft.component.DataComponentTypes.REPAIR_COST));
            }
        }

        return result;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MAID_SUIT_UPGRADE;
    }
}
