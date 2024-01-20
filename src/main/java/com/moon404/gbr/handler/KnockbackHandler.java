package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.item.R2R5Item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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
        if (entity.hasEffect(MobEffects.LEVITATION))
        {
            event.setCanceled(true);
        }
        ItemStack itemStack = entity.getMainHandItem();
        if (itemStack.getItem() == GunBattleRoyaleItems.R2R5.get())
        {
            if (R2R5Item.bursting(itemStack))
            {
                event.setCanceled(true);
            }
        }
    }
}
