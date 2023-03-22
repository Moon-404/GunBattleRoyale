package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.SnareEntity;
import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleEntities;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.entity.player.Player;

public class Snare extends SkillItem
{
    public Snare(Properties properties)
    {
        super(properties, ClassType.ATTACK);
    }

    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        SnareEntity snare = new SnareEntity(GunBattleRoyaleEntities.SNARE.get(), player.level);
        snare.user = player;
        snare.setPos(player.getEyePosition());
        snare.setNoGravity(true);
        snare.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 2, 0);
        player.level.addFreshEntity(snare);
        return true;
    }
}
