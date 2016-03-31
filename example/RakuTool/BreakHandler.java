package com.example.RakuTool;

import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BreakHandler
{
    @SubscribeEvent
    public void breakBlockEvent(BreakEvent event)
    {
    	if(!event.getPlayer().capabilities.isCreativeMode)// クリエイティブ以外
    	{
    		if(event.getPlayer().getHeldItemMainhand() != null)// null チェック
    		{
                // 一括破壊処理：プレイヤーのメインハンドにあるアイテムのレジストリネームと、レジストリ内にあるこのMODで追加される斧のレジストリネームが同じなら処理を行う
                if(event.getPlayer().getHeldItemMainhand().getItem().getRegistryName().equals(GameRegistry.findItem(RakuTool.MODID, RakuTool.MODITEMNAME_AXE).getRegistryName()))
                {
                	int iCnt = AxeOfPillar.Cut(event.world, event.state, event.pos);
                	event.getPlayer().getHeldItemMainhand().setItemDamage(event.getPlayer().getHeldItemMainhand().getItemDamage()+iCnt);
                	event.getPlayer().getHeldItemMainhand().getItem().showDurabilityBar(event.getPlayer().getHeldItemMainhand());
                	if(event.getPlayer().getHeldItemMainhand().getItemDamage() >= event.getPlayer().getHeldItemMainhand().getMaxDamage()){
                		//耐久０
                		AxeOfPillar.Broken(event.getPlayer().getHeldItemMainhand(), event.getPlayer());
                	}
                	if(DebugManager.DEBUG){
                		System.out.println("だめーじ"+event.getPlayer().getHeldItemMainhand().getItemDamage());
                	}
                }
                if(DebugManager.DEBUG){
                    //event.stateを文字列で使う例、minecraft:log[axis=z,variant=birch]
                    System.out.println("PlayerEvent.BreakSpeed☆ ");
                    DebugManager.breakEvent = event;// 前回のイベント監視
                }
    		}
    	}
    }
}