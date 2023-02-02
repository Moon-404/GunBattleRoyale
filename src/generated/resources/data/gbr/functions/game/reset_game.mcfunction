execute at @e[tag=airdrop] run setblock ~ ~0.5 ~ air
kill @e[tag=airdrop]

schedule clear gbr:game/reset_supply
execute at @e[tag=chest] run function gbr:game/reset_chest

execute at @e[tag=ring] run function gbr:game/reset_center
execute as @e[tag=center] run tag @s remove center