package org.KViEternal.combat_maid.item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import java.util.List;

public class Combat_Maid_Suit_Item extends Item {
    public Combat_Maid_Suit_Item(ArmorMaterial material, EquipmentType type) {
        this(material, type, new Item.Settings().armor(material, type).fireproof().rarity(Rarity.EPIC));
    }

    public Combat_Maid_Suit_Item(ArmorMaterial material, EquipmentType type, Item.Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        net.minecraft.component.type.NbtComponent customData = stack.get(net.minecraft.component.DataComponentTypes.CUSTOM_DATA);
        if (customData != null && customData.copyNbt().contains("combat_maid_base_material")) {
            String material = customData.copyNbt().getString("combat_maid_base_material").orElse("");
            if (!material.isEmpty()) {
                String displayMaterial = material.substring(0, 1).toUpperCase() + material.substring(1).toLowerCase();
                return Text.literal("Combat Maid Suit (" + displayMaterial + ")");
            }
        }
        return super.getName(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, net.minecraft.component.type.TooltipDisplayComponent displayComponent, java.util.function.Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.combat_maid.combat_maid_suit_tooltip"));
    }
}
