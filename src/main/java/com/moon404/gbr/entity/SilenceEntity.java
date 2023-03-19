package com.moon404.gbr.entity;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleItems;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class SilenceEntity extends ThrowableItemProjectile
{
    public SilenceEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel)
    {
        super(pEntityType, pLevel);
    }

    @Override
    protected Item getDefaultItem()
    {
        return GunBattleRoyaleItems.SILENCE.get();
    }

    protected void onHit(HitResult pResult)
    {
        super.onHit(pResult);
        for (Player player : this.level.players())
        {
            if (this.distanceTo(player) <= 4)
            {
                player.addEffect(new MobEffectInstance(GunBattleRoyaleEffects.SILENCE.get(), 200));
            }
        }
        this.kill();
    }
}
