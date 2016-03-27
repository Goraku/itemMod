package com.example.GomiMod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerChangedDimensionHandler
{
    @SubscribeEvent
    public void playerChangedDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        
        System.out.println("PlayerChangedDimensionEvent☆");// イベント監視　要削除
    }
}
