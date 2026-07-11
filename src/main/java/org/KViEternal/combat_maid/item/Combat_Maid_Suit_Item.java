package org.KViEternal.combat_maid.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Combat_Maid_Suit_Item extends ArmorItem {
    public Combat_Maid_Suit_Item(ArmorMaterial material, Type type) {
        this(material, type, new Item.Settings().fireproof().rarity(Rarity.EPIC));
    }

    public Combat_Maid_Suit_Item(ArmorMaterial material, Type type, Item.Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (!this.getClass().getSimpleName().contains("Elytra")) {
            tooltip.add(Text.translatable("item.combat_maid.combat_maid_suit_tooltip"));
        }
    }

    @Override
    public Text getName(ItemStack stack) {
        String baseName = this.getClass().getSimpleName().contains("Elytra") ? "Elytra Maid Suit" : "Maid Suit";
        
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains("maid_suit_material")) {
            String material = nbt.getString("maid_suit_material");
            if (material != null && !material.isEmpty()) {
                material = material.substring(0, 1).toUpperCase() + material.substring(1).toLowerCase();
                return Text.literal(baseName + " (" + material + ")");
            }
        }
        
        return Text.literal(baseName);
    }
}
