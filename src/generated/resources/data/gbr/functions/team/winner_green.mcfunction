tellraw @a [{"selector": "@a[team=green]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players add @a[team=green] stat_win 1
scoreboard players operation @a[team=green] rank += game_rank global