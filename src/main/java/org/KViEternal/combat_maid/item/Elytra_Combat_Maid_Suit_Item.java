package org.KViEternal.combat_maid.item;

import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;

import java.util.List;

public class Elytra_Combat_Maid_Suit_Item extends Combat_Maid_Suit_Item implements FabricElytraItem {

    public Elytra_Combat_Maid_Suit_Item(RegistryEntry<ArmorMaterial> material, Type type) {
        super(material, type, new Item.Settings()
                .fireproof()
                .rarity(Rarity.EPIC));
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        tooltip.add(Text.translatable("item.combat_maid.elytra_combat_maid_suit_tooltip"));
    }

    @Override
    public boolean useCustomElytra(LivingEntity entity, ItemStack chestStack, boolean tickElytra) {
        return true;
    }
}
