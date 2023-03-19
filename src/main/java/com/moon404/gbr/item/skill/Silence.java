package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.SilenceEntity;
import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleEntities;

import net.minecraft.world.entity.player.Player;

public class Silence extends SkillItem
{
    public Silence(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onToss(Player player)
    {
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        SilenceEntity silence = new SilenceEntity(GunBattleRoyaleEntities.SILENCE.get(), player.level);
        silence.setPos(player.getEyePosition());
        silence.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1, 0);
        player.level.addFreshEntity(silence);
        return true;
    }
}
