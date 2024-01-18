# @s 使用复活的玩家
scoreboard players operation game_revive_teamid global = @s teamid
scoreboard players reset * revive
execute as @a[gamemode=spectator] if score @s teamid = game_revive_teamid global run scoreboard players set @s revive 1
tellraw @a [{"selector": "@a[scores={revive=1}]"}, " 被 ", {"selector": "@s"}, " 复活了！"]
execute at @s as @a[scores={revive=1}] run function gbr:game/revive_player
