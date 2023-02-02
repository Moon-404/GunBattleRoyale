execute at @e[tag=supply] run setblock ~ ~ ~ air
execute at @e[tag=airdrop] run setblock ~ ~0.5 ~ air
kill @e[tag=airdrop]
kill @e[type=item]
execute at @e[tag=ring] run function gbr:game/reset_center
execute as @e[tag=center] run tag @s remove center