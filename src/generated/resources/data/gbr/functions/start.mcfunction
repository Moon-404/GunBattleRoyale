scoreboard objectives remove death
scoreboard objectives add death deathCount
execute as @a[gamemode=adventure] unless score @s ob matches 1 run scoreboard players set @s death 0

# 单人模式存档
# scoreboard players set game_sum global 0
# execute as @a[gamemode=adventure, scores={death=0}] run scoreboard players add game_sum global 1
# execute if score game_sum global matches 2..2147483647 run function gbr:game/set_game
# execute if score game_sum global matches 0..1 run title @a title "人数不足无法开始游戏"

# 组队模式
# 0 可以开始，1 队伍数不足，2 某队人数超过上限，3 延迟未满
scoreboard players set game_startable global 0
function gbr:team/count_alive
execute if score total team_alive matches 0..1 run scoreboard players set game_startable global 1
execute if score green team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score yellow team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score orange team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score lime team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score pink team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score brown team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score red team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score blue team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score black team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score magenta team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score purple team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score cyan team_alive > game_team_max global run scoreboard players set game_startable global 2
execute if score game_waiting global matches 1 run scoreboard players set game_startable global 3
execute if score game_startable global matches 0 run function gbr:game/set_game
execute if score game_startable global matches 1 run title @a title "队伍数不足"
execute if score game_startable global matches 2 run title @a title "有队伍玩家太多"
execute if score game_startable global matches 3 run title @a title "两场游戏间隔太短"
