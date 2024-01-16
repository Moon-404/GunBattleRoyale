package com.moon404.gbr.item.skill;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class EnterVoid extends SkillItem
{
    public EnterVoid(Properties properties)
    {
        super(properties, ClassType.ROGUE);
    }

    private static int getFreeSlot(Inventory inventory)
    {
        for (int i = 9; i < inventory.items.size(); i++)
        {
            if (inventory.items.get(i).isEmpty())
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        Inventory inventory = player.getInventory();
        int freeBagSlot = getFreeSlot(inventory);
        if (freeBagSlot >= 0)
        {
            ItemStack itemStack = player.getMainHandItem();
            if (!itemStack.isEmpty())
            {
                inventory.setItem(freeBagSlot, itemStack.copy());
                inventory.removeItem(itemStack);
            }
        }
        freeBagSlot = getFreeSlot(inventory);
        if (freeBagSlot >= 0)
        {
            ItemStack itemStack = player.getOffhandItem();
            if (!itemStack.isEmpty())
            {
                inventory.setItem(freeBagSlot, itemStack.copy());
                inventory.removeItem(itemStack);
            }
        }
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 100));
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
        return true;
    }
}
