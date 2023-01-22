# 召唤一个盔甲架
summon minecraft:armor_stand ~ ~ ~ {Invisible:true, Glowing:true, Tags:["supply"]}
execute as @e[tag=ring] run data modify entity @s Glowing set value true