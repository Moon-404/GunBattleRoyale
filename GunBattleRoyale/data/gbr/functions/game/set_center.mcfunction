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
worldborder damage amount 1