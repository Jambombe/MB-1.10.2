package net.J2P.MailBox.item;

import net.J2P.MailBox.MailBoxMod;
import net.J2P.MailBox.utils.StringNames;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    public static Item letter_stamp;
    public static Item letter;

    public static void preInit(){

        letter_stamp = new LetterStampItem(StringNames.LETTER_STAMP);
        letter = new LetterItem(StringNames.LETTER);

        registerItems();
    }

    public static void registerItems(){
        GameRegistry.register(letter_stamp, new ResourceLocation(MailBoxMod.MODID, StringNames.LETTER_STAMP));
        GameRegistry.register(letter, new ResourceLocation(MailBoxMod.MODID, StringNames.LETTER));

    }

    // Enregistrer les textures de TOUS les items
    public static void registerRenders(){
        registerRender(letter_stamp);
        registerRender(letter);
    }

    // Enregistrer la texture d'UN SEUL item
    public static void registerRender(Item i){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(MailBoxMod.MODID+":"+i.getUnlocalizedName().substring(5), "inventory"));
    }

}
