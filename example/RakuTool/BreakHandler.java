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
                	// 材質チェック
                	if(AxeOfPillar.Check(event.state.getBlock().toString())){
	                	int iCnt = AxeOfPillar.Cut(event.world, event.state, event.pos);// 一括破壊
	                	event.getPlayer().getHeldItemMainhand().setItemDamage(event.getPlayer().getHeldItemMainhand().getItemDamage()+iCnt);//耐久減少
	                	event.getPlayer().getHeldItemMainhand().getItem().showDurabilityBar(event.getPlayer().getHeldItemMainhand());//耐久表示更新
                	}
                	if(DebugManager.DEBUG){
                		System.out.println("だめーじ"+event.getPlayer().getHeldItemMainhand().getItemDamage());
                	}
                }
                // 耐久チェック
            	if(event.getPlayer().getHeldItemMainhand().getItemDamage() >= event.getPlayer().getHeldItemMainhand().getMaxDamage()){
            		//耐久０
            		AxeOfPillar.Broken(event.getPlayer().getHeldItemMainhand(), event.getPlayer());
            	}
                if(DebugManager.DEBUG){
            		System.out.println("canHarvestBlock="+event.getPlayer().getHeldItemMainhand().getItem().canHarvestBlock(event.state));
            		System.out.println("getHarvestLevel="+event.getPlayer().getHeldItemMainhand().getItem().getHarvestLevel(event.getPlayer().getHeldItemMainhand(),"axe"));
                	//event.stateを文字列で使う例、minecraft:log[axis=z,variant=birch]
                    System.out.println("PlayerEvent.BreakSpeed☆ "+event.state+event.getPlayer().getHeldItemMainhand().getItem().canHarvestBlock(event.state));
                    DebugManager.breakEvent = event;// 前回のイベント監視
                }
    		}
    	}
    }
}