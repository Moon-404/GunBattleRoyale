package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.HealthBottleEntity;
import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleEntities;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

public class HealthBottle extends SkillItem
{
    public HealthBottle(Properties properties)
    {
        super(properties, ClassType.SUPPORT);
    }

    @Override
    public boolean onLand(ItemEntity entity)
    {
        if (entity.getThrowingEntity() instanceof Player player)
        {
            if (ClassType.getClass(player) != this.classType) return false;
            if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        }
        HealthBottleEntity bottle = new HealthBottleEntity(GunBattleRoyaleEntities.HEALTH_BOTTLE.get(), entity.level);
        bottle.setPos(entity.position());
        entity.level.addFreshEntity(bottle);
        entity.kill();
        return false;
    }
}
