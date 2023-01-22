# 计分板赋值
execute as @a store result score @s height run data get entity @s Pos[1]
execute as @a store result score @s absorption run data get entity @s AbsorptionAmount
execute as @a store result score @s maxhealth run attribute @s generic.max_health get

# 尾迹
# execute at @a[gamemode=adventure, scores={height=40..200, rank=0..4}] run particle dust 0.55 0.27 0.07 2.0 ~ ~0.2 ~
execute at @a[gamemode=adventure, scores={height=40..200, rank=0..9}] run particle dust 0.75 0.75 0.75 2.0 ~ ~0.2 ~
execute at @a[gamemode=adventure, scores={height=40..200, rank=10..24}] run particle dust 1.00 0.86 0.00 2.0 ~ ~0.2 ~
execute at @a[gamemode=adventure, scores={height=40..200, rank=25..44}] run particle dust 0.50 0.86 1.00 2.0 ~ ~0.2 ~
execute at @a[gamemode=adventure, scores={height=40..200, rank=45..69}] run particle dust 0.00 0.45 0.85 2.0 ~ ~0.2 ~
execute at @a[gamemode=adventure, scores={height=40..200, rank=70..99}] run particle dust 0.69 0.05 0.79 2.0 ~ ~0.2 ~
execute at @a[gamemode=adventure, scores={height=40..200, rank=100..2147483647}] run particle dust 1.00 0.25 0.21 2.0 ~ ~0.2 ~

# 游戏内&游戏外
execute if score game_start global matches 0 run function gbr:tick_out_game
execute if score game_start global matches 1 run function gbr:tick_in_game