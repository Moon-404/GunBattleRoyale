execute if block ~ ~-1 ~ green_stained_glass run team join green @s
execute as @s[team=green] run title @s actionbar "您的队伍是：绿队"

execute if block ~ ~-1 ~ yellow_stained_glass run team join yellow @s
execute as @s[team=yellow] run title @s actionbar "您的队伍是：黄队"

execute if block ~ ~-1 ~ orange_stained_glass run team join orange @s
execute as @s[team=orange] run title @s actionbar "您的队伍是：橘队"

execute if block ~ ~-1 ~ lime_stained_glass run team join lime @s
execute as @s[team=lime] run title @s actionbar "您的队伍是：亮绿队"

execute if block ~ ~-1 ~ pink_stained_glass run team join pink @s
execute as @s[team=pink] run title @s actionbar "您的队伍是：粉队"

execute if block ~ ~-1 ~ brown_stained_glass run team join brown @s
execute as @s[team=brown] run title @s actionbar "您的队伍是：棕队"

execute if block ~ ~-1 ~ red_stained_glass run team join red @s
execute as @s[team=red] run title @s actionbar "您的队伍是：红队"

execute if block ~ ~-1 ~ blue_stained_glass run team join blue @s
execute as @s[team=blue] run title @s actionbar "您的队伍是：蓝队"

execute if block ~ ~-1 ~ black_stained_glass run team join black @s
execute as @s[team=black] run title @s actionbar "您的队伍是：黑队"

execute if block ~ ~-1 ~ magenta_stained_glass run team join magenta @s
execute as @s[team=magenta] run title @s actionbar "您的队伍是：品红队"

execute if block ~ ~-1 ~ purple_stained_glass run team join purple @s
execute as @s[team=purple] run title @s actionbar "您的队伍是：紫队"

execute if block ~ ~-1 ~ cyan_stained_glass run team join cyan @s
execute as @s[team=cyan] run title @s actionbar "您的队伍是：浅蓝队"

execute if block ~ ~-1 ~ white_stained_glass run team leave @s
execute as @s[team=!] run scoreboard players set @s ob 0
execute as @s[team=] run scoreboard players set @s ob 1
execute as @s[scores={ob=1}] run title @s actionbar "您将在下一场观战"