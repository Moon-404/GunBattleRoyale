scoreboard players remove @s rank 2
tellraw @a [{"selector": "@s"}, " 对队友造成了最多的伤害，惩罚分：2"]
scoreboard players remove @s kills 1
execute if score @s kills matches 1..2147483647 run function gbr:team/kill_team