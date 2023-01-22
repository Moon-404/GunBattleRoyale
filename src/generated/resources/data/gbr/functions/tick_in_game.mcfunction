execute as @a[gamemode=adventure, scores={death=1..2147483647}] run function gbr:death
execute as @a[gamemode=spectator] unless score @s death matches -1 run function gbr:death
execute as @a[gamemode=adventure, scores={height=35..45}] run function gbr:jump
execute as @a[gamemode=adventure, scores={absorption=1..16}] run function gbr:shield
scoreboard players set game_sum global 0
execute as @a[gamemode=adventure, scores={death=0}] run scoreboard players add game_sum global 1
execute if score game_sum global matches 0..1 run function gbr:win