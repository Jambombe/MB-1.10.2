package net.J2P.MailBox.proxy;

import net.J2P.MailBox.block.ModBlocks;
import net.J2P.MailBox.item.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {
        ModItems.registerRenders();
        ModBlocks.registerRenders();

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
