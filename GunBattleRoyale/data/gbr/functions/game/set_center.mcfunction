# 设置圈中心的信标
tag @s add center
setblock ~ ~ ~1 diamond_block
setblock ~1 ~ ~1 diamond_block
setblock ~-1 ~ ~1 diamond_block
setblock ~ ~ ~ diamond_block
setblock ~1 ~ ~ diamond_block
setblock ~-1 ~ ~ diamond_block
setblock ~ ~ ~-1 diamond_block
setblock ~1 ~ ~-1 diamond_block
setblock ~-1 ~ ~-1 diamond_block
setblock ~ ~1 ~ beacon
worldborder center ~ ~

worldborder set 481 0
worldborder set 1 240
execute if score game_player_count global matches 7..12 run worldborder set 601 0
execute if score game_player_count global matches 7..12 run worldborder set 1 300
execute if score game_player_count global matches 13..18 run worldborder set 721 0
execute if score game_player_count global matches 13..18 run worldborder set 1 360
execute if score game_player_count global matches 19..24 run worldborder set 841 0
execute if score game_player_count global matches 19..24 run worldborder set 1 420
execute if score game_player_count global matches 25..2147483647 run worldborder set 961 0
execute if score game_player_count global matches 25..2147483647 run worldborder set 1 480
