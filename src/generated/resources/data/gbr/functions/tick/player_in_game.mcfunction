execute as @s[tag=jumping] if score @s height matches 0..100 at @s run function gbr:game/jumping
execute if score @s absorption matches 1..16 run function gbr:skill/shield
# execute at @s unless entity @e[tag=fly, dx=2, dz=2, dy=-10] run effect clear @s levitation
execute if score @s death matches 1..2147483647 run function gbr:game/death
execute if data entity @s SelectedItem if entity @s[nbt=!{SelectedItem:{id:"minecraft:ghast_tear"}}] run function gbr:skill/void_cancel