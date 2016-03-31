package com.example.RakuTool;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

// MODメインクラス
@Mod(modid = RakuTool.MODID,name = RakuTool.MODNAME,version = RakuTool.VERSION)// (modId="MyModId",name="My example mod",version="1.0",dependencies="required-after:FML")
public class RakuTool
{
    // MOD ID
    public static final String MODID = "RakuTool";
    // バージョン
    public static final String VERSION = "1.0";
    // MOD NAME
    public static final String MODNAME = "RakuTool";
    // MOD ITEMNAME
    public static final String MODITEMNAME_AXE = "AxeOfPillar";
    // 斧アイテムクラス
    private static AxeOfPillar itemAxeOfPillar = new AxeOfPillar();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        itemAxeOfPillar = new AxeOfPillar(); // アイテム生成
        GameRegistry.registerItem(itemAxeOfPillar, MODITEMNAME_AXE); // アイテム登録
        // アイテムにモデルJSONを設定
        if (event.getSide().isClient()) {
            ModelLoader.setCustomModelResourceLocation(itemAxeOfPillar, 0, new ModelResourceLocation(MODID + ":" + "TAxe0_model"));
        }

        // ハンドラ登録
        MinecraftForge.EVENT_BUS.register(new BreakHandler());

        if(DebugManager.DEBUG){
            System.out.println("FMLPreInitializationEvent☆");// イベント監視
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        if(DebugManager.DEBUG){
            System.out.println("FMLInitializationEvent☆");// イベント監視
        }
    }
}
