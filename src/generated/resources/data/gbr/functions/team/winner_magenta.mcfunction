tellraw @a [{"selector": "@a[team=magenta]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players add @a[team=magenta] stat_win 1
scoreboard players operation @a[team=magenta] rank += game_rank global