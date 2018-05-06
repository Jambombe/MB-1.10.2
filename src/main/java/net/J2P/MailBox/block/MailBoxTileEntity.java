package net.J2P.MailBox.block;

import net.J2P.MailBox.utils.StringNames;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public class MailBoxTileEntity extends TileEntity {

    private UUID playerId = null;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.hasKey(StringNames.TE_OWNER))
        {
            this.playerId = compound.getUniqueId(StringNames.TE_OWNER);
            compound.setUniqueId(StringNames.TE_OWNER, playerId);
            System.out.println("ID : readFromNbt : " + playerId);
        } else {
            System.out.println("WARN readToNbt : MailBoxTileEntity does not have any field called " + StringNames.TE_OWNER);
        }

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        if (!worldObj.isRemote){
            if (!compound.hasKey(StringNames.TE_OWNER) && playerId != null) {
                compound.setUniqueId(StringNames.TE_OWNER, playerId);
                System.out.println("ID : writeToNbt : " + playerId);
            } else {
                System.out.println("WARN writeToNbt : MailBoxTileEntity already have field called " + StringNames.TE_OWNER);
            }
        }

        return compound;
    }

    // Met à jour l'UUID du bloc
    public void updateUUID(UUID id){
        this.playerId = id;
        System.out.println("ID : updateUUID : " + id);
        System.out.println("playerID : updateUUID : " + this.playerId);
        this.getTileData().setUniqueId(StringNames.TE_OWNER, id);
    }


    public UUID getUUID(){
        return this.getTileData().getUniqueId(StringNames.TE_OWNER);
    }

    public String getPlayerNameFromUUID(UUID id){
        return this.worldObj.getPlayerEntityByUUID(id).getName();

    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        int metadata = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.readFromNBT(tag);
    }

    @Override
    public NBTTagCompound getTileData() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

//    public void addClick(){
//        this.getTileData().setInteger(StringNames.TE_OWNER, getTileData().getInteger(StringNames.TE_OWNER)+1);
//    }
//
//    public int getClicks(){
//        return getTileData().getInteger(StringNames.TE_OWNER);
//    }
}
