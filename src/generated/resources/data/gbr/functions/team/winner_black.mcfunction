tellraw @a [{"selector": "@a[team=black]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players add @a[team=black] stat_win 1
scoreboard players operation @a[team=black] rank += game_rank global