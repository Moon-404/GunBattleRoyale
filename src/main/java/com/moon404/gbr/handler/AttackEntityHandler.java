package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.item.R2R5Item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AttackEntityHandler
{
    private static void launch(Entity entity)
    {
        if (entity instanceof LivingEntity livingEntity)
        {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 10, false, false, false));
        }
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event)
    {
        if (!(event.getEntity() instanceof ServerPlayer)) return;
        Player player = event.getEntity();
        Entity target = event.getTarget();
        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() != GunBattleRoyaleItems.R2R5.get()) return;
        if (!R2R5Item.bursting(itemStack)) return;
        if (player.isOnGround())
        {
            R2R5Item.setEnergy(itemStack, R2R5Item.getEnergy(itemStack) - R2R5Item.LAUNCH_ENERGY);
            launch(player);
            launch(target);
        }
    }
}