package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.struct.LaserInfo;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.event.GunFireEvent;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.util.GunEnchantmentHelper;
import com.mrcrayfish.guns.util.GunModifierHelper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GunFireHandler
{
    @SubscribeEvent
    public static void onPreGunFire(GunFireEvent.Pre event)
    {
        if (event.isClient()) return;
        if (event.getStack().getItem() != GunBattleRoyaleItems.CHARGE_RIFLE.get()) return;
        Player player = event.getEntity();
        CompoundTag compoundTag = player.getPersistentData();
        compoundTag.putInt("charging", LaserInfo.DURATION_TICK);
    }

    private static void addGunCD(ItemStack itemstack, ItemCooldowns cooldowns)
    {
        if (itemstack.getItem() instanceof GunItem item)
        {
            Gun modifiedGun = item.getModifiedGun(itemstack);
            int rate = GunEnchantmentHelper.getRate(itemstack, modifiedGun);
            rate = GunModifierHelper.getModifiedRate(itemstack, rate);
            cooldowns.addCooldown(item, rate);
        }
    }

    @SubscribeEvent
    public static void onPostGunFire(GunFireEvent.Post event)
    {
        if (!event.isClient()) return;
        Player player = event.getEntity();
        Inventory inventory = player.getInventory();
        ItemCooldowns cooldowns = player.getCooldowns();
        for (ItemStack itemstack : inventory.items)
        {
            addGunCD(itemstack, cooldowns);
        }
        for (ItemStack itemstack : inventory.armor)
        {
            addGunCD(itemstack, cooldowns);
        }
        for (ItemStack itemstack : inventory.offhand)
        {
            addGunCD(itemstack, cooldowns);
        }
    }
}
