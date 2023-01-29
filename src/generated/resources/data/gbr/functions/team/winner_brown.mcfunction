tellraw @a [{"selector": "@a[team=brown]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players add @a[team=brown] stat_win 1
scoreboard players operation @a[team=brown] rank += game_rank global