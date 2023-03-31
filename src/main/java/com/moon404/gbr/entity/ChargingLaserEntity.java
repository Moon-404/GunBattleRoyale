package com.moon404.gbr.entity;

import java.util.List;

import com.moon404.gbr.message.RenderLaserMessage;
import com.moon404.gbr.struct.LaserInfo;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import com.mrcrayfish.guns.init.ModSyncedDataKeys;
import com.mrcrayfish.guns.item.GunItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
        laser.aiming = ModSyncedDataKeys.AIMING.getValue((Player)shooter) ? 1 : 0;
        CompoundTag compoundTag = shooter.getPersistentData();
        int charging = compoundTag.getInt("charging");
        laser.size = LaserInfo.DURATION_TICK - charging + 1;
        if (charging == 0)
        {
            this.setAdditionalDamage(laser.size / 2.0F);
            laser.size += laser.size / 2.0F;
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if (laser == null) return;
        List<ServerPlayer> players = this.getLevel().getServer().getPlayerList().getPlayers();
        for (ServerPlayer player : players)
        {
            if (player.equals(this.shooter)) laser.isShooter = 1;
            else laser.isShooter = 0;
            RenderLaserMessage.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {return player;}), laser);
        }
    }
}
