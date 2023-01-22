execute as @a store result score @s height run data get entity @s Pos[1]
execute as @a store result score @s absorption run data get entity @s AbsorptionAmount
execute as @a store result score @s maxhealth run attribute @s generic.max_health get
execute if score game_start global matches 0 run function gbr:tick_out_game
execute if score game_start global matches 1 run function gbr:tick_in_game
execute as @e[type=item, nbt={Item:{id:"minecraft:rabbit_foot"}}] at @s if data entity @s Thrower run function gbr:skill_run
execute as @e[type=item, nbt={Item:{id:"minecraft:ghast_tear"}}] at @s if data entity @s Thrower run function gbr:skill_void
execute as @e[type=item, nbt={Item:{id:"minecraft:glowstone_dust"}}] at @s if data entity @s Thrower run function gbr:skill_glow
execute as @e[type=item, nbt={Item:{id:"minecraft:phantom_membrane"}}] at @s if data entity @s Thrower run function gbr:skill_fly
execute at @e[tag=fly] run particle poof ~ ~ ~ 0 1 0 1 0
execute at @e[tag=fly] run effect give @a[gamemode=adventure, dy=0] levitation 1 9
execute at @e[tag=fly] run effect give @a[gamemode=adventure, dy=100] levitation 1 0
execute as @a[gamemode=adventure] at @s unless entity @e[tag=fly, dy=-10] run effect clear @s levitation
execute as @e[tag=fly] run scoreboard players add @s global 1
kill @e[tag=fly, scores={global=100..2147483647}]