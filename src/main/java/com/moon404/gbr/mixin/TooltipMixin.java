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
        List<Component> toolTip = event.getToolTip();
        if (item == GunBattleRoyaleItems.GRIP.get())
        {
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 小幅提高开镜速度"));
            toolTip.add(Component.literal("- 小幅降低后座偏移角度"));
            return;
        }
        if (item == GunBattleRoyaleItems.ADVANCED_GRIP.get())
        {
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 大幅提高开镜速度"));
            toolTip.add(Component.literal("- 大幅降低后座偏移角度"));
            return;
        }
        if (item == GunBattleRoyaleItems.STOCK.get())
        {
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 小幅降低后座力"));
            toolTip.add(Component.literal("- 小幅提高低射速武器的射速"));
            return;
        }
        if (item == GunBattleRoyaleItems.ADVANCED_STOCK.get())
        {
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 大幅降低后座力"));
            toolTip.add(Component.literal("- 大幅提高低射速武器的射速"));
            return;
        }
        if (item == GunBattleRoyaleItems.STABILIZER.get())
        {
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 小幅降低散布"));
            toolTip.add(Component.literal("- 小幅降低子弹下坠"));
            return;
        }
        if (item == GunBattleRoyaleItems.ADVANCED_STABILIZER.get())
        {
            toolTip.add(Component.translatable("perk.cgm.title").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            toolTip.add(Component.literal("- 大幅降低散布"));
            toolTip.add(Component.literal("- 大幅降低子弹下坠"));
            return;
        }
        if (item == GunBattleRoyaleItems.SHIELD_CELL.get())
        {
            toolTip.add(Component.literal("恢复 4 点护盾"));
            toolTip.add(Component.literal("使用时间：1.5秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.SHIELD_BATTERY.get())
        {
            toolTip.add(Component.literal("完全恢复护盾"));
            toolTip.add(Component.literal("使用时间：2.5秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.SYRINGE.get())
        {
            toolTip.add(Component.literal("恢复 4 点生命"));
            toolTip.add(Component.literal("使用时间：2.5秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.MED_KIT.get())
        {
            toolTip.add(Component.literal("完全恢复生命"));
            toolTip.add(Component.literal("使用时间：4.0秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.PHOENIX_KIT.get())
        {
            toolTip.add(Component.literal("完全恢复护盾与生命"));
            toolTip.add(Component.literal("使用时间：5.0秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.SHIELD_BOOST.get())
        {
            toolTip.add(Component.literal("提供 30 护盾进化经验"));
            toolTip.add(Component.literal("使用此物品造成的护盾进化会恢复等量护盾值"));
            toolTip.add(Component.literal("使用时间：1.5秒"));
            return;
        }
        if (item == GunBattleRoyaleItems.STIM.get())
        {
            toolTip.add(Component.literal("按 Q 扔出，立即生效"));
            toolTip.add(Component.literal("失去 4 点生命值（不会致死）"));
            toolTip.add(Component.literal("获得 5 秒速度III与跳跃II"));
        }
        if (item == GunBattleRoyaleItems.VOID.get())
        {
            toolTip.add(Component.literal("按 Q 扔出，立即生效"));
            toolTip.add(Component.literal("获得 5 秒隐身与失明"));
            toolTip.add(Component.literal("切换物品会提前结束效果"));
        }
        if (item == GunBattleRoyaleItems.GLOW.get())
        {
            toolTip.add(Component.literal("按 Q 扔出，立即生效"));
            toolTip.add(Component.literal("使附近的所有玩家发光（包括自己）"));
            toolTip.add(Component.literal("距离越远发光时间越短"));
        }
        if (item == GunBattleRoyaleItems.LIFT.get())
        {
            toolTip.add(Component.literal("按 Q 扔出，落地生效"));
            toolTip.add(Component.literal("生成一个持续 5 秒的重力电梯"));
            toolTip.add(Component.literal("可抬升最高 10 格，有摔落伤害"));
        }
        if (item == GunBattleRoyaleItems.HEALTH_BOTTLE.get())
        {
            toolTip.add(Component.literal("按 Q 扔出，落地生效"));
            toolTip.add(Component.literal("生成一个持续 4 秒的生命恢复区域"));
            toolTip.add(Component.literal("站在其中的玩家每秒恢复 2 点生命"));
        }
        if (item == GunBattleRoyaleItems.SHIELD_BOTTLE.get())
        {
            toolTip.add(Component.literal("按 Q 扔出，落地生效"));
            toolTip.add(Component.literal("生成一个持续 4 秒的护盾恢复区域"));
            toolTip.add(Component.literal("站在其中的玩家每秒恢复 2 点护盾"));
        }
        if (item == GunBattleRoyaleItems.PEARL.get())
        {
            toolTip.add(Component.literal("按 Q 扔出，落地生效"));
            toolTip.add(Component.literal("瞬移到目的地点，有明显的粒子效果"));
            toolTip.add(Component.literal("落地前玩家只能够缓慢移动"));
        }
        if (item == GunBattleRoyaleItems.TOTEM.get())
        {
            toolTip.add(Component.literal("按 Q 扔出，立即生效"));
            toolTip.add(Component.literal("记录自己的当前位置"));
            toolTip.add(Component.literal("5 秒内如果护盾被击碎则会传送回去"));
        }
        if (item == GunBattleRoyaleItems.FAST.get())
        {
            toolTip.add(Component.literal("放在副手，被动生效"));
            toolTip.add(Component.literal("受到伤害时获得 2 秒速度III"));
        }
        if (item == GunBattleRoyaleItems.IRE.get())
        {
            toolTip.add(Component.literal("放在副手，被动生效"));
            toolTip.add(Component.literal("造成伤害时令目标发光 3 秒"));
        }
    }
}
