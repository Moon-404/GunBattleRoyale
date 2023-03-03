package com.moon404.gbr.entity;

import com.moon404.gbr.struct.LaserInfo;
import com.moon404.gbr.struct.RenderLaserMessage;
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
    public LaserInfo laser;
    
    public ChargingLaserEntity(EntityType<? extends ProjectileEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public ChargingLaserEntity(EntityType<? extends ProjectileEntity> entityType, Level world, LivingEntity shooter, ItemStack weapon, GunItem item, Gun modifiedGun)
    {
        super(entityType, world, shooter, weapon, item, modifiedGun);
        laser = new LaserInfo();
        laser.from = shooter.getEyePosition();
        laser.length = (float)modifiedGun.getProjectile().getSpeed();
        laser.xRot = shooter.getXRot();
        laser.yRot = shooter.getYRot();
    }

    @Override
    public void tick()
    {
        super.tick();
        if (laser != null) RenderLaserMessage.INSTANCE.send(PacketDistributor.ALL.noArg(), laser);
    }
}
