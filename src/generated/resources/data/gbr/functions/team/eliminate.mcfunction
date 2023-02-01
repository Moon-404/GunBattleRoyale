tellraw @a [{"selector": "@a[scores={eliminated=1}]"}, " 已被清除，排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players operation @a[scores={eliminated=1}] rank += game_rank global
execute as @e[tag=team] if score @s teamid = game_eliminated_teamid global run tag @s remove exist
scoreboard players remove game_team_norank global 1
execute if score game_team_norank global matches -2147483648..0 run scoreboard players add game_rank global 2