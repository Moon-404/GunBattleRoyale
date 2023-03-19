package com.moon404.gbr.item.skill;

import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class Purify extends SkillItem
{
    public Purify(Properties properties)
    {
        super(properties, ClassType.SUPPORT);
    }

    @Override
    public boolean onToss(Player player)
    {
        for (Player target : player.level.players())
        {
            if (target.getTeam() == player.getTeam())
            {
                for (MobEffectInstance effect : player.getActiveEffects())
                {
                    if (effect.getEffect().getCategory() != MobEffectCategory.BENEFICIAL)
                    {
                        target.removeEffect(effect.getEffect());
                    }
                }
            }
        }
        return true;
    }
}
