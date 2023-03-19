package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.ShieldBottleEntity;
import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleEntities;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

public class ShieldBottle extends SkillItem
{
    public ShieldBottle(Properties properties)
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
        ShieldBottleEntity bottle = new ShieldBottleEntity(GunBattleRoyaleEntities.SHIELD_BOTTLE.get(), entity.level);
        bottle.setPos(entity.position());
        entity.level.addFreshEntity(bottle);
        entity.kill();
        return false;
    }
}
