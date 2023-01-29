tellraw @a [{"selector": "@a[team=purple]"}, " 是胜利者！"]
scoreboard players add @a[team=purple] stat_win 1
scoreboard players operation @a[team=purple] rank += game_rank global