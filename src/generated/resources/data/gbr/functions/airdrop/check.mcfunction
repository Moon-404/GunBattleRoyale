execute store result score @s height run data get entity @s Pos[1]
execute unless score @s height matches 0..12 run kill @s
execute if score @s height matches 0..12 run function gbr:airdrop/success