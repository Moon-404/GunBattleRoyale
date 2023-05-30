scoreboard players set game_rank global 0
execute if score total team_alive matches 5..6 run scoreboard players set game_rank global 1
execute if score total team_alive matches 3..4 run scoreboard players set game_rank global 2
execute if score total team_alive matches 2 run scoreboard players set game_rank global 3
tellraw @a [{"selector": "@a[scores={eliminated=1}]"}, " 已被清除，排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players operation @a[scores={eliminated=1}] rank += game_rank global
execute as @e[tag=team] if score @s teamid = game_eliminated_teamid global run tag @s remove exist
execute if score total team_alive matches 2 run scoreboard players set game_rank global 5
