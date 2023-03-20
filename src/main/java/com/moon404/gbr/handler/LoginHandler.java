package com.moon404.gbr.handler;

import com.moon404.gbr.ChooseCommand;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.Scoreboard;
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

        Scoreboard scoreboard = player.level.getScoreboard();
        if (ChooseCommand.getScore(scoreboard, "game_start", "global") == 0)
        {
            scoreboard.removePlayerFromTeam(player.getScoreboardName());
            player.sendSystemMessage(Component.literal("您将在下一场游戏中观战，使用/choose指令来选择队伍"));
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, Integer.MAX_VALUE));
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, Integer.MAX_VALUE));
        }
    }
}
