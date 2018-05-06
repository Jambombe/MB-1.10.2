package net.J2P.MailBox.block;

import net.J2P.MailBox.MailBoxMod;
import net.J2P.MailBox.owner.WeakOwner;
import net.J2P.MailBox.utils.StringNames;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
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

public class MailBoxBlock extends Block implements ITileEntityProvider {

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
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new MailBoxTileEntity();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        EntityPlayer player = null;
        if (placer != null && placer instanceof EntityPlayer)
            player = (EntityPlayer) placer;

        this.setBlockOwner(player.getName());
//        player.addChatMessage(new TextComponentString("Bloc placé par : " + this.getBlockOwner()));

//        UUID playerID = player.getUniqueID();
//
//        NBTTagCompound nbt;
//        if (stack.hasTagCompound())
//            nbt = stack.getTagCompound();
//        else
//            nbt = new NBTTagCompound();
//
//        nbt.setUniqueId("owner", playerID);
//        stack.setTagCompound(nbt);

        updateTileEntityOwner(worldIn, pos, placer);

    }

    // Au placement du bloc, on associe l'UUID de placer au bloc
    private void updateTileEntityOwner(World worldIn, BlockPos pos, EntityLivingBase placer) {
        if (! worldIn.isRemote){
            TileEntity te = worldIn.getTileEntity(pos);
            EntityPlayer player = (EntityPlayer) placer;

            if (te != null && te instanceof MailBoxTileEntity){

                MailBoxTileEntity mbte = (MailBoxTileEntity) te;

                UUID playerID = player.getUniqueID();

                mbte.updateUUID(playerID);
                player.addChatMessage(new TextComponentString("BlockPlaced : "+((MailBoxTileEntity) te).getPlayerNameFromUUID(playerID)));
            }
        }
    }

    // Clic droit sur le bloc
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

//        if (!worldIn.isRemote){
//            TileEntity te = worldIn.getTileEntity(pos);
//
//            if (te != null && te instanceof MailBoxTileEntity){
//
//                MailBoxTileEntity mbte = (MailBoxTileEntity) te;
//
//                mbte.addClick();
//                playerIn.addChatMessage(new TextComponentString(""+((MailBoxTileEntity) te).getClicks()));
//                return true;
//            }
//        }
          if (! worldIn.isRemote){
            TileEntity te = worldIn.getTileEntity(pos);

            if (te != null && te instanceof MailBoxTileEntity){

                MailBoxTileEntity mbte = (MailBoxTileEntity) te;

                playerIn.addChatMessage(new TextComponentString("BlockActivated : " + mbte.getTileData().getUniqueId(StringNames.TE_OWNER)));
            }
        }

        return true;
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {

    }

    public ActionResult<ItemStack> onRightClickOnBlock(ItemStack stack, EntityPlayer player, World world, EnumHand hand){
        if (! world.isRemote)
            System.out.println("Right click");

        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    public void setBlockOwner(String playerName){ this.blockOwner = playerName; }
    public String getBlockOwner(){ return this.blockOwner; }
}
