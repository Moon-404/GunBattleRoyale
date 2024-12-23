clear @s
item replace entity @s armor.chest with elytra{Enchantments:[{id:"binding_curse", lvl:1}, {id:"vanishing_curse", lvl:1}]} 1
tag @s add jumping
experience set @s 1 levels
experience set @s 0 points
data modify entity @s AbsorptionAmount set value 8
scoreboard players add @s stat_total 1
scoreboard players add game_player_count global 1

scoreboard players set @s fee 0
execute if score @s rank matches 10..29 run scoreboard players set @s fee 1
execute if score @s rank matches 30..59 run scoreboard players set @s fee 2
execute if score @s rank matches 60..99 run scoreboard players set @s fee 3
execute if score @s rank matches 100..149 run scoreboard players set @s fee 4
execute if score @s rank matches 150..2147483647 run scoreboard players operation @s fee = @s rank
execute if score @s rank matches 150..2147483647 run scoreboard players remove @s fee 150
execute if score @s rank matches 150..2147483647 run scoreboard players operation @s fee /= game_fee_interval global
execute if score @s rank matches 150..2147483647 run scoreboard players add @s fee 5
scoreboard players operation @s rank -= @s fee
tellraw @s ["游戏开始，扣除了入场费：", {"score": {"name": "@s", "objective": "fee"}}]
