package com.example.GomiMod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PickupHandler
{
    @SubscribeEvent
    public void itemPickupEvent(PlayerEvent.ItemPickupEvent event)
    {
        System.out.println("ItemPickupEvent☆");// イベント監視
    }
}