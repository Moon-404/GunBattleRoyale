particle poof ~-1 ~ ~-1 0 1 0 1 0
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=0] levitation 1 9
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=100] levitation 1 0
scoreboard players add @s global 1
execute if score @s global matches 100..2147483647 run kill @s