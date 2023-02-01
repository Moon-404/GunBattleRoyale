execute as @e[tag=jump] run teleport @a[gamemode=adventure] @s
gamemode spectator @a[gamemode=adventure, scores={ob=1}]
scoreboard players set @a[gamemode=spectator] death -1
execute at @e[tag=supply] run setblock ~ ~ ~ minecraft:chest{LootTable:"gbr:chests"}
execute as @e[tag=ring, sort=random, limit=1] run data modify entity @s Tags append value "center"
execute at @e[tag=center] run function gbr:game/set_center
worldborder set 601 0
worldborder set 1 300
item replace entity @a[gamemode=adventure] armor.chest with elytra{Enchantments:[{id:"binding_curse", lvl:1}]} 1
execute as @a[gamemode=adventure] run function gbr:game/fee

# game_team_norank 为不可以获得排名分的队伍数量
scoreboard players operation game_team_half global = total team_alive
scoreboard players operation game_team_half global /= game_two global
scoreboard players operation game_team_norank global = total team_alive
scoreboard players operation game_team_norank global -= game_team_half global
scoreboard players set game_rank global 0
execute as @e[tag=team, scores={team_alive=1..3}] run tag @s add exist

scoreboard players set @a[gamemode=adventure, team=green] teamid 1
scoreboard players set @a[gamemode=adventure, team=yellow] teamid 2
scoreboard players set @a[gamemode=adventure, team=orange] teamid 3
scoreboard players set @a[gamemode=adventure, team=lime] teamid 4
scoreboard players set @a[gamemode=adventure, team=pink] teamid 5
scoreboard players set @a[gamemode=adventure, team=brown] teamid 6
scoreboard players set @a[gamemode=adventure, team=red] teamid 7
scoreboard players set @a[gamemode=adventure, team=blue] teamid 8
scoreboard players set @a[gamemode=adventure, team=black] teamid 9
scoreboard players set @a[gamemode=adventure, team=magenta] teamid 10
scoreboard players set @a[gamemode=adventure, team=purple] teamid 11
scoreboard players set @a[gamemode=adventure, team=cyan] teamid 12

scoreboard players set game_tick global 0
scoreboard players set game_start global 1
scoreboard players set game_airdrop_count global 0
scoreboard players add @a[gamemode=adventure] stat_total 1
schedule function gbr:game/remove_afk 30s
schedule function gbr:game/judge 1s
scoreboard objectives setdisplay sidebar team_alive