# execute as @p[gamemode=adventure, scores={death=0}] run function gbr:game/winner

execute if score green team_alive matches 1..2147483647 run function gbr:team/winner_green
execute if score yellow team_alive matches 1..2147483647 run function gbr:team/winner_yellow
execute if score orange team_alive matches 1..2147483647 run function gbr:team/winner_orange
execute if score lime team_alive matches 1..2147483647 run function gbr:team/winner_lime
execute if score pink team_alive matches 1..2147483647 run function gbr:team/winner_pink
execute if score brown team_alive matches 1..2147483647 run function gbr:team/winner_brown
execute if score red team_alive matches 1..2147483647 run function gbr:team/winner_red
execute if score blue team_alive matches 1..2147483647 run function gbr:team/winner_blue
execute if score black team_alive matches 1..2147483647 run function gbr:team/winner_black
execute if score magenta team_alive matches 1..2147483647 run function gbr:team/winner_magenta
execute if score purple team_alive matches 1..2147483647 run function gbr:team/winner_purple
execute if score cyan team_alive matches 1..2147483647 run function gbr:team/winner_cyan

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
scoreboard players set game_start global 0
title @a title "游戏结束"
scoreboard players set game_waiting global 1
schedule function gbr:game/wait_finish 30s
scoreboard objectives setdisplay sidebar rank