package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.PearlEntity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class Pearl extends SkillItem
{
    public Pearl(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onToss(Player player)
    {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 114514, 4));
        PearlEntity pearl = new PearlEntity(player.level, player);
        pearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1, 0);
        player.level.addFreshEntity(pearl);
        return true;
    }
}
