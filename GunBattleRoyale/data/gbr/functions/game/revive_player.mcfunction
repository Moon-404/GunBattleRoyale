# at 使用复活道具玩家的地点 @s 被复活玩家
teleport ~ ~ ~
experience set @s 1 levels
experience set @s 0 points
data modify entity @s AbsorptionAmount set value 8
gamemode adventure @s
loot give @s loot gbr:revive
