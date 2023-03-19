package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.ExhibitEntity;
import com.moon404.gbr.init.GunBattleRoyaleEntities;

import net.minecraft.world.entity.item.ItemEntity;

public class Exhibit extends SkillItem
{
    public Exhibit(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onLand(ItemEntity entity)
    {
        ExhibitEntity exhibit = new ExhibitEntity(GunBattleRoyaleEntities.EXHHIBIT.get(), entity.level);
        exhibit.setPos(entity.position());
        entity.level.addFreshEntity(exhibit);
        entity.kill();
        return false;
    }
}
