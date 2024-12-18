# 重设圈中心点的信标
tag @s remove center
setblock ~ ~ ~1 air
setblock ~1 ~ ~1 air
setblock ~-1 ~ ~1 air
setblock ~ ~ ~ air
setblock ~1 ~ ~ air
setblock ~-1 ~ ~ air
setblock ~ ~ ~-1 air
setblock ~1 ~ ~-1 air
setblock ~-1 ~ ~-1 air
setblock ~ ~1 ~ air
worldborder set 114514