scoreboard players add @s stat_kill 1
scoreboard players add @s rank 2
tellraw @a [{"selector": "@s"}, " 在本次击杀中造成了最多的伤害，击杀分：2"]
scoreboard players remove @s kills 1
execute if score @s kills matches 1..2147483647 run function gbr:game/kill