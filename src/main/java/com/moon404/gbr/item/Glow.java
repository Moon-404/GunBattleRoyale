package com.moon404.gbr.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Glow extends SkillItem
{
    public Glow(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onToss(Player player)
    {
        Level level = player.level;
        for (Player target : level.players())
        {
            double distance = player.position().distanceTo(target.position());
            if (distance < 100)
            {
                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100 - (int)distance));
            }
        }
        return true;
    }
}
