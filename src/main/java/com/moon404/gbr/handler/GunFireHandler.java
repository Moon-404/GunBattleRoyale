package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.item.Nemesis;
import com.moon404.gbr.struct.LaserInfo;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.event.GunFireEvent;
import com.mrcrayfish.guns.init.ModItems;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.attachment.IAttachment.Type;
import com.mrcrayfish.guns.util.GunModifierHelper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
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
    public static void onServerPostGunFire(GunFireEvent.Post event)
    {
        if (event.isClient()) return;
        if (event.getStack().getItem() instanceof Nemesis)
        {
            CompoundTag compoundTag = event.getStack().getOrCreateTag();
            int energy = compoundTag.getInt("energy");
            int charge = compoundTag.getInt("charge");
            int count = compoundTag.getInt("count") + 1;
            if (count > 4) count = 1;
            if (count == 4)
            {
                energy = Math.min(8, energy + 1);
                charge = 100;
            }
            compoundTag.putInt("energy", energy);
            compoundTag.putInt("charge", charge);
            compoundTag.putInt("count", count);
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

    private static int nemesisLastShotTick = 0;
    private static int nemesisShotCount = 0;
    @SubscribeEvent
    public static void onClientPostGunFire(GunFireEvent.Post event)
    {
        if (!event.isClient()) return;
        Player player = event.getEntity();
        ItemStack itemStack = event.getStack();
        Item item = itemStack.getItem();
        if (item instanceof Nemesis)
        {
            int tick = player.tickCount;
            Gun modifiedGun = ((GunItem)item).getModifiedGun(itemStack);
            int rate = modifiedGun.getGeneral().getRate();
            rate = GunModifierHelper.getModifiedRate(itemStack, rate);
            int energy = itemStack.getOrCreateTag().getInt("energy");
            int interval = rate - energy / 2;
            if (tick == nemesisLastShotTick + 1)
            {
                nemesisShotCount += 1;
            }
            else if (tick > nemesisLastShotTick + interval - 1)
            {
                nemesisShotCount = 0;
            }
            System.out.println(nemesisLastShotTick + " " + nemesisShotCount + " " + tick + " " + interval);
            nemesisLastShotTick = tick;
            if (nemesisShotCount == 4)
            {
                ItemCooldowns cooldowns = player.getCooldowns();
                cooldowns.addCooldown(item, interval);
            }
        }
    }
}
