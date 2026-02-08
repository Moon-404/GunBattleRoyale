clear @s gunskills:skill_bag 1
execute if entity @s[tag=attack] run loot give @s loot gbr:skills/attack
execute if entity @s[tag=rogue] run loot give @s loot gbr:skills/rogue
execute if entity @s[tag=support] run loot give @s loot gbr:skills/support
execute if entity @s[tag=scout] run loot give @s loot gbr:skills/scout
execute unless entity @s[tag=attack] unless entity @s[tag=rogue] unless entity @s[tag=support] unless entity @s[tag=scout] run loot give @s loot gbr:skills/rogue
