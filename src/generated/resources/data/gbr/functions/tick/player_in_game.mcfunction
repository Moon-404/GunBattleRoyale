execute as @s[tag=jumping] if score @s height matches 0..100 at @s run function gbr:game/jumping
# execute at @s unless entity @e[tag=fly, dx=2, dz=2, dy=-10] run effect clear @s levitation
execute if score @s death matches 1..2147483647 run function gbr:game/death
