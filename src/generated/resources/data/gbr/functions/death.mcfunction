# 生存模式玩家death!=0时触发
scoreboard players set @s death -1
gamemode spectator @s
execute at @p[gamemode=adventure, scores={death=0}] run spawnpoint @s ~ ~ ~
execute at @p[gamemode=adventure, scores={death=0}] run tp @s ~ ~ ~