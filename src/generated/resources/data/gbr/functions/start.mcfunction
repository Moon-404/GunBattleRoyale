scoreboard objectives remove death
scoreboard objectives add death deathCount
execute as @a[gamemode=adventure] unless score @s ob matches 1 run scoreboard players set @s death 0
scoreboard players set game_sum global 0
execute as @a[gamemode=adventure, scores={death=0}] run scoreboard players add game_sum global 1
execute if score game_sum global matches 2..2147483647 run function gbr:set_game
execute if score game_sum global matches 0..1 run title @a title "人数不足无法开始游戏"