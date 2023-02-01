# execute as @p[gamemode=adventure, scores={death=0}] run function gbr:game/winner

scoreboard players reset * winner
execute as @a[gamemode=adventure] run scoreboard players operation game_winner_teamid global = @s teamid
execute as @a if score @s teamid = game_winner_teamid global run scoreboard players set @s winner 1
tellraw @a [{"selector": "@a[scores={winner=1}]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
scoreboard players add @a[scores={winner=1}] stat_win 1
scoreboard players operation @a[scores={winner=1}] rank += game_rank global

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

execute at @e[tag=supply] run setblock ~ ~ ~ air
execute at @e[tag=airdrop] run setblock ~ ~0.5 ~ air
kill @e[tag=airdrop]
kill @e[type=item]
effect clear @a[gamemode=adventure]
worldborder set 114514
execute at @e[tag=ring] run function gbr:game/reset_center
execute as @e[tag=center] run data modify entity @s Tags set value ["ring"]
schedule clear gbr:game/afk
scoreboard players set game_start global 0
title @a title "游戏结束"
scoreboard players set game_waiting global 1
schedule function gbr:game/wait_finish 30s
scoreboard objectives setdisplay sidebar rank