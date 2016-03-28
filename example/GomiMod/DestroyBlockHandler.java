package com.example.GomiMod;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DestroyBlockHandler
{
    @SubscribeEvent
    public void destroyBlockEvent(PlayerEvent.BreakSpeed event)
    {
        if(DebugManager.DEBUG){
            System.out.println("PlayerEvent.BreakSpeed☆"+
                "X"+event.pos.getX()+
                "Y"+event.pos.getY()+
                "Z"+event.pos.getZ()+","+
                ""+event.state.getBlock().getLocalizedName()+","+
                event.state.getBlock().getBlockHardness(event.state, event.entity.worldObj, event.pos)+", " );// イベント監視
        }
    }
}