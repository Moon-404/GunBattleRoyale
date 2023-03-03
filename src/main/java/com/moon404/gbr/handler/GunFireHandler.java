package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.struct.LaserInfo;
import com.mrcrayfish.guns.event.GunFireEvent;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GunFireHandler
{
    @SubscribeEvent
    public static void onGunFire(GunFireEvent.Pre event)
    {
        if (event.isClient()) return;
        if (event.getStack().getItem() != GunBattleRoyaleItems.CHARGE_RIFLE.get()) return;
        Player player = event.getEntity();
        CompoundTag compoundTag = player.getPersistentData();
        compoundTag.putInt("charging", LaserInfo.DURATION_TICK);
    }
}
