package net.J2P.MailBox.block;

import net.J2P.MailBox.MailBoxMod;
import net.J2P.MailBox.utils.StringNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static Block mail_box;

    public static void preInit(){

        mail_box = new MailBoxBlock(Material.IRON, StringNames.MAIL_BOX);

        registerBlocks();
    }

    public static void registerBlocks(){
        registerBlock(mail_box, StringNames.MAIL_BOX);
    }

    public static void registerBlock(Block b, String name){
        GameRegistry.register(b, new ResourceLocation(MailBoxMod.MODID, name));
        GameRegistry.register(new ItemBlock(b), new ResourceLocation(MailBoxMod.MODID, name));
    }

    // Enregistrer les textures de TOUS les blocks
    public static void registerRenders(){
        registerRender(mail_box);
    }

    // Enregistrer la texture d'UN SEUL block
    public static void registerRender(Block b){
        Item i = Item.getItemFromBlock(b);

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(MailBoxMod.MODID+":"+i.getUnlocalizedName().substring(5), "inventory"));
//        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(b.getRegistryName(), "inventory"));
    }

}
