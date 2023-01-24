scoreboard players set game_airdrop_success global 1
execute at @s run tp ~ 120 ~
effect give @s slow_falling 30
title @a title "正在运送空投补给"
data modify entity @s Tags set value ["airdrop", "dropping"]