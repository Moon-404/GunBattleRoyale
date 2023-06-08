package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.struct.LaserInfo;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.event.GunFireEvent;
import com.mrcrayfish.guns.init.ModItems;
import com.mrcrayfish.guns.item.attachment.IAttachment.Type;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GunFireHandler
{
    @SubscribeEvent
    public static void onServerPreGunFire(GunFireEvent.Pre event)
    {
        if (event.isClient()) return;
        if (event.getStack().getItem() == ModItems.HEAVY_RIFLE.get())
        {
            CompoundTag compoundTag = event.getStack().getOrCreateTag();
            int ammo = compoundTag.getInt("AmmoCount");
            if (ammo < 2)
            {
                event.setCanceled(true);
            }
            else
            {
                compoundTag.putInt("AmmoCount", ammo - 1);
            }
        }
        if (event.getStack().getItem() == GunBattleRoyaleItems.CHARGE_RIFLE.get())
        {
            Player player = event.getEntity();
            CompoundTag compoundTag = player.getPersistentData();
            compoundTag.putInt("charging", LaserInfo.DURATION_TICK);
        }
    }

    @SubscribeEvent
    public static void onClientPreGunFire(GunFireEvent.Pre event)
    {
        if (!event.isClient()) return;
        if (event.getStack().getItem() == ModItems.HEAVY_RIFLE.get())
        {
            CompoundTag compoundTag = event.getStack().getOrCreateTag();
            int ammo = compoundTag.getInt("AmmoCount");
            if (ammo < 2)
            {
                event.setCanceled(true);
            }
        }
        
        float threshold = 1.0F;
        ItemStack itemStack = event.getStack();
        ItemStack underBarrel = Gun.getAttachment(Type.UNDER_BARREL, itemStack);
        if (underBarrel.getItem() == GunBattleRoyaleItems.GRIP.get())
        {
            threshold *= 0.8F;
        }
        if (underBarrel.getItem() == GunBattleRoyaleItems.ADVANCED_GRIP.get())
        {
            threshold *= 0.6F;
        }
        if (event.getEntity().getAttackStrengthScale(0) < threshold)
        {
            event.setCanceled(true);
            return;
        }
    }
}
