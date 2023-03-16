package com.moon404.gbr.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class Stim extends SkillItem
{
    public Stim(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onToss(Player player)
    {
        float hp = player.getHealth();
        hp -= 6;
        if (hp < 1) hp = 1;
        player.setHealth(hp);
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 100, 1));
        return true;
    }
}
