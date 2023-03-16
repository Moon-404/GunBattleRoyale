package com.moon404.gbr.handler;

import com.moon404.gbr.item.SkillItem;

import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemTossHandler
{
    @SubscribeEvent
    public static void onItemToss(ItemTossEvent event)
    {
        if (event.getPlayer().level.isClientSide) return;
        if (event.getEntity().getItem().getItem() instanceof SkillItem item)
        {
            event.setCanceled(item.onToss(event.getPlayer()));
        }
    }
}
