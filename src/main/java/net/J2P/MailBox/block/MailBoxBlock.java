package net.J2P.MailBox.block;

import net.J2P.MailBox.MailBoxMod;
import net.J2P.MailBox.tileentity.MailBoxTE;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class MailBoxBlock extends Block implements ITileEntityProvider {


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
        return new MailBoxTE();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        updateTileEntityOwner(worldIn, pos, placer);
    }

    // Au placement du bloc, création d'un TileEntity MailBoxTE pour associer l'UUID de placer au bloc

    /**
     * Creation d'une TileEntity MailBoxTE, associant l'uuid du placer au bloc posé
     * @param worldIn instance de World dans laquelle est placé le bloc
     * @param pos Position du bloc dans le World worldIn
     * @param placer Instance de l'entité ayant posé le bloc
     */
    private void updateTileEntityOwner(World worldIn, BlockPos pos, EntityLivingBase placer) {
        if (! worldIn.isRemote){
            TileEntity te = worldIn.getTileEntity(pos);

            if (placer instanceof EntityPlayer) {
                if (te != null && te instanceof MailBoxTE) {

                    MailBoxTE mbte = (MailBoxTE) te;

                    UUID playerID = placer.getUniqueID();

                    mbte.updateUUID(playerID);
                    placer.addChatMessage(new TextComponentString("BlockPlaced : " + ((MailBoxTE) te).getPlayerNameFromUUID(playerID)));
                }
            }
        }
    }

    // Clic droit sur le bloc
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (! worldIn.isRemote){

            // Enregistrement
            TileEntity te = worldIn.getTileEntity(pos);

            if (te != null && te instanceof MailBoxTE){

            MailBoxTE mbte = (MailBoxTE) te;

            playerIn.addChatMessage(new TextComponentString("BlockActivated ("+pos.getX() +", "+pos.getY() +", "+pos.getZ() +") : " + mbte.getUUID()));
            }
        }

        return true;
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {

    }
}
