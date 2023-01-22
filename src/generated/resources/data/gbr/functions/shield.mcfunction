effect clear @s absorption
effect clear @s regeneration
execute if score @s absorption matches 1..4 run function gbr:gold_apple
execute if score @s absorption matches 5..16 run function gbr:enchanted_gold_apple