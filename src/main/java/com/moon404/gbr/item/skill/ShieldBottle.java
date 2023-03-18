package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.ShieldBottleEntity;
import com.moon404.gbr.init.GunBattleRoyaleEntities;

import net.minecraft.world.entity.item.ItemEntity;

public class ShieldBottle extends SkillItem
{
    public ShieldBottle(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onLand(ItemEntity entity)
    {
        ShieldBottleEntity bottle = new ShieldBottleEntity(GunBattleRoyaleEntities.SHIELD_BOTTLE.get(), entity.level);
        bottle.setPos(entity.position());
        entity.level.addFreshEntity(bottle);
        entity.kill();
        return false;
    }
}
