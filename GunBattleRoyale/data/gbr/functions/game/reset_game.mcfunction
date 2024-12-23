execute as @e[tag=airdrop] at @s run function gbr:airdrop/reset
execute as @e[tag=supply] at @s run data modify block ~ ~ ~ Items set value []
execute as @e[tag=ring] at @s run function gbr:game/reset_center

kill @e[type=corpse:corpse]
kill @e[type=item]
