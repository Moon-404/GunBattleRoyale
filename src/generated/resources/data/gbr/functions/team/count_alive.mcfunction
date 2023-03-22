scoreboard players set @e[tag=team] team_alive 0

execute as @a[gamemode=adventure, team=green] run scoreboard players add @e[tag=green] team_alive 1
execute as @a[gamemode=adventure, team=yellow] run scoreboard players add @e[tag=yellow] team_alive 1
execute as @a[gamemode=adventure, team=orange] run scoreboard players add @e[tag=orange] team_alive 1
execute as @a[gamemode=adventure, team=lime] run scoreboard players add @e[tag=lime] team_alive 1
execute as @a[gamemode=adventure, team=pink] run scoreboard players add @e[tag=pink] team_alive 1
execute as @a[gamemode=adventure, team=brown] run scoreboard players add @e[tag=brown] team_alive 1
execute as @a[gamemode=adventure, team=red] run scoreboard players add @e[tag=red] team_alive 1
execute as @a[gamemode=adventure, team=blue] run scoreboard players add @e[tag=blue] team_alive 1
execute as @a[gamemode=adventure, team=black] run scoreboard players add @e[tag=black] team_alive 1
execute as @a[gamemode=adventure, team=magenta] run scoreboard players add @e[tag=magenta] team_alive 1
execute as @a[gamemode=adventure, team=purple] run scoreboard players add @e[tag=purple] team_alive 1
execute as @a[gamemode=adventure, team=cyan] run scoreboard players add @e[tag=cyan] team_alive 1
execute as @e[tag=testplayer] run scoreboard players add @e[tag=test] team_alive 1

scoreboard players set total team_alive 0
execute as @e[tag=team, scores={team_alive=1..3}] run scoreboard players add total team_alive 1

scoreboard players set game_team_max global 3
execute as @e[tag=team] if score @s team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score game_force_start global matches 1 run scoreboard players set game_team_max global 3
