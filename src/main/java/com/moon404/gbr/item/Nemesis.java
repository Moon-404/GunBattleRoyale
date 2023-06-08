package com.moon404.gbr.item;

import com.mrcrayfish.guns.item.GunItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/*
 * CompoundTags:
 * energy: 0-8 充能点数，每一轮射击获得1点，每2点降低1tick的射击间隔
 * charge: 0-100 充能点数，射击时重置为100，变为0时energy减1，然后重置为20
 * count: 1-4 标识本次射击是第几发
 */
public class Nemesis extends GunItem
{
    public Nemesis(Properties properties)
    {
        super(properties);
    }
    
    @Override
    public int getBarColor(ItemStack itemStack)
    {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        int energy = compoundTag.getInt("energy");
        return 0x555500 + 0x111111 * energy;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slotId, boolean isSelected)
    {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        int energy = compoundTag.getInt("energy");
        int charge = compoundTag.getInt("charge");
        charge -= 1;
        if (charge <= 0)
        {
            charge = 20;
            energy = Math.max(0, energy - 1);
        }
        compoundTag.putInt("energy", energy);
        compoundTag.putInt("charge", charge);
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack)
    {
        return true;
    }
}
