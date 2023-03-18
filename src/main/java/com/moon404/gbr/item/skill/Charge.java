package com.moon404.gbr.item.skill;

import net.minecraft.world.entity.player.Player;

public class Charge extends SkillItem
{
    public Charge(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onToss(Player player)
    {
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