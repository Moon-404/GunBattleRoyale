package com.moon404.gbr.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ShieldBoost extends RecoverItem
{
    public ShieldBoost(Item.Properties properties)
    {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 30;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
    {
        if (!level.isClientSide && entity instanceof Player player)
        {
            int oldlevel = player.experienceLevel;
            player.giveExperiencePoints(3000);
            int newlevel = player.experienceLevel;
            if (newlevel > oldlevel)
            {
                player.setAbsorptionAmount(player.getAbsorptionAmount() + 4);
            }
        }
        return this.isEdible() ? entity.eat(level, stack) : stack;
    }
}
