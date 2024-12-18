# 初始化数据
function gbr:init
# 为了避免盔甲架阻挡子弹，把 supply 盔甲架替换为 marker
execute at @e[type=armor_stand, tag=supply] run summon marker ~ ~ ~ {Tags:["supply"]}
# 清除 supply 盔甲架
kill @e[type=armor_stand, tag=supply]
# 给所有补给点位放上箱子
execute at @e[tag=supply] unless block ~ ~ ~ chest run setblock ~ ~ ~ chest
# 让所有盔甲架不发光
execute as @e[type=armor_stand] run data modify entity @s Glowing set value false