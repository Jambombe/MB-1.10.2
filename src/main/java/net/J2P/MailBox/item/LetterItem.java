package net.J2P.MailBox.item;

import net.J2P.MailBox.MailBoxMod;
import net.J2P.MailBox.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LetterItem extends Item {

    public LetterItem(String name) {
        this.setUnlocalizedName(name);
        this.setCreativeTab(MailBoxMod.tabMailBox);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        Block b = worldIn.getBlockState(pos).getBlock();

        if (b == ModBlocks.mail_box){
            // Supprime une lettre de la main
//            playerIn.setHeldItem(hand, new ItemStack(ModItems.letter, stack.stackSize -1));
            stack.stackSize--;

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.PASS;
    }
}
