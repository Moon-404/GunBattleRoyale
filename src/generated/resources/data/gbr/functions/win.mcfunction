title @a title [{"selector": "@p[gamemode=adventure, scores={death=0}]"}, " 是胜利者！"]
scoreboard players add @a[gamemode=adventure, scores={death=0}] wins 1
execute at @e[tag=supply] run setblock ~ ~ ~ air
kill @e[type=item]
clear @a[gamemode=adventure]
effect clear @a[gamemode=adventure]
worldborder set 114514
execute at @e[tag=center] run function gbr:reset_center
execute as @e[tag=center] run data modify entity @s Tags set value ["ring"]
scoreboard players set game_start global 0