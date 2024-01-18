package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.item.R2R5Item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ShieldBlockHandler
{
    @SubscribeEvent
    public static void onShieldBlock(ShieldBlockEvent event)
    {
        if (event.getEntity() instanceof Player player)
        {
            ItemStack itemStack = player.getMainHandItem();
            if (itemStack.getItem() == GunBattleRoyaleItems.R2R5.get())
            {
                float amount = event.getOriginalBlockedDamage();
                if (player.isUsingItem()) amount /= 2;
                if (R2R5Item.bursting(itemStack)) amount /= 2;
                float blocked = event.getOriginalBlockedDamage() - amount;
                int deltaEnergy = (int)(blocked / 0.05);
                event.setBlockedDamage(blocked);
                int energy = R2R5Item.getEnergy(itemStack);
                R2R5Item.setEnergy(itemStack, energy + deltaEnergy);
            }
        }
    }
}
