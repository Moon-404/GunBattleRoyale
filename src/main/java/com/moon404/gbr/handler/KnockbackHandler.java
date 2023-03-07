package com.moon404.gbr.handler;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KnockbackHandler
{
    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event)
    {
        LivingEntity entity = event.getEntity();
        if (entity.getAbsorptionAmount() > 0)
        {
            event.setCanceled(true);
        }
    }
}
