scoreboard players add @s stat_kill 1

scoreboard players operation game_killer_teamid global = @s teamid
scoreboard players reset * assist
execute as @a if score @s teamid = game_killer_teamid global run scoreboard players set @s assist 1
scoreboard players add @a[scores={assist=1}] rank 1
tellraw @a [{"selector": "@a[scores={assist=1}]"}, " 获得最高伤害击杀分：1"]

scoreboard players remove @s kills 1
execute if score @s kills matches 1..2147483647 run function gbr:team/kill_other