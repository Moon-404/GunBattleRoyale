tellraw @a [{"selector": "@a[team=blue]"}, " 是胜利者！"]
scoreboard players add @a[team=blue] stat_win 1
scoreboard players operation @a[team=blue] rank += game_rank global