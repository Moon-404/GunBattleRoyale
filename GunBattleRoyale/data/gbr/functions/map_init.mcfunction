# 初始化数据
function gbr:init
# 放置平台
summon armor_stand 100.5 122 100.5 {Invisible:true, Glowing:true, NoGravity:true, Tags:["jump"]}
fill 97 121 97 103 121 103 minecraft:glass
place template minecraft:start 90 161 95 none none 1 0
# 让所有盔甲架不发光
execute as @e[type=armor_stand] run data modify entity @s Glowing set value false