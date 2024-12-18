scoreboard players reset * winner
execute as @e[tag=exist] run scoreboard players operation game_winner_teamid global = @s teamid
execute as @a if score @s teamid = game_winner_teamid global run scoreboard players set @s winner 1
tellraw @a [{"selector": "@a[scores={winner=1}]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players add @a[scores={winner=1}] stat_win 1
scoreboard players operation @a[scores={winner=1}] rank += game_rank global
tag @e[tag=exist] remove exist

team empty green
team empty yellow
team empty orange
team empty lime
team empty pink
team empty brown
team empty red
team empty blue
team empty black
team empty magenta
team empty purple
team empty cyan
kill @e[tag=testplayer]
team empty test

effect clear @a[gamemode=adventure]
clear @a[gamemode=adventure]
scoreboard players set game_start global 0
title @a title "游戏结束"
scoreboard players set game_waiting global 1
schedule function gbr:game/wait_finish 30s

execute as @e[tag=airdrop] at @s run function gbr:airdrop/reset
execute as @e[tag=supply] at @s run data modify block ~ ~ ~ Items set value []
execute at @e[tag=ring] run function gbr:game/reset_center

kill @e[type=corpse:corpse]
kill @e[type=item]

scoreboard objectives setdisplay sidebar rank
