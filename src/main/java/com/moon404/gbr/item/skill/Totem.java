package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.TotemEntity;
import com.moon404.gbr.init.GunBattleRoyaleEntities;

import net.minecraft.world.entity.player.Player;

public class Totem extends SkillItem
{
    public Totem(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onToss(Player player)
    {
        TotemEntity totem = new TotemEntity(GunBattleRoyaleEntities.TOTEM.get(), player.level);
        totem.setPos(player.position());
        totem.player = player;
        player.level.addFreshEntity(totem);
        return true;
    }
}
