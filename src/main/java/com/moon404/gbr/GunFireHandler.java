package com.moon404.gbr;

import com.mrcrayfish.guns.event.GunFireEvent;

import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GunFireHandler
{
    @SubscribeEvent
    public static void onGunFire(GunFireEvent.Post event)
    {
        if (event.isClient()) return;
        Player player = event.getEntity();
        // ArmorStand armorStand = new ArmorStand(player.getLevel(), player.getX(), player.getY(), player.getZ());
        // player.getLevel().addFreshEntity(armorStand);
    }
}
