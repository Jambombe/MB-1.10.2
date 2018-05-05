package net.J2P.MailBox.recipes;

import net.J2P.MailBox.block.ModBlocks;
import net.J2P.MailBox.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

    public static void registerAllRecipes(){
        registerCraftingRecipes();
        registerFurnaceRecipes();
        registerBrewingRecipes();
    }

    public static void registerCraftingRecipes(){

        // MailBox
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mail_box), new Object[]{" L ", "YCB", " F ", 'L', ModItems.letter, 'Y', new ItemStack(Items.DYE,1, 11), 'C', Blocks.CHEST, 'B', new ItemStack(Items.DYE,1, 4), 'F', Items.FEATHER });

        // Letter Stamps
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.letter_stamp, 2), new Object[]{Items.PAPER, Items.SLIME_BALL});

        // Lettre rédigée
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.letter), new Object[]{ModItems.letter_stamp, Items.WRITTEN_BOOK});
    }

    public static void registerFurnaceRecipes(){

    }

    public static void registerBrewingRecipes(){

    }

}
