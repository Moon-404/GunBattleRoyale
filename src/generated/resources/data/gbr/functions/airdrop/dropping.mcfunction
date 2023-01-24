execute at @s run particle campfire_signal_smoke ~ ~ ~ 0 0 0 0.5 0 force @a
execute if data entity @s {OnGround:true} at @s run function gbr:airdrop/land