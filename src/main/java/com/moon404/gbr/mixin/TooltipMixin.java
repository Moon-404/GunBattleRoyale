package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.mrcrayfish.guns.item.attachment.impl.Attachment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

@Mixin(Attachment.class)
public class TooltipMixin
{
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    @Overwrite(remap = false)
    public static void addInformationEvent(ItemTooltipEvent event)
    {
        Item item = event.getItemStack().getItem();
        if (item == GunBattleRoyaleItems.GRIP.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 小幅提高开镜速度"));
            toolTip.add(Component.literal("- 小幅降低后座偏移角度"));
            return;
        }
        if (item == GunBattleRoyaleItems.ADVANCED_GRIP.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 大幅提高开镜速度"));
            toolTip.add(Component.literal("- 大幅降低后座偏移角度"));
            return;
        }
        if (item == GunBattleRoyaleItems.STOCK.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 小幅降低后座力"));
            toolTip.add(Component.literal("- 小幅提高低射速武器的射速"));
            return;
        }
        if (item == GunBattleRoyaleItems.ADVANCED_STOCK.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 大幅降低后座力"));
            toolTip.add(Component.literal("- 大幅提高低射速武器的射速"));
            return;
        }
        if (item == GunBattleRoyaleItems.STABILIZER.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 小幅降低散布"));
            toolTip.add(Component.literal("- 小幅降低子弹下坠"));
            return;
        }
        
        if (item == GunBattleRoyaleItems.ADVANCED_STABILIZER.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 大幅降低散布"));
            toolTip.add(Component.literal("- 大幅降低子弹下坠"));
            return;
        }
    }
}
