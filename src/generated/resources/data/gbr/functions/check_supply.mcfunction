scoreboard players set game_bad_chests global 0
scoreboard players set game_same_supply global 0
scoreboard players set game_total_supply global 0
execute as @e[tag=supply] run scoreboard players add game_total_supply global 1
execute as @e[tag=supply] at @s run function gbr:game/check_chest
execute as @e[tag=supply] at @s run function gbr:game/same_chest
tag @e[tag=checking] remove checking
tellraw @p ["共有 ", {"score": {"name": "game_total_supply", "objective": "global"}}, " 个物资点"]
tellraw @p ["共有 ", {"score": {"name": "game_bad_chests", "objective": "global"}}, " 个靠墙的物资点"]
tellraw @p ["共有 ", {"score": {"name": "game_same_supply", "objective": "global"}}, " 个重叠的物资点"]