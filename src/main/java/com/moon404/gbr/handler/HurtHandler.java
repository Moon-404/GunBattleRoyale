package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.item.R2R5Item;
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
        if (source.isFall() && event.getEntity() instanceof Player player)
        {
            ItemStack itemStack = player.getOffhandItem();
            if (itemStack.getItem() == GunBattleRoyaleItems.BOOT.get() && !player.hasEffect(GunBattleRoyaleEffects.SILENCE.get()) && ClassType.getClass(player) == ClassType.ROGUE)
            {
                itemStack.setCount(itemStack.getCount() - 1);
            }
            else
            {
                double duration = Math.sqrt(event.getAmount()) * 10;
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int)duration, 4));
            }
            event.setCanceled(true);
            return;
        }

        if (event.getEntity() instanceof Player player)
        {
            ItemStack itemStack = player.getMainHandItem();
            if (itemStack.getItem() == GunBattleRoyaleItems.R2R5.get())
            {
                event.setAmount(event.getAmount() / 2);
                if (R2R5Item.bursting(itemStack))
                {
                    event.setAmount(event.getAmount() / 2);
                }
            }
        }

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
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.GLOWING, 100));
                itemStack.setCount(itemStack.getCount() - 1);
            }

            itemStack = from.getMainHandItem();
            if (itemStack.getItem() == GunBattleRoyaleItems.R2R5.get())
            {
                int energy = R2R5Item.getEnergy(itemStack);
                int deltaEnergy = (int)(event.getAmount() / R2R5Item.CHARGE_RATIO);
                R2R5Item.setEnergy(itemStack, energy + deltaEnergy);
            }
        }

        if (event.getEntity() instanceof Player player)
        {
            ItemStack itemStack = player.getOffhandItem();
            if (!player.hasEffect(MobEffects.MOVEMENT_SPEED) && itemStack.getItem() == GunBattleRoyaleItems.FAST.get() && !player.hasEffect(GunBattleRoyaleEffects.SILENCE.get()) && ClassType.getClass(player) == ClassType.ATTACK)
            {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 2));
                itemStack.setCount(itemStack.getCount() - 1);
            }
        }
    }
}
