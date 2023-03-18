package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.LiftEntity;
import com.moon404.gbr.init.GunBattleRoyaleEntities;

import net.minecraft.world.entity.item.ItemEntity;

public class Lift extends SkillItem
{
    public Lift(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onLand(ItemEntity entity)
    {
        LiftEntity lift = new LiftEntity(GunBattleRoyaleEntities.LIFT.get(), entity.level);
        lift.setPos(entity.position());
        entity.level.addFreshEntity(lift);
        entity.kill();
        return false;
    }
}
