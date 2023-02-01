# 召唤一个盔甲架
summon minecraft:armor_stand ~ ~ ~ {Invisible:true, Glowing:true, NoGravity:true, Tags:["test"]}
execute as @e[tag=ring] run data modify entity @s Glowing set value true
execute at @e[type=armor_stand, tag=supply] run summon marker ~ ~ ~ {Tags:["supply"]}
kill @e[type=armor_stand, tag=supply]