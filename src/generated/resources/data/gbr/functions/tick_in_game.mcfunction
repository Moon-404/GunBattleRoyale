# 跳伞&苹果
execute as @a[gamemode=adventure, scores={height=35..45}] run function gbr:jump
execute as @a[gamemode=adventure, scores={absorption=1..16}] run function gbr:shield
# 技能
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
# 击杀分
execute as @a[scores={kills=1..2147483647}] run scoreboard players operation @s rank += @s kills
execute as @a[scores={kills=1..2147483647}] run scoreboard players reset @s kills
# 死亡判定
execute as @a[gamemode=adventure, scores={death=1..2147483647}] run function gbr:death
execute as @a[gamemode=spectator] unless score @s death matches -1 run function gbr:death
# 获胜判定
scoreboard players set game_sum global 0
execute as @a[gamemode=adventure, scores={death=0}] run scoreboard players add game_sum global 1
execute if score game_sum global matches 0..1 run function gbr:win