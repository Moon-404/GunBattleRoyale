package com.moon404.gbr;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.TeamArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;

public class ChooseCommand
{
    public static int getScore(Scoreboard scoreboard, String player, String objective)
    {
        return scoreboard.getOrCreatePlayerScore(player, scoreboard.getOrCreateObjective(objective)).getScore();
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("choose")
        .then(Commands.argument("choose", TeamArgument.team()).executes((command) ->
        {
            Player player = command.getSource().getPlayer();
            Scoreboard scoreboard = player.level.getScoreboard();
            if (getScore(scoreboard, "game_start", "global") == 0)
            {
                PlayerTeam team = TeamArgument.getTeam(command, "choose");
                if (team.getName().equals("test"))
                {
                    player.sendSystemMessage(Component.literal("无法使用此指令进入测试队伍").withStyle(Style.EMPTY.withColor(0xFF0000)));
                    return Command.SINGLE_SUCCESS;
                }
                scoreboard.addPlayerToTeam(player.getScoreboardName(), team);
                player.sendSystemMessage(Component.literal("切换队伍成功，当前队伍：").append(team.getFormattedDisplayName()));
                player.removeEffect(MobEffects.GLOWING);
                player.removeEffect(MobEffects.INVISIBILITY);
            }
            else
            {
                player.sendSystemMessage(Component.literal("切换队伍失败，游戏已开始").withStyle(Style.EMPTY.withColor(0xFF0000)));
            }
            return Command.SINGLE_SUCCESS;
        }))
        .then(Commands.literal("observe").executes((command) ->
        {
            Player player = command.getSource().getPlayer();
            Scoreboard scoreboard = player.level.getScoreboard();
            if (getScore(scoreboard, "game_start", "global") == 0)
            {
                scoreboard.removePlayerFromTeam(player.getScoreboardName());
                player.sendSystemMessage(Component.literal("您将在下一场游戏中观战"));
                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, Integer.MAX_VALUE));
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, Integer.MAX_VALUE));
            }
            else
            {
                player.sendSystemMessage(Component.literal("进入观战失败，游戏已开始").withStyle(Style.EMPTY.withColor(0xFF0000)));
            }
            return Command.SINGLE_SUCCESS;
        }));
        dispatcher.register(builder);
    }
}
