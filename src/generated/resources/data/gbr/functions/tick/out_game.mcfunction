gamemode adventure @a[gamemode=spectator]
execute as @a[gamemode=adventure] run function gbr:tick/player_out_game
execute as @e[tag=start] run teleport @a[gamemode=adventure, scores={height=-2147483648..160}] @s
execute as @e[tag=start] run teleport @a[gamemode=adventure, scores={height=165..2147483647}] @s
