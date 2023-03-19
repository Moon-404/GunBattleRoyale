package com.moon404.gbr.handler;

import com.moon404.gbr.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LoginHandler
{
    @SubscribeEvent
    public static void onLogin(PlayerLoggedInEvent event)
    {
        Player player = event.getEntity();
        player.sendSystemMessage(Component.literal("欢迎使用枪战大逃杀MOD，当前职业：").append(ClassType.getClass(player).getDisplay()));
        player.sendSystemMessage(Component.literal("您可以点击下面的职业或使用/class指令来选择职业"));
        for (ClassType type : ClassType.values())
        {
            player.sendSystemMessage(type.getHelper());
        }
    }
}
