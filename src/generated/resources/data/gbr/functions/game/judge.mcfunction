# 组队获胜判定
function gbr:game/count_team_alive
execute if score total team_alive matches 2..2147483647 run schedule function gbr:game/judge 1s
execute if score total team_alive matches 0..1 run function gbr:game/win