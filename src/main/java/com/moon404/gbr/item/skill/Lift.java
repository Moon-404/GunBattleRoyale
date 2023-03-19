package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.LiftEntity;
import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleEntities;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

public class Lift extends SkillItem
{
    public Lift(Properties properties)
    {
        super(properties, ClassType.ROGUE);
    }

    @Override
    public boolean onLand(ItemEntity entity)
    {
        if (entity.getThrowingEntity() instanceof Player player)
        {
            if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        }
        LiftEntity lift = new LiftEntity(GunBattleRoyaleEntities.LIFT.get(), entity.level);
        lift.setPos(entity.position());
        entity.level.addFreshEntity(lift);
        entity.kill();
        return false;
    }
}
