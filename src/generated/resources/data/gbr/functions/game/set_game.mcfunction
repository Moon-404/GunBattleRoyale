scoreboard players reset * death
scoreboard players reset * kills
execute as @a[gamemode=adventure, team=!] run scoreboard players set @s ob 0
execute as @a[gamemode=adventure, team=] run scoreboard players set @s ob 1
execute as @a[gamemode=adventure] unless score @s ob matches 1 run scoreboard players set @s death 0
effect clear @a[gamemode=adventure]

execute as @e[tag=jump] run teleport @a[gamemode=adventure] @s
execute at @e[tag=jump] run summon gbr:shield_bottle ~ ~ ~
gamemode spectator @a[gamemode=adventure, scores={ob=1}]
scoreboard players set @a[gamemode=spectator] death -1

schedule clear gbr:game/set_supply
execute as @e[tag=supply, tag=!chest] at @s run function gbr:game/set_chest

execute as @e[tag=ring, sort=random, limit=1] run data modify entity @s Tags append value "center"
execute at @e[tag=center] run function gbr:game/set_center
worldborder set 601 0
worldborder set 1 300
execute as @a[gamemode=adventure] run function gbr:game/set_game_player

# game_team_norank 为不可以获得排名分的队伍数量
scoreboard players operation game_team_half global = total team_alive
scoreboard players operation game_team_half global /= game_two global
scoreboard players operation game_team_norank global = total team_alive
scoreboard players operation game_team_norank global -= game_team_half global
scoreboard players set game_rank global 0
execute as @e[tag=team, scores={team_alive=1..3}] run tag @s add exist

function gbr:team/setid

scoreboard players set game_tick global 0
scoreboard players set game_start global 1
scoreboard players set game_airdrop_count global 0
schedule function gbr:game/remove_afk 30s
schedule function gbr:game/judge 1s
scoreboard objectives setdisplay sidebar nothing