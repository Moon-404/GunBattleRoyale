package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.HealthBottleEntity;
import com.moon404.gbr.init.GunBattleRoyaleEntities;

import net.minecraft.world.entity.item.ItemEntity;

public class HealthBottle extends SkillItem
{
    public HealthBottle(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onLand(ItemEntity entity)
    {
        HealthBottleEntity bottle = new HealthBottleEntity(GunBattleRoyaleEntities.HEALTH_BOTTLE.get(), entity.level);
        bottle.setPos(entity.position());
        entity.level.addFreshEntity(bottle);
        entity.kill();
        return false;
    }
}
