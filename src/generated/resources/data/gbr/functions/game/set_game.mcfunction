gamemode spectator @a[gamemode=adventure, scores={ob=1}]
scoreboard players set @a[gamemode=spectator] death -1
execute as @e[tag=jump] run teleport @a[gamemode=adventure] @s
execute at @e[tag=supply] run setblock ~ ~ ~ minecraft:chest{LootTable:"gbr:chests"}
execute as @e[tag=ring, sort=random, limit=1] run data modify entity @s Tags append value "center"
execute at @e[tag=center] run function gbr:game/set_center
worldborder set 301 0
worldborder set 1 300
item replace entity @a[gamemode=adventure] armor.chest with elytra 1
scoreboard players set game_tick global 0
scoreboard players set game_start global 1
scoreboard players set game_airdrop_count global 0
