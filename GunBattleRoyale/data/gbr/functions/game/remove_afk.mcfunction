gamemode spectator @a[gamemode=adventure, scores={height=100..2147483647}]
execute if entity @a[gamemode=adventure, scores={height=100..2147483647}] run tellraw @a [{"selector": "@a[gamemode=adventure, scores={height=100..2147483647}]"}, "由于太久未跳伞已被系统移入观战"]
