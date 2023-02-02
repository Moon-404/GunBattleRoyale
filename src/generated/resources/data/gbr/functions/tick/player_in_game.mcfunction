execute if score @s height matches 40..49 run function gbr:game/jump
execute if score @s absorption matches 1..16 run function gbr:skill/shield
# execute at @s unless entity @e[tag=fly, dx=2, dz=2, dy=-10] run effect clear @s levitation
execute if score @s death matches 1..2147483647 run function gbr:game/death
