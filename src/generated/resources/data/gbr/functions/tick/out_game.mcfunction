gamemode adventure @a[gamemode=spectator]
execute as @a[gamemode=adventure] run function gbr:tick/player_out_game
execute as @e[tag=start] run teleport @a[gamemode=adventure, scores={height=-2147483648..160}] @s
execute as @a[scores={maxhealth=21..40}] run function gbr:game/reset_health