# 每tick都要进行的玩家判定
execute as @a[gamemode=adventure, tag=jumping, scores={height=0..100}] at @s run function gbr:game/jumping
# 空投判定
scoreboard players add game_tick global 1
execute if score game_tick global matches 1950 run function gbr:airdrop/airdrop
execute if score game_tick global matches 2050 run function gbr:airdrop/airdrop
execute if score game_tick global matches 4000 run function gbr:airdrop/airdrop
execute at @e[tag=dropping] run particle campfire_signal_smoke ~ ~ ~ 0 0.5 0 0 0 force @a
execute at @e[tag=finish] unless block ~ ~0.5 ~ chest{Items:[]} run particle campfire_cosy_smoke ~ ~ ~ 0 0.5 0 1 0 force @a
execute as @e[tag=dropping, nbt={OnGround:true}] at @s run function gbr:airdrop/land
# AFK
execute if score game_tick global matches 600 if entity @a[gamemode=adventure, scores={height=100..2147483647}] run tellraw @a [{"selector": "@a[gamemode=adventure, scores={height=100..2147483647}]"}, "由于太久未跳伞已被系统移入观战"]
execute if score game_tick global matches 600 run gamemode spectator @a[gamemode=adventure, scores={height=100..2147483647}]
# 阶段缩圈
execute if score game_tick global matches 600 run title @a actionbar [{"text": "注意！安全区将在30秒后缩小！", "color": "gold"}]
execute if score game_tick global matches 2400 run title @a actionbar [{"text": "注意！安全区将在30秒后缩小！", "color": "gold"}]
execute if score game_tick global matches 3600 run title @a actionbar [{"text": "注意！安全区将在30秒后缩小！", "color": "gold"}]
execute if score game_tick global matches 4800 run title @a actionbar [{"text": "注意！安全区将在30秒后缩小！", "color": "gold"}]

execute if score game_tick global matches 1200 run title @a actionbar [{"text": "警告！安全区正在缩小！", "color": "red"}]
execute if score game_tick global matches 1200 run worldborder set 241 60
execute if score game_tick global matches 1200 run worldborder damage amount 1

execute if score game_tick global matches 3000 run title @a actionbar [{"text": "警告！安全区正在缩小！", "color": "red"}]
execute if score game_tick global matches 3000 run worldborder set 121 30
execute if score game_tick global matches 3000 run worldborder damage amount 2

execute if score game_tick global matches 4200 run title @a actionbar [{"text": "警告！安全区正在缩小！", "color": "red"}]
execute if score game_tick global matches 4200 run worldborder set 61 30
execute if score game_tick global matches 4200 run worldborder damage amount 4

execute if score game_tick global matches 5400 run title @a actionbar [{"text": "警告！安全区正在缩小！", "color": "red"}]
execute if score game_tick global matches 5400 run worldborder set 1 30
execute if score game_tick global matches 5400 run worldborder damage amount 8
