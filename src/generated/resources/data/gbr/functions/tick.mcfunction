# 计分板赋值
execute as @a store result score @s height run data get entity @s Pos[1]

# 游戏内&游戏外
execute if score game_start global matches 0 run function gbr:tick/out_game
execute if score game_start global matches 1 run function gbr:tick/in_game