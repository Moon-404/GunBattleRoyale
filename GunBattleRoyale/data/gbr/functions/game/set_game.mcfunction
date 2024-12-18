effect clear @a[gamemode=adventure]
gamemode spectator @a[gamemode=adventure, team=]

scoreboard players reset game_player_count global
execute as @a[gamemode=adventure] run function gbr:game/set_game_player
execute as @e[tag=jump] run teleport @a[gamemode=adventure] @s

execute as @e[tag=supply] at @s run loot insert ~ ~ ~ loot gbr:chests
execute as @e[tag=ring, sort=random, limit=1] at @s run function gbr:game/set_center

# game_team_norank 为不可以获得排名分的队伍数量
scoreboard players operation game_team_half global = total team_alive
scoreboard players operation game_team_half global /= game_two global
scoreboard players operation game_team_norank global = total team_alive
scoreboard players operation game_team_norank global -= game_team_half global
scoreboard players set game_rank global 0
execute as @e[tag=team, scores={team_alive=1..2147483647}] run tag @s add exist

function gbr:team/setid

scoreboard players set game_tick global 0
scoreboard players set game_start global 1
scoreboard players set game_airdrop_count global 0
schedule function gbr:game/remove_afk 30s
scoreboard objectives setdisplay sidebar nothing
