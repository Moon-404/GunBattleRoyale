execute if score @s rank matches 10..24 run scoreboard players set @s fee 1
execute if score @s rank matches 25..44 run scoreboard players set @s fee 2
execute if score @s rank matches 45..69 run scoreboard players set @s fee 3
execute if score @s rank matches 70..99 run scoreboard players set @s fee 4
execute if score @s rank matches 100..2147483647 run scoreboard players operation @s fee = @s rank
execute if score @s rank matches 100..2147483647 run scoreboard players remove @s fee 100
execute if score @s rank matches 100..2147483647 run scoreboard players operation @s fee /= game_fee_interval global
execute if score @s rank matches 100..2147483647 run scoreboard players add @s fee 5
scoreboard players operation @s rank -= @s fee
tellraw @s ["游戏开始，扣除了入场费：", {"score": {"name": "%s", "objective": "fee"}}]
