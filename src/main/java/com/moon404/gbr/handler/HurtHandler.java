package com.moon404.gbr.handler;

import com.moon404.gbr.struct.DamageInfo;
import com.moon404.gbr.struct.ShowDamageMessage;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

public class HurtHandler
{
    @SubscribeEvent
    public static void onLivingDamage(LivingHurtEvent event)
    {
        DamageSource source = event.getSource();
        if (source.getEntity() instanceof ServerPlayer from)
        {
            DamageInfo damage = new DamageInfo();
            damage.amount = event.getAmount();
            if (event.getEntity() instanceof Player target)
            {
                if (target.getAbsorptionAmount() > 0)
                {
                    damage.getArmorColor(target.experienceLevel);
                }
            }
            from.giveExperiencePoints((int)(damage.amount * 100));
            ShowDamageMessage.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {return from;}), damage);
        }
    }
}
