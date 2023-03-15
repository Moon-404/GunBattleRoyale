package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

@Mixin(Inventory.class)
public class InventoryMixin
{
    @Shadow
    public NonNullList<ItemStack> items;
    @Shadow
    private static int SELECTION_SIZE;

    @Overwrite
    public int getFreeSlot()
    {
        for (int i = SELECTION_SIZE; i < this.items.size(); ++i)
        {
            if (this.items.get(i).isEmpty())
            {
                return i;
            }
        }
        for (int i = 0; i < SELECTION_SIZE; ++i)
        {
            if (this.items.get(i).isEmpty())
            {
                return i;
            }
        }
        return -1;
    }
}
