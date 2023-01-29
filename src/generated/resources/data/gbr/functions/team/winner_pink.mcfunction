tellraw @a [{"selector": "@a[team=pink]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players add @a[team=pink] stat_win 1
scoreboard players operation @a[team=pink] rank += game_rank global