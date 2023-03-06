package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import com.mrcrayfish.guns.util.GunEnchantmentHelper;
import com.mrcrayfish.guns.util.GunModifierHelper;

import net.minecraft.world.item.ItemStack;

@Mixin(ProjectileEntity.class)
public class DamageMixin extends EntityMixin
{
    @Shadow
    protected Gun.Projectile projectile;
    @Shadow
    protected float additionalDamage;
    @Shadow
    private ItemStack weapon;
    @Shadow
    protected Gun modifiedGun;
    @Shadow
    protected Gun.General general;

    @Overwrite(remap = false)
    public float getDamage()
    {
        float initialDamage = (this.projectile.getDamage() + this.additionalDamage);
        if (this.projectile.isDamageReduceOverLife())
        {
            float modifier = (float)(this.projectile.getLife() - this.tickCount + 1) / (float)(this.projectile.getLife() + 1);
            initialDamage *= modifier;
        }
        float damage = initialDamage / this.general.getProjectileAmount();
        damage = GunModifierHelper.getModifiedDamage(this.weapon, this.modifiedGun, damage);
        damage = GunEnchantmentHelper.getAcceleratorDamage(this.weapon, damage);
        return Math.max(0F, damage);
    }
}
