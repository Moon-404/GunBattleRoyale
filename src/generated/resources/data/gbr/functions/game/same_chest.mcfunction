tag @s add checking
execute as @e[tag=supply, distance=0..1, tag=!checking] run scoreboard players add game_same_supply global 1
execute as @e[tag=supply, distance=0..1, tag=!checking] run effect give @s glowing 3600
tag @e[tag=supply, distance=0..1, tag=!checking] add checking