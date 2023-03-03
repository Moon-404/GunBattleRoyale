package com.moon404.gbr.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class GunBattleRoyaleTab extends CreativeModeTab
{
    public GunBattleRoyaleTab(String label)
    {
        super(label);
    }

    @Override
    public ItemStack makeIcon()
    {
        ItemStack stack = GunBattleRoyaleItems.LONG_SCOPE.get().getDefaultInstance();
        return stack;
    }
}
