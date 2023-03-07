effect give @s regeneration 1 5 true
experience set @s 0 levels
data modify entity @s AbsorptionAmount set value 0
clear @s
execute if score @s ob matches 1 run effect give @s glowing 1 0 true
execute if score @s ob matches 1 run effect give @s invisibility 1 0 true
execute at @s run function gbr:team/choose