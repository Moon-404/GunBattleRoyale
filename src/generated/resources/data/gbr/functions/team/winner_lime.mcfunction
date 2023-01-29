tellraw @a [{"selector": "@a[team=lime]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players add @a[team=lime] stat_win 1
scoreboard players operation @a[team=lime] rank += game_rank global