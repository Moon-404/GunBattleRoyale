scoreboard players set @s ob 1
gamemode spectator @s
title @s title "由于您太久未跳伞，您已被系统移入观战"
tellraw @a [{"selector": "@s"}, "由于太久未跳伞已被系统移入观战"]