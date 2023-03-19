package com.moon404.gbr.struct;

import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;

public enum ClassType
{
    ATTACK("attack", "进攻", 0xAA0000, "：使用多种进攻技能正面突破对手"),
    ROGUE("rogue", "游侠", 0x00AAAA, "：使用多种转移技能从侧面干扰对手"),
    SUPPORT("support", "辅助", 0x00AA00, "：为团队提供净化、恢复、强化"),
    SCOUT("scout", "侦察", 0xFFAA00, "：为团队提供周围敌人的信息");

    private final String name;
    private final MutableComponent display;
    private final MutableComponent helper;

    private ClassType(String name, String display, int color, String helper)
    {
        this.name = name;
        Style style = Style.EMPTY.withColor(color);
        this.display = Component.literal(display).withStyle(style);
        String command = "/class " + name;
        style = style.withUnderlined(true);
        style = style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        style = style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal(command)));
        this.helper = this.display.copy().append(helper).withStyle(style);
    }

    public String getName()
    {
        return this.name;
    }

    public MutableComponent getDisplay()
    {
        return this.display;
    }
    
    public MutableComponent getHelper()
    {
        return this.helper;
    }

    public static void removeClass(Player player)
    {
        for (ClassType type : values())
        {
            player.removeTag(type.getName());
        }
    }

    public static void setClass(Player player, ClassType type)
    {
        removeClass(player);
        player.addTag(type.getName());
    }

    public static ClassType getClass(Player player)
    {
        for (ClassType type : values())
        {
            if (player.getTags().contains(type.getName()))
            {
                return type;
            }
        }
        return ClassType.ROGUE;
    }
}
