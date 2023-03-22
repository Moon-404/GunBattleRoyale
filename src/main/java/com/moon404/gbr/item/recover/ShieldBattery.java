package com.moon404.gbr.item.recover;

import com.moon404.gbr.init.GunBattleRoyaleEffects;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ShieldBattery extends RecoverItem
{
    public ShieldBattery(Item.Properties properties)
    {
        super(properties);
    }

    public int getUseDuration(ItemStack stack)
    {
        return 50;
    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
    {
        if (!level.isClientSide && entity instanceof Player player)
        {
            if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get()))
            {
                player.displayClientMessage(Component.literal("被禁疗无法使用"), true);
                return stack;
            }
            float shield = player.experienceLevel * 4 + 4;
            player.setAbsorptionAmount(shield);
        }
        stack.setDamageValue(0);
        return this.isEdible() ? entity.eat(level, stack) : stack;
    }
}
