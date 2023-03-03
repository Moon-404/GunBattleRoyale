package com.moon404.gbr.entity;

import com.moon404.gbr.LaserInfo;
import com.moon404.gbr.RenderLaserMessage;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import com.mrcrayfish.guns.item.GunItem;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;

public class ChargingLaserEntity extends ProjectileEntity
{
    public ChargingLaserEntity(EntityType<? extends ProjectileEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public ChargingLaserEntity(EntityType<? extends ProjectileEntity> entityType, Level world, LivingEntity shooter, ItemStack weapon, GunItem item, Gun modifiedGun)
    {
        super(entityType, world, shooter, weapon, item, modifiedGun);
        LaserInfo laser = new LaserInfo();
        laser.from = this.position();
        laser.length = (float)modifiedGun.getProjectile().getSpeed();
        laser.xRot = shooter.getXRot();
        laser.yRot = shooter.getYRot();
        RenderLaserMessage.INSTANCE.send(PacketDistributor.ALL.noArg(), laser);
    }

    public void tick()
    {
        super.tick();
    }
}
