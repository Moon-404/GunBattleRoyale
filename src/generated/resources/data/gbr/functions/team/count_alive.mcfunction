scoreboard players reset * team_alive
scoreboard players set total team_alive 0
scoreboard players set game_team_max global 3

execute as @a[gamemode=adventure, team=green] run scoreboard players add green team_alive 1
execute if score green team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=yellow] run scoreboard players add yellow team_alive 1
execute if score yellow team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=orange] run scoreboard players add orange team_alive 1
execute if score orange team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=lime] run scoreboard players add lime team_alive 1
execute if score lime team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=pink] run scoreboard players add pink team_alive 1
execute if score pink team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=brown] run scoreboard players add brown team_alive 1
execute if score brown team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=red] run scoreboard players add red team_alive 1
execute if score red team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=blue] run scoreboard players add blue team_alive 1
execute if score blue team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=black] run scoreboard players add black team_alive 1
execute if score black team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=magenta] run scoreboard players add magenta team_alive 1
execute if score magenta team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=purple] run scoreboard players add purple team_alive 1
execute if score purple team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute as @a[gamemode=adventure, team=cyan] run scoreboard players add cyan team_alive 1
execute if score cyan team_alive matches 1..2147483647 run scoreboard players add total team_alive 1

execute if score green team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score yellow team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score orange team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score lime team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score pink team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score brown team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score red team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score blue team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score black team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score magenta team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score purple team_alive matches 1 run scoreboard players set game_team_max global 2
execute if score cyan team_alive matches 1 run scoreboard players set game_team_max global 2
