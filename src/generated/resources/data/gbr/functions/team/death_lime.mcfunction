tellraw @a ["lime 已被清除，排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players operation @a[team=lime] rank += game_rank global
scoreboard players set lime team_exist 0
scoreboard players remove game_team_norank global 1
execute if score game_team_norank global matches -2147483648..0 run scoreboard players add game_rank global 1