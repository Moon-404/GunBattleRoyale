package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.moon404.gbr.item.Nemesis;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.util.GunEnchantmentHelper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

@Mixin(GunEnchantmentHelper.class)
public class GunEnchantmentMixin
{
    @Inject(method = "getRate", at = @At("RETURN"), cancellable = true)
    private static void nemesisRate(ItemStack weapon, Gun modifiedGun, CallbackInfoReturnable<Integer> cir)
    {
        if (weapon.getItem() instanceof Nemesis)
        {
            cir.setReturnValue(getRate(weapon, cir.getReturnValue()));
        }
    }

    private static int getRate(ItemStack weapon, int rate)
    {
        CompoundTag compoundTag = weapon.getOrCreateTag();
        int energy = compoundTag.getInt("energy"); 
        int count = compoundTag.getInt("count");
        if (count < 4)
        {
            rate = 1;
        }
        else
        {
            rate -= energy / 2;
        }
        System.out.println(count + " " + rate);
        return rate;
    }
}
