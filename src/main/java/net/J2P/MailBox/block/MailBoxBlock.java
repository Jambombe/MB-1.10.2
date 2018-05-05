package net.J2P.MailBox.block;

import net.J2P.MailBox.MailBoxMod;
import net.J2P.MailBox.owner.WeakOwner;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class MailBoxBlock extends Block {

    private String blockOwner;

    public MailBoxBlock(Material materialIn, String name) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(MailBoxMod.tabMailBox);
//        this.setBlockUnbreakable();
        this.setHardness(5F);
        this.setResistance(1000F);
        this.setSoundType(SoundType.LADDER);
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        EntityPlayer player = (EntityPlayer) placer;
        this.setBlockOwner(player.getName());
        player.addChatMessage(new TextComponentString("Bloc placé par : " + this.getBlockOwner()));

        UUID playerID = player.getUniqueID();

        NBTTagCompound nbt;
        if (stack.hasTagCompound())
            nbt = stack.getTagCompound();
        else
            nbt = new NBTTagCompound();

        nbt.setUniqueId("owner", playerID);
        stack.setTagCompound(nbt);

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

//        worldIn.;
        return false;
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        if (!worldIn.isRemote)
            playerIn.addChatMessage(new TextComponentString("Cette boîte aux lettres appartient à " + this.getBlockOwner()));


    }

    public ActionResult<ItemStack> onRightClickOnBlock(ItemStack stack, EntityPlayer player, World world, EnumHand hand){
        if (! world.isRemote)
            System.out.println("Right click");

        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    public void setBlockOwner(String playerName){ this.blockOwner = playerName; }
    public String getBlockOwner(){ return this.blockOwner; }
}
