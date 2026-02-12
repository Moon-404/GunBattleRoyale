# 初始化数据
function gbr:init
# 放置平台
summon armor_stand 160.5 122 160.5 {Invisible:true, Glowing:true, NoGravity:true, Tags:["jump"]}
fill 157 121 157 163 121 163 minecraft:glass
place template minecraft:start 150 161 155 none none 1 0
# 让所有盔甲架不发光
execute as @e[type=armor_stand] run data modify entity @s Glowing set value false