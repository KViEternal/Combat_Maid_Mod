package org.KViEternal.combat_maid.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.util.Unit;

import java.util.List;

public class Elytra_Combat_Maid_Suit_Item extends Combat_Maid_Suit_Item {

    public Elytra_Combat_Maid_Suit_Item(ArmorMaterial material, EquipmentType type) {
        super(material, type, new Item.Settings()
                .armor(material, type)
                .component(DataComponentTypes.GLIDER, Unit.INSTANCE)
                .fireproof()
                .rarity(Rarity.EPIC));
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, net.minecraft.component.type.TooltipDisplayComponent displayComponent, java.util.function.Consumer<Text> textConsumer, TooltipType type) {
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        textConsumer.accept(Text.translatable("item.combat_maid.elytra_combat_maid_suit_tooltip"));
    }
}
