tellraw @s "您已阵亡，可以使用数字键1和2传送至玩家处观战"
scoreboard players set @s death -1
scoreboard players add @s stat_death 1
gamemode spectator @s
execute as @a[scores={kills=1..2147483647}] run function gbr:game/kill