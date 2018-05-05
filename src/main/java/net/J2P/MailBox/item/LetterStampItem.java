package net.J2P.MailBox.item;

import net.J2P.MailBox.MailBoxMod;
import net.J2P.MailBox.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.List;

public class LetterStampItem extends Item {

    public LetterStampItem(String name) {
        this.setUnlocalizedName(name);
        this.setCreativeTab(MailBoxMod.tabMailBox);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        Block b = worldIn.getBlockState(pos).getBlock();

        playerIn.addChatMessage(new TextComponentString("You just right-clicked on " + b.getLocalizedName()));

        return EnumActionResult.SUCCESS;
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, EntityPlayer player, World world, EnumHand hand){
        if (! world.isRemote)
            System.out.println("Right click");

        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Used with letter to send it");
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
