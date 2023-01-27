effect give @s regeneration 1 5
clear @s
execute if score @s ob matches 1 run effect give @s glowing 1
execute if score @s ob matches 1 run effect give @s invisibility 1
execute at @s run function gbr:game/choose_team