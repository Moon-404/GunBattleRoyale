# 全局变量，常量，以game_开头
scoreboard objectives add global dummy
scoreboard players set game_start global 0
scoreboard players set game_waiting global 0
# 死亡数，冒险+1=战败，冒险+0=存活，旁观+-1=观战
scoreboard objectives add death deathCount
# 是否观战
scoreboard objectives add ob dummy
# 玩家高度
scoreboard objectives add height dummy
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
# 队伍
team add green
team add yellow
team add orange
team add lime
team add pink
team add brown
team add red
team add blue
team add black
team add magenta
team add purple
team add cyan
team add test

team modify green nametagVisibility hideForOtherTeams
team modify yellow nametagVisibility hideForOtherTeams
team modify orange nametagVisibility hideForOtherTeams
team modify lime nametagVisibility hideForOtherTeams
team modify pink nametagVisibility hideForOtherTeams
team modify brown nametagVisibility hideForOtherTeams
team modify red nametagVisibility hideForOtherTeams
team modify blue nametagVisibility hideForOtherTeams
team modify black nametagVisibility hideForOtherTeams
team modify magenta nametagVisibility hideForOtherTeams
team modify purple nametagVisibility hideForOtherTeams
team modify cyan nametagVisibility hideForOtherTeams
team modify test nametagVisibility hideForOtherTeams

team modify green color dark_green
team modify yellow color yellow
team modify orange color gold
team modify lime color green
team modify pink color light_purple
team modify brown color dark_gray
team modify red color dark_red
team modify blue color blue
team modify black color black
team modify magenta color red
team modify purple color dark_purple
team modify cyan color aqua
team modify test color white

scoreboard objectives add team_alive dummy
scoreboard players set game_two global 2
scoreboard objectives add fee dummy
scoreboard players set game_fee_interval global 50
scoreboard objectives add teamid dummy
scoreboard objectives add assist dummy
scoreboard objectives add winner dummy
scoreboard objectives add eliminated dummy

kill @e[tag=team]
summon marker ~ ~ ~ {Tags:["team", "green"]}
summon marker ~ ~ ~ {Tags:["team", "yellow"]}
summon marker ~ ~ ~ {Tags:["team", "orange"]}
summon marker ~ ~ ~ {Tags:["team", "lime"]}
summon marker ~ ~ ~ {Tags:["team", "pink"]}
summon marker ~ ~ ~ {Tags:["team", "brown"]}
summon marker ~ ~ ~ {Tags:["team", "red"]}
summon marker ~ ~ ~ {Tags:["team", "blue"]}
summon marker ~ ~ ~ {Tags:["team", "black"]}
summon marker ~ ~ ~ {Tags:["team", "magenta"]}
summon marker ~ ~ ~ {Tags:["team", "purple"]}
summon marker ~ ~ ~ {Tags:["team", "cyan"]}
summon marker ~ ~ ~ {Tags:["team", "test"]}

function gbr:team/setid