tellraw @a [{"selector": "@a[team=cyan]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players add @a[team=cyan] stat_win 1
scoreboard players operation @a[team=cyan] rank += game_rank global