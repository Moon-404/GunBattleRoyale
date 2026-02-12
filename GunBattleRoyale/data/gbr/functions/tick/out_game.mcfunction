experience set @a[gamemode=adventure] 0 levels
execute as @a[gamemode=adventure] run data modify entity @s Health set value 20
execute as @a[gamemode=adventure] run data modify entity @s AbsorptionAmount set value 0
execute as @e[tag=start] run teleport @a[gamemode=adventure, scores={height=-2147483648..160}] @s
execute as @e[tag=start] run teleport @a[gamemode=adventure, scores={height=170..2147483647}] @s

execute if score game_tick global matches 100 run function gbr:mapgen/terrain
execute if score game_tick global matches 350 run function gbr:mapgen/structure
execute if score game_tick global matches 500 run function gbr:mapgen/chest
