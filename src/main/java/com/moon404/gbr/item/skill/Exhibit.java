package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.ExhibitEntity;
import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleEntities;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

public class Exhibit extends SkillItem
{
    public Exhibit(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onLand(ItemEntity entity)
    {
        if (entity.getThrowingEntity() instanceof Player player)
        {
            if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        }
        ExhibitEntity exhibit = new ExhibitEntity(GunBattleRoyaleEntities.EXHHIBIT.get(), entity.level);
        exhibit.setPos(entity.position());
        entity.level.addFreshEntity(exhibit);
        entity.kill();
        return false;
    }
}
