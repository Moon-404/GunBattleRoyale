package com.moon404.gbr.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.moon404.gbr.message.UpdateItemStackMessage;
import com.moon404.gbr.struct.ItemStackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class R2R5Item extends Item
{
    public static final int MAX_ENERGY = 400;
    public static final int DASH_ENERGY = 10;
    public static final int LAUNCH_ENERGY = 50;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    private final Multimap<Attribute, AttributeModifier> burstModifiers;
    private static final AttributeModifier BASE_DAMAGE = new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 5.0D, Operation.ADDITION);
    private static final AttributeModifier BASE_ATTACK_SPEED = new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.4D, Operation.ADDITION);
    private static final AttributeModifier BURST_DAMAGE = new AttributeModifier("r2r5_burst_damage", 0.5D, Operation.MULTIPLY_TOTAL);
    private static final AttributeModifier BURST_MOVEMENT_SPEED = new AttributeModifier("r2r5_burst_speed", 0.2D, Operation.MULTIPLY_TOTAL);
    private static boolean pressing = false;

    public R2R5Item(Properties pProperties)
    {
        super(pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, BASE_DAMAGE);
        builder.put(Attributes.ATTACK_SPEED, BASE_ATTACK_SPEED);
        this.defaultModifiers = builder.build();
        builder.put(Attributes.ATTACK_DAMAGE, BURST_DAMAGE);
        builder.put(Attributes.MOVEMENT_SPEED, BURST_MOVEMENT_SPEED);
        this.burstModifiers = builder.build();
    }

    public static int getEnergy(ItemStack itemStack)
    {
        return itemStack.getOrCreateTag().getInt("energy");
    }

    public static void setEnergy(ItemStack itemStack, int amount)
    {
        if (amount <= 0)
        {
            setBurst(itemStack, false);
            amount = 0;
        }
        if (amount > MAX_ENERGY) amount = MAX_ENERGY;
        if (bursting(itemStack) && getEnergy(itemStack) < amount) return;
        itemStack.getOrCreateTag().putInt("energy", amount);
    }

    public static void setBurst(ItemStack itemStack, boolean bool)
    {
        itemStack.getOrCreateTag().putBoolean("bursting", bool);
    }

    public static boolean bursting(ItemStack itemStack)
    {
        return itemStack.getOrCreateTag().getBoolean("bursting");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack itemStack)
    {
        if (slot != EquipmentSlot.MAINHAND) return ImmutableMultimap.of();
        if (bursting(itemStack)) return this.burstModifiers;
        return this.defaultModifiers;
    }
    
    @Override
    public UseAnim getUseAnimation(ItemStack itemStack)
    {
        return UseAnim.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack itemStack)
    {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public int getBarColor(ItemStack itemStack)
    {
        if (bursting(itemStack)) return 0xAA0000;
        if (getEnergy(itemStack) == MAX_ENERGY) return 0xFFAA00;
        return 0x00AA00;
    }

    @Override
    public boolean isFoil(ItemStack itemStack)
    {
        return bursting(itemStack);
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack)
    {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack itemStack)
    {
        return 13 * getEnergy(itemStack) / MAX_ENERGY;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int remainingUseDuration)
    {
        if (!level.isClientSide) return;
        if (livingEntity instanceof LocalPlayer player)
        {
            if (Minecraft.getInstance().options.keyAttack.isDown())
            {
                if (!pressing)
                {
                    pressing = true;
                    if (getEnergy(itemStack) == MAX_ENERGY)
                    {
                        ItemStackInfo message = new ItemStackInfo();
                        message.slot = player.getInventory().findSlotMatchingItem(itemStack);
                        message.action = 1;
                        UpdateItemStackMessage.INSTANCE.sendToServer(message);
                    }
                    else if (bursting(itemStack))
                    {
                        ItemStackInfo message = new ItemStackInfo();
                        message.slot = player.getInventory().findSlotMatchingItem(itemStack);
                        message.action = 2;
                        UpdateItemStackMessage.INSTANCE.sendToServer(message);
                    }
                }
            }
            else
            {
                pressing = false;
            }
        }
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slotId, boolean isSelected)
    {
        if (!bursting(itemStack)) return;
        setEnergy(itemStack, getEnergy(itemStack) - 1);
    }
}
