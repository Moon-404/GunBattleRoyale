package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.SmokeEntity;
import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleEntities;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.entity.player.Player;

public class Smoke extends SkillItem
{
    public Smoke(Properties properties)
    {
        super(properties, ClassType.ATTACK);
    }
    
    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        SmokeEntity smoke = new SmokeEntity(GunBattleRoyaleEntities.SMOKE.get(), player.level);
        smoke.setPos(player.getEyePosition());
        smoke.setNoGravity(true);
        smoke.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 2, 0);
        player.level.addFreshEntity(smoke);
        return true;
    }
}
