execute as @e[tag=airdrop] at @s run function gbr:airdrop/reset

schedule clear gbr:game/reset_supply
execute as @e[tag=chest] at @s run function gbr:game/reset_chest

execute at @e[tag=ring] run function gbr:game/reset_center
execute as @e[tag=center] run tag @s remove center

kill @e[type=corpse:corpse]

kill @e[type=item]