scoreboard players add game_airdrop_retry global 1
execute at @e[tag=center] run summon armor_stand ~ ~ ~ {Invisible:true, Tags:["airdrop", "checking"]}
execute at @e[tag=center] run spreadplayers ~ ~ 0 150 false @e[tag=checking]
schedule function gbr:airdrop/check1 5t