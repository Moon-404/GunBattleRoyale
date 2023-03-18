package com.moon404.gbr.entity;

import com.mojang.math.Vector3f;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class PearlEntity extends ThrownEnderpearl
{
    public PearlEntity(EntityType<? extends ThrownEnderpearl> pEntityType, Level pLevel)
    {
        super(pEntityType, pLevel);
    }
    
    public PearlEntity(Level pLevel, LivingEntity pShooter)
    {
        super(pLevel, pShooter);
    }

    protected void onHitEntity(EntityHitResult pResult)
    {
        if (this.getOwner() instanceof LivingEntity entity)
        {
            entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        }
        super.onHitEntity(pResult);
    }

    protected void onHit(HitResult pResult)
    {
        if (this.getOwner() instanceof LivingEntity entity)
        {
            entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        }
        super.onHit(pResult);
    }

    public void tick()
    {
        if (this.level instanceof ServerLevel level)
        {
            Vector3f color = new Vector3f(0.67F, 0, 0.67F);
            DustParticleOptions options = new DustParticleOptions(color, 1.5F);
            level.sendParticles(options, this.getX(), this.getY(), this.getZ(), 0, 0, 0, 0, 0);
        }
        super.tick();
    }
}
