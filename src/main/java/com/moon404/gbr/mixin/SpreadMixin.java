package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.moon404.gbr.EventHandler;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.common.SpreadTracker;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import com.mrcrayfish.guns.init.ModSyncedDataKeys;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.util.GunModifierHelper;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

@Mixin(ProjectileEntity.class)
public class SpreadMixin
{
    private RandomSource random = RandomSource.create();

    @Shadow(remap=false)
    private Vec3 getVectorFromRotation(float pitch, float yaw)
    {
        throw new NullPointerException("This code should not be running!");
    }

    @Overwrite(remap=false)
    private Vec3 getDirection(LivingEntity shooter, ItemStack weapon, GunItem item, Gun modifiedGun)
    {
        // 基础扩散
        float gunSpread = GunModifierHelper.getModifiedSpread(weapon, modifiedGun.getGeneral().getSpread());
        if (gunSpread == 0.0F)
        {
            return this.getVectorFromRotation(shooter.getXRot(), shooter.getYRot());
        }
        else
        {
            if (shooter instanceof Player)
            {
                // 如果枪不总是扩散，根据射击时间改变基准值
                if (!modifiedGun.getGeneral().isAlwaysSpread())
                {
                    gunSpread *= SpreadTracker.get((Player)shooter).getSpread(item);
                }
                // 非霰弹枪散布调整
                if (modifiedGun.getGeneral().getProjectileAmount() == 1)
                {
                    // 瞄准时散布变为 10%
                    if ((Boolean)ModSyncedDataKeys.AIMING.getValue((Player)shooter))
                    {
                        gunSpread *= 0.1F;
                    }
                    // 根据移动速度改变扩散
                    gunSpread *= (0.6F + EventHandler.speedMap.get(shooter));
                }
            }
            return this.getVectorFromRotation(
                shooter.getXRot() - gunSpread / 2.0F + this.random.nextFloat() * gunSpread,
                shooter.getYHeadRot() - gunSpread / 2.0F + this.random.nextFloat() * gunSpread);
        }
    }
}
