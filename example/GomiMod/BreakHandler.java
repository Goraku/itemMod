package com.example.GomiMod;

import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BreakHandler
{
	private BreakEvent e;
	@SubscribeEvent
	public void breakBlockEvent(BreakEvent event)
	{
        if(Debugmanager.DEBUG){
    		//event.stateを文字列で使う例、minecraft:log[axis=z,variant=birch]
        	System.out.println("PlayerEvent.BreakSpeed☆"+
            		"X"+event.pos.getX()+
            		"Y"+event.pos.getY()+
            		"Z"+event.pos.getZ()+","+
            		""+event.state.getBlock().getLocalizedName()+","+
            		event.state.getBlock().getBlockHardness(event.state, event.world, event.pos)+",getRegistryName="
            		+event.state.getBlock().getRegistryName()+",getUnlocalizedName="+
            		event.state.getBlock().getUnlocalizedName()+",event.state="+
            		event.state);
            e = event;// 前回のイベント監視
        }
	}
}