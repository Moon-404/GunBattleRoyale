execute as @e[tag=chest, limit=20] at @s run function gbr:game/reset_chest
kill @e[type=item]
schedule function gbr:game/reset_supply 1s