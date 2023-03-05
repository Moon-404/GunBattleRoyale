package com.moon404.gbr.handler;

import com.moon404.gbr.struct.DamageInfo;
import com.moon404.gbr.struct.ShowDamageMessage;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

public class DamageHandler
{
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event)
    {
        LivingEntity target = event.getEntity();
        float amount = event.getAmount();
        DamageSource source = event.getSource();
        Entity from = source.getEntity();
        Vec3 pos = source.getSourcePosition();
        System.out.printf("%s -> %s with %s at %s\n", from.getScoreboardName(), target.getScoreboardName(), amount, pos.toString());
        if (from instanceof ServerPlayer)
        {
            DamageInfo damage = new DamageInfo();
            damage.amount = event.getAmount();
            ShowDamageMessage.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayer)from;}), damage);
        }
    }
}
