package com.moon404.gbr.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public abstract class RecoverItem extends Item
{
    public static final FoodProperties RECOVER_ITEM = (new FoodProperties.Builder()).alwaysEat().build();

    public RecoverItem(Item.Properties properties)
    {
        super(properties);
    }

    public abstract int getUseDuration(ItemStack stack);

    public abstract ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity);

    public UseAnim getUseAnimation(ItemStack stack)
    {
        return UseAnim.EAT;
    }

    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int pRemainingUseDuration)
    {
        if (level.isClientSide) return;
        stack.setDamageValue(pRemainingUseDuration);
    }

    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int pTimeCharged)
    {
        if (level.isClientSide) return;
        stack.setDamageValue(0);
    }

    public int getMaxDamage(ItemStack stack)
    {
        return getUseDuration(stack);
    }

    public boolean isBarVisible(ItemStack stack)
    {
        return stack.getDamageValue() != 0;
    }
}
