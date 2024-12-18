scoreboard players reset * kill
scoreboard players set @s kill 1
scoreboard players add @s stat_kill 1

scoreboard players operation game_killer_teamid global = @s teamid
scoreboard players reset * assist
execute as @a if score @s teamid = game_killer_teamid global run scoreboard players set @s assist 1
