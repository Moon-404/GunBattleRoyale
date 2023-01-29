tellraw @a [{"selector": "@a[team=orange]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players add @a[team=orange] stat_win 1
scoreboard players operation @a[team=orange] rank += game_rank global