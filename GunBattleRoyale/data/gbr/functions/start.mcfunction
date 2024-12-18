scoreboard players set game_startable global 0
function gbr:team/count_alive
execute if score total team_alive matches 0..1 run scoreboard players set game_startable global 1
execute if score game_waiting global matches 1 run scoreboard players set game_startable global 2
execute if score game_startable global matches 0 run function gbr:game/set_game
execute if score game_startable global matches 1 run title @a title "队伍数不足"
execute if score game_startable global matches 2 run title @a title "两场游戏间隔太短"
