# 全局变量，常量，以game_开头
scoreboard objectives add global dummy
scoreboard players set game_start global 0
# 死亡数，冒险+1=战败，冒险+0=存活，旁观+-1=观战
scoreboard objectives add death deathCount
# 是否观战
scoreboard objectives add ob dummy
# 玩家高度
scoreboard objectives add height dummy
# 玩家伤害吸收
scoreboard objectives add absorption dummy
# 玩家最大生命值
scoreboard objectives add maxhealth dummy
# 玩家积分
scoreboard objectives add rank dummy
scoreboard objectives setdisplay list rank
# 玩家击杀数
scoreboard objectives add kills playerKillCount
# 生涯数据
scoreboard objectives add stat_kill dummy
scoreboard objectives add stat_death dummy
scoreboard objectives add stat_win dummy
scoreboard objectives add stat_total dummy
# 占位
scoreboard objectives add nothing dummy
# 毒圈属性
worldborder damage amount 1
worldborder damage buffer 0
worldborder warning distance 1
worldborder warning time 1
gamerule doImmediateRespawn true
gamerule naturalRegeneration false