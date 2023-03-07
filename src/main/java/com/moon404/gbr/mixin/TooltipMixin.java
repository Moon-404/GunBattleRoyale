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
        if (item == GunBattleRoyaleItems.SHIELD_CELL.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.literal("恢复 4 点护盾"));
            toolTip.add(Component.literal("使用时间：1.5秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.SHIELD_BATTERY.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.literal("完全恢复护盾"));
            toolTip.add(Component.literal("使用时间：2.5秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.SYRINGE.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.literal("恢复 4 点生命"));
            toolTip.add(Component.literal("使用时间：2.5秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.MED_KIT.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.literal("完全恢复生命"));
            toolTip.add(Component.literal("使用时间：4.0秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.PHOENIX_KIT.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.literal("完全恢复护盾与生命"));
            toolTip.add(Component.literal("使用时间：5.0秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.SHIELD_BOOST.get())
        {
            List<Component> toolTip = event.getToolTip();
            toolTip.add(Component.literal("提供 30 护盾进化经验"));
            toolTip.add(Component.literal("使用此物品造成的护盾进化会恢复等量护盾值"));
            toolTip.add(Component.literal("使用时间：1.5秒"));
            return;
        }
    }
}
