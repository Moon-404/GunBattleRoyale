particle poof ~-1 ~ ~-1 0 1 0 1 0
effect clear @a levitation
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=0] levitation 1 9
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=1] levitation 1 8
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=2] levitation 1 7
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=3] levitation 1 6
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=4] levitation 1 5
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=5] levitation 1 4
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=6] levitation 1 3
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=7] levitation 1 2
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=8] levitation 1 1
effect give @a[gamemode=adventure, dx=-2, dz=-2, dy=9] levitation 1 0
scoreboard players add @s global 1
execute if score @s global matches 100..2147483647 run effect clear @a levitation
execute if score @s global matches 100..2147483647 run kill @s