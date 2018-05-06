package net.J2P.MailBox;

import net.J2P.MailBox.tileentity.MailBoxTE;
import net.J2P.MailBox.block.ModBlocks;
import net.J2P.MailBox.item.ModItems;
import net.J2P.MailBox.proxy.CommonProxy;
import net.J2P.MailBox.recipes.ModRecipes;
import net.J2P.MailBox.tab.CreativeTabMailBox;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = MailBoxMod.MODID, version = MailBoxMod.VERSION, name = MailBoxMod.NAME)
public class MailBoxMod
{
    public static final String MODID = "mailbox";
    public static final String VERSION = "1.0";
    public static final String NAME = "Mail Box";

    @SidedProxy(clientSide = "net.J2P.MailBox.proxy.ClientProxy", serverSide = "net.J2P.MailBox.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static MailBoxMod instance;

    public static CreativeTabMailBox tabMailBox;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        tabMailBox = new CreativeTabMailBox(CreativeTabs.getNextID(), "tab_mailbox");

        ModItems.preInit();
        ModBlocks.preInit();

        ModRecipes.registerAllRecipes();

        proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event){

        proxy.init(event);

        GameRegistry.registerTileEntity(MailBoxTE.class, MODID+":MailBoxTE");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

        proxy.postInit(event);
    }
}
