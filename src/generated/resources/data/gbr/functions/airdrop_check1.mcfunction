execute as @e[tag=checking] store result score @s height run data get entity @s Pos[1]
execute as @e[tag=checking] unless score @s height matches 0..12 run kill @s
execute as @e[tag=checking] if score @s height matches 0..12 run function gbr:airdrop_success
execute as @e[tag=checking] if score game_airdrop_success global matches 0 if score game_airdrop_retry global matches 1..9 run function gbr:airdrop1