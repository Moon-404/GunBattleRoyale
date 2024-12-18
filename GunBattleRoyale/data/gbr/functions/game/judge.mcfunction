function gbr:team/count_alive

scoreboard players reset * eliminated
scoreboard players set game_eliminated_teamid global 0
execute as @e[tag=exist, scores={team_alive=0}] run scoreboard players operation game_eliminated_teamid global = @s teamid
execute as @a if score @s teamid = game_eliminated_teamid global run scoreboard players set @s eliminated 1

execute if score game_eliminated_teamid global matches 1..12 run function gbr:team/eliminate
execute if score total team_alive matches 0..1 run function gbr:game/win
