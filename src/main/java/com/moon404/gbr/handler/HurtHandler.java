package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.message.ShowDamageMessage;
import com.moon404.gbr.struct.ClassType;
import com.moon404.gbr.struct.DamageInfo;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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

            ItemStack itemStack = from.getOffhandItem();
            if (itemStack.getItem() == GunBattleRoyaleItems.IRE.get() && !event.getEntity().hasEffect(MobEffects.GLOWING) && !from.hasEffect(GunBattleRoyaleEffects.SILENCE.get()) && ClassType.getClass(from) == ClassType.SCOUT)
            {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.GLOWING, 60));
                itemStack.setCount(itemStack.getCount() - 1);
            }
        }

        if (event.getEntity() instanceof Player player)
        {
            ItemStack itemStack = player.getOffhandItem();
            if (!player.hasEffect(MobEffects.MOVEMENT_SPEED) && itemStack.getItem() == GunBattleRoyaleItems.FAST.get() && !player.hasEffect(GunBattleRoyaleEffects.SILENCE.get()) && ClassType.getClass(player) == ClassType.ATTACK)
            {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2));
                itemStack.setCount(itemStack.getCount() - 1);
            }
        }
    }
}
