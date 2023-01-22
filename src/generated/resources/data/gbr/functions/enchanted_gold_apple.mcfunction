effect clear @s fire_resistance
effect clear @s resistance
execute if score @s maxhealth matches 20 run attribute @s generic.max_health base set 28
execute if score @s maxhealth matches 24 run attribute @s generic.max_health base set 32
execute if score @s maxhealth matches 28 run attribute @s generic.max_health base set 36
execute if score @s maxhealth matches 32 run attribute @s generic.max_health base set 40
execute if score @s maxhealth matches 36 run attribute @s generic.max_health base set 40
effect give @s instant_health 1 2