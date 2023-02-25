scoreboard players set game_not_air_count global 0
execute unless block ~-1 ~ ~ air run scoreboard players add game_not_air_count global 1
execute unless block ~1 ~ ~ air run scoreboard players add game_not_air_count global 1
execute unless block ~ ~1 ~ air run scoreboard players add game_not_air_count global 1
execute unless block ~ ~-1 ~ air run scoreboard players add game_not_air_count global 1
execute unless block ~ ~ ~1 air run scoreboard players add game_not_air_count global 1
execute unless block ~ ~ ~-1 air run scoreboard players add game_not_air_count global 1
execute if score game_not_air_count global matches 2..6 run scoreboard players add game_bad_chests global 1
execute if score game_not_air_count global matches 2..6 run effect give @s glowing 3600