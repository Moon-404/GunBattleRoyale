tellraw @a [{"selector": "@a[team=red]"}, " 是胜利者！"]
scoreboard players add @a[team=red] stat_win 1
scoreboard players operation @a[team=red] rank += game_rank global