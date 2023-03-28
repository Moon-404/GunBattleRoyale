package com.moon404.gbr.handler;

import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.event.GunReloadEvent;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.util.GunEnchantmentHelper;
import com.mrcrayfish.guns.util.GunModifierHelper;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GunReloadHandler
{
    @SubscribeEvent
    public static void onPostGunReload(GunReloadEvent.Post event)
    {
        if (!event.isClient()) return;
        Player player = event.getEntity();
        ItemStack itemStack = event.getStack();
        ItemCooldowns cooldowns = player.getCooldowns();
        if (itemStack.getItem() instanceof GunItem item)
        {
            Gun modifiedGun = item.getModifiedGun(itemStack);
            int rate = GunEnchantmentHelper.getRate(itemStack, modifiedGun);
            rate = GunModifierHelper.getModifiedRate(itemStack, rate);
            float percent = cooldowns.getCooldownPercent(item, 0);
            int last = Math.round(percent * rate);
            int interval = GunEnchantmentHelper.getReloadInterval(itemStack);
            cooldowns.addCooldown(item, last + interval);
        }
    }
}
