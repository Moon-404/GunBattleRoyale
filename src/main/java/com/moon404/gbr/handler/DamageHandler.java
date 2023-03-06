package com.moon404.gbr.handler;

import com.moon404.gbr.struct.DamageInfo;
import com.moon404.gbr.struct.ShowDamageMessage;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

public class DamageHandler
{
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event)
    {
        DamageSource source = event.getSource();
        Entity from = source.getEntity();
        if (from instanceof ServerPlayer)
        {
            DamageInfo damage = new DamageInfo();
            damage.amount = event.getAmount();
            ShowDamageMessage.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayer)from;}), damage);
        }
    }
}
