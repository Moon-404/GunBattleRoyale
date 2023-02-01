# 每tick都要进行的玩家判定
execute as @a[gamemode=adventure] run function gbr:tick/player_in_game
# 空投判定
scoreboard players add game_tick global 1
execute if score game_tick global matches 2000 run function gbr:airdrop/airdrop
execute if score game_tick global matches 4000 run function gbr:airdrop/airdrop
execute as @e[tag=dropping] run function gbr:airdrop/dropping
# 技能
execute as @e[type=item] if data entity @s Thrower at @s run function gbr:skill/item
execute as @e[tag=fly] at @s run function gbr:skill/fly_entity
