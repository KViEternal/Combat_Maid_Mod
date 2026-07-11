package org.KViEternal.combat_maid.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;

public class Combat_Maid_Suit_Item extends ArmorItem {
    public Combat_Maid_Suit_Item(RegistryEntry<ArmorMaterial> material, Type type) {
        this(material, type, new Item.Settings().fireproof().rarity(Rarity.EPIC).maxDamage(-1));
    }

    public Combat_Maid_Suit_Item(RegistryEntry<ArmorMaterial> material, Type type, Item.Settings settings) {
        super(material, type, settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        net.minecraft.component.type.NbtComponent customData = stack.get(net.minecraft.component.DataComponentTypes.CUSTOM_DATA);
        if (customData != null && customData.contains("combat_maid_base_material")) {
            String material = customData.copyNbt().getString("combat_maid_base_material");
            String displayMaterial = material.substring(0, 1).toUpperCase() + material.substring(1).toLowerCase();
            return Text.literal("Combat Maid Suit (" + displayMaterial + ")");
        }
        return super.getName(stack);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("item.combat_maid.combat_maid_suit_tooltip"));
    }
}
