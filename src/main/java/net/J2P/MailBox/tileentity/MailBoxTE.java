package net.J2P.MailBox.tileentity;

import net.J2P.MailBox.utils.StringNames;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public class MailBoxTE extends TileEntity {

    // Attributs à sauvegarder dans le TileEntity
    private UUID playerId = null;

    /////////////////////////////////////////////

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.playerId = compound.getUniqueId(StringNames.TE_OWNER);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (playerId != null)
            compound.setUniqueId(StringNames.TE_OWNER, playerId);

        return compound;
    }

    // Met à jour l'UUID du bloc
    // Méthode appelée au placement du bloc
    public void updateUUID(UUID id){
        this.playerId = id;
        this.getTileData().setUniqueId(StringNames.TE_OWNER, id);
    }


    public UUID getUUID(){
        return this.getTileData().getUniqueId(StringNames.TE_OWNER);
    }

    public String getPlayerNameFromUUID(UUID id){
        return this.worldObj.getPlayerEntityByUUID(id).getName();

    }

///////////// NETWORK METHODS /////////////

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
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

///////////////////////////////////////////

}
