# execute at @a[gamemode=adventure, scores={height=40..200, rank=0..4}] run particle dust 0.55 0.27 0.07 2.0 ~ ~0.2 ~
execute if score @s rank matches 0..9 run particle dust 0.75 0.75 0.75 2.0 ~ ~0.2 ~
execute if score @s rank matches 10..29 run particle dust 1.00 0.86 0.00 2.0 ~ ~0.2 ~
execute if score @s rank matches 30..59 run particle dust 0.50 0.86 1.00 2.0 ~ ~0.2 ~
execute if score @s rank matches 60..99 run particle dust 0.00 0.45 0.85 2.0 ~ ~0.2 ~
execute if score @s rank matches 100..149 run particle dust 0.69 0.05 0.79 2.0 ~ ~0.2 ~
execute if score @s rank matches 150..2147483647 run particle dust 1.00 0.25 0.21 2.0 ~ ~0.2 ~