scoreboard players set game_startable global 0
function gbr:team/count_alive
execute if score total team_alive matches 0..1 run scoreboard players set game_startable global 1
execute as @e[tag=team] if score @s team_alive > game_team_max global run scoreboard players set game_startable global 2
execute as @e[tag=team] if score @s team_alive matches 4..2147483647 run scoreboard players set game_startable global 3
execute if score game_waiting global matches 1 run scoreboard players set game_startable global 4
execute if score game_startable global matches 0 run function gbr:game/set_game
execute if score game_startable global matches 1 run title @a title "队伍数不足"
execute if score game_startable global matches 2 run title @a subtitle "该限制会在两分钟后解除"
execute if score game_startable global matches 2 run title @a title "单排三排不能混排"
execute if score game_startable global matches 3 run title @a title "队伍人数上限为三"
execute if score game_startable global matches 4 run title @a title "两场游戏间隔太短"
