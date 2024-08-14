package org.KViEternal.combat_maid.item;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class Reinforced_Cloth extends Item {
    public Reinforced_Cloth() {
        super(new Item.Settings().rarity(Rarity.EPIC).fireproof());
    }
}
