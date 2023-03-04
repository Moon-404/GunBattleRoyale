scoreboard players set game_airdrop_success global 0
scoreboard players set game_airdrop_retry global 0
scoreboard players add game_airdrop_count global 1
execute if score game_airdrop_count global matches 1 run function gbr:airdrop/spread1
execute if score game_airdrop_count global matches 2 run function gbr:airdrop/spread1
execute if score game_airdrop_count global matches 3 run function gbr:airdrop/spread2