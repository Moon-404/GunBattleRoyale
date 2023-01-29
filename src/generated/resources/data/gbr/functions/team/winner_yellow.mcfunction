tellraw @a [{"selector": "@a[team=yellow]"}, " 是胜利者！"]
scoreboard players add @a[team=yellow] stat_win 1
scoreboard players operation @a[team=yellow] rank += game_rank global