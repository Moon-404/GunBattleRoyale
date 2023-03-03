package com.moon404.gbr.handler;

import com.moon404.gbr.entity.ChargingLaserEntity;
import com.mrcrayfish.guns.event.GunProjectileHitEvent;

import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ProjectileHitHandler
{
    @SubscribeEvent
    public static void onProjectileHit(GunProjectileHitEvent event)
    {
        if (event.getProjectile() instanceof ChargingLaserEntity)
        {
            ChargingLaserEntity laser = (ChargingLaserEntity)event.getProjectile();
            HitResult result = event.getRayTrace();
            Vec3 hit = result.getLocation();
            if (laser.laser == null) return;
            laser.laser.length = (float)laser.laser.from.distanceTo(hit);
        }
    }    
}
