execute if score @s rank matches 10..24 run tellraw @s "游戏开始，扣除了1分入场费"
execute if score @s rank matches 10..24 run scoreboard players remove @s rank 1
execute if score @s rank matches 25..44 run tellraw @s "游戏开始，扣除了2分入场费"
execute if score @s rank matches 25..44 run scoreboard players remove @s rank 2
execute if score @s rank matches 45..69 run tellraw @s "游戏开始，扣除了3分入场费"
execute if score @s rank matches 45..69 run scoreboard players remove @s rank 3
execute if score @s rank matches 70..99 run tellraw @s "游戏开始，扣除了4分入场费"
execute if score @s rank matches 70..99 run scoreboard players remove @s rank 4
execute if score @s rank matches 100..2147483647 run tellraw @s "游戏开始，扣除了5分入场费"
execute if score @s rank matches 100..2147483647 run scoreboard players remove @s rank 5
