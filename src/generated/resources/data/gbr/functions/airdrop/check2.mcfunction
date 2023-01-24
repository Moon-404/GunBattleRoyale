execute as @e[tag=checking] run function gbr:airdrop/check
execute if score game_airdrop_success global matches 0 if score game_airdrop_retry global matches 1..9 run function gbr:airdrop/spread2