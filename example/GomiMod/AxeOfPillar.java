package com.example.GomiMod;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/*
 * 縦方向破壊斧 アイテムクラス
 * */
public class AxeOfPillar extends Item
{
    // コンストラクタ
    public AxeOfPillar() {
        // 色んな設定をする
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabMisc);
        setUnlocalizedName("AxeOfPillar");
    }
}// AxeOfPillar
