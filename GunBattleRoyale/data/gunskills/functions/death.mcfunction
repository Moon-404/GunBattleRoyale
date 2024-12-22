scoreboard players add @a[scores={kill=1}] rank 1
scoreboard players add @a[scores={assist=1}] rank 1
execute if entity @a[scores={kill=1}] run tellraw @a [{"selector": "@a[scores={kill=1}]"}, "击杀", {"selector": "@s"}, "获得2分，队友获得1分"]
tellraw @s "您已阵亡，可以使用数字键1和2传送至玩家处观战"
scoreboard players add @s stat_death 1
gamemode spectator @s
function gbr:game/judge
scoreboard players reset * kill
scoreboard players reset * assist
