scoreboard players add game_airdrop_count global 1
execute at @e[tag=center] run summon armor_stand ~ ~ ~ {Invisible:true, Tags:["airdrop", "checking"]}
execute if score game_airdrop_count global matches 1 run execute at @e[tag=center] run spreadplayers ~ ~ 0 120 under 15 false @e[tag=checking]
execute if score game_airdrop_count global matches 2 run execute at @e[tag=center] run spreadplayers ~ ~ 0 60 under 15 false @e[tag=checking]
execute if score game_airdrop_count global matches 3 run execute at @e[tag=center] run spreadplayers ~ ~ 0 30 under 15 false @e[tag=checking]
execute as @e[tag=checking] at @s run function gbr:airdrop/set
title @a actionbar "正在运送空投补给"
