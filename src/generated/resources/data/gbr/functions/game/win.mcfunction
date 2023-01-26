execute as @p[gamemode=adventure, scores={death=0}] run function gbr:game/winner
execute at @e[tag=supply] run setblock ~ ~ ~ air
execute at @e[tag=airdrop] run setblock ~ ~ ~ air
kill @e[tag=airdrop]
kill @e[type=item]
effect clear @a[gamemode=adventure]
worldborder set 114514
execute at @e[tag=center] run function gbr:game/reset_center
execute as @e[tag=center] run data modify entity @s Tags set value ["ring"]
scoreboard players set game_start global 0