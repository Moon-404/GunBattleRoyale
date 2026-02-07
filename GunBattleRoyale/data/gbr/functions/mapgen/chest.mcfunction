execute at @e[type=armor_stand, tag=supply] run summon marker ~ ~ ~ {Tags:["supply"]}
kill @e[type=armor_stand, tag=supply]
execute at @e[tag=supply] unless block ~ ~ ~ chest run setblock ~ ~ ~ chest
execute as @e[tag=supply] at @s run loot insert ~ ~ ~ loot gbr:chests
kill @e[type=item]
