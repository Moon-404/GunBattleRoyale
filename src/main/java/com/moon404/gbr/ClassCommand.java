package com.moon404.gbr;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;

public class ClassCommand
{
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("class");
        for (ClassType type : ClassType.values())
        {
            builder.then(Commands.literal(type.getName()).executes((command) ->
            {
                Player player = (Player) command.getSource().getPlayer();
                Scoreboard scoreboard = player.level.getScoreboard();
                Objective objective = scoreboard.getOrCreateObjective("global");
                if (scoreboard.getOrCreatePlayerScore("game_start", objective).getScore() == 0)
                {
                    ClassType.setClass(player, type);
                    player.sendSystemMessage(Component.literal("更换职业成功，当前职业：").append(type.getDisplay()));
                }
                else
                {
                    player.sendSystemMessage(Component.literal("更换职业失败，游戏已开始").withStyle(Style.EMPTY.withColor(0xFF0000)));
                }
                return Command.SINGLE_SUCCESS;
            }));
        }
        dispatcher.register(builder);
    }
}
