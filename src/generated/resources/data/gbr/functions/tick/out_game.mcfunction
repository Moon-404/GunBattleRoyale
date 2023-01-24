gamemode adventure @a[gamemode=spectator]
effect give @a[gamemode=adventure] regeneration 1 5
execute as @e[tag=start] run teleport @a[gamemode=adventure, scores={height=0..160}] @s
execute as @a[scores={maxhealth=21..40}] run function gbr:game/reset_health