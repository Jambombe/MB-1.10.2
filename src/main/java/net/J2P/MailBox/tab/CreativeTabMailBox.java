package net.J2P.MailBox.tab;

import net.J2P.MailBox.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabMailBox extends CreativeTabs {

    public CreativeTabMailBox(int index, String label) {
        super(index, label);
    }

    // Item affiché en tant qu'icon du Creative tab
    @Override
    public Item getTabIconItem() {
        return ModItems.letter_stamp;
    }
}
