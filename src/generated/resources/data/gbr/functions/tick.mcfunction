# 计分板赋值
execute as @a store result score @s height run data get entity @s Pos[1]
execute as @a store result score @s absorption run data get entity @s AbsorptionAmount
execute as @a store result score @s maxhealth run attribute @s generic.max_health get

# 尾迹
execute as @a[gamemode=adventure, scores={height=40..200}] at @s run function gbr:tick/rank_particle

# 游戏内&游戏外
execute if score game_start global matches 0 run function gbr:tick/out_game
execute if score game_start global matches 1 run function gbr:tick/in_game