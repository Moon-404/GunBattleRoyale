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
        return UseAnim.BOW;
    }
}
