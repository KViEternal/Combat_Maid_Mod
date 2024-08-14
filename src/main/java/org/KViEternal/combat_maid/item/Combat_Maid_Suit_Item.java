package org.KViEternal.combat_maid.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;

public class Combat_Maid_Suit_Item extends ArmorItem {
    public Combat_Maid_Suit_Item(ArmorMaterial material, Type type) {
        super(material, type, new Item.Settings().fireproof().rarity(Rarity.EPIC));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.combat_maid.combat_maid_suit_tooltip"));
    }
}
