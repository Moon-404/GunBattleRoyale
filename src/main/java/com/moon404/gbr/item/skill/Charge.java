package com.moon404.gbr.item.skill;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.entity.player.Player;

public class Charge extends SkillItem
{
    public Charge(Properties properties)
    {
        super(properties, ClassType.SUPPORT);
    }

    @Override
    public boolean onToss(Player player)
    {
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        for (Player target : player.level.players())
        {
            if (target.getTeam() == player.getTeam())
            {
                target.giveExperiencePoints(2500);
            }
        }
        return true;
    }
}
