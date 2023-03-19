package com.moon404.gbr.item.skill;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Glow extends SkillItem
{
    public Glow(Properties properties)
    {
        super(properties, ClassType.SCOUT);
    }

    @Override
    public boolean onToss(Player player)
    {
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        Level level = player.level;
        for (Player target : level.players())
        {
            double distance = player.distanceTo(target);
            if (distance < 100)
            {
                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100 - (int)distance));
            }
        }
        return true;
    }
}
