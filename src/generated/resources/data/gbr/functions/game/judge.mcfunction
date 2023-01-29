# 组队获胜判定
function gbr:team/count_alive

execute if score green team_exist matches 1 unless score green team_alive matches 1..2147483647 run function gbr:team/death_green
execute if score yellow team_exist matches 1 unless score yellow team_alive matches 1..2147483647 run function gbr:team/death_yellow
execute if score orange team_exist matches 1 unless score orange team_alive matches 1..2147483647 run function gbr:team/death_orange
execute if score lime team_exist matches 1 unless score lime team_alive matches 1..2147483647 run function gbr:team/death_lime
execute if score pink team_exist matches 1 unless score pink team_alive matches 1..2147483647 run function gbr:team/death_pink
execute if score brown team_exist matches 1 unless score brown team_alive matches 1..2147483647 run function gbr:team/death_brown
execute if score red team_exist matches 1 unless score red team_alive matches 1..2147483647 run function gbr:team/death_red
execute if score blue team_exist matches 1 unless score blue team_alive matches 1..2147483647 run function gbr:team/death_blue
execute if score black team_exist matches 1 unless score black team_alive matches 1..2147483647 run function gbr:team/death_black
execute if score magenta team_exist matches 1 unless score magenta team_alive matches 1..2147483647 run function gbr:team/death_magenta
execute if score purple team_exist matches 1 unless score purple team_alive matches 1..2147483647 run function gbr:team/death_purple
execute if score cyan team_exist matches 1 unless score cyan team_alive matches 1..2147483647 run function gbr:team/death_cyan

execute if score total team_alive matches 2..2147483647 run schedule function gbr:game/judge 1s
execute if score total team_alive matches 0..1 run function gbr:game/win