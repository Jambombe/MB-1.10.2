package net.J2P.MailBox.owner;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.UUID;

public class WeakOwner {

    private WeakReference<EntityPlayer> ownerEntity;
    private UUID ownerID;

    public void setOwner(EntityLivingBase owner) {    // The definitive moment when owner is known a-priori
        this.ownerID = owner.getUniqueID ();

        if (! (owner instanceof EntityPlayer)) {        // Awkwardly, Block's onBlockPlacedBy method gives us a livingBase
            this.ownerEntity = new WeakReference (null);  // And we must instantiate our private fields at all costs
            System.out.println ("Owner set by non-player entity!");
        }
        this.ownerEntity = new WeakReference ((EntityPlayer) owner);
    }

    public EntityPlayer getOwnerEntity() {
        EntityPlayer owner = ownerEntity.get ();        // See what entity we already know
        if (owner == null || owner.isDead) {            // If useless,
            owner = lookupOwner ();                       // Try to find it
        }
        return owner;                                   // WARNING: May still be null (not logged in)
    }

    public UUID getOwnerID() {
        return ownerID;
    }

    public EntityPlayer lookupOwner() {
//        if (ownerID == null) return null;
//
//        List<EntityPlayerMP> allPlayers = MinecraftServer.getServer ().getConfigurationManager ().playerEntityList;
//        for (EntityPlayerMP p : allPlayers) {
//            if (p.getUniqueID ().equals (ownerID)) {
//                setOwner (p);
//                return ownerEntity.get ();
//            }
//        }
        return null;
    }

    public void readFromNBT(NBTTagCompound compound) {    // Read two longs into the UUID, not used until needed
        ownerID = new UUID (compound.getLong ("UUIDMost"), compound.getLong ("UUIDLeast"));
        ownerEntity = new WeakReference (lookupOwner ());
    }

    public void writeToNBT(NBTTagCompound compound) {     // Write owner's UUID to two longs, in order needed at read
        if (null == ownerID) return;
        compound.setLong ("UUIDMost", ownerID.getMostSignificantBits ());
        compound.setLong ("UUIDLeast", ownerID.getLeastSignificantBits ());
    }

}
