item replace entity @s armor.chest with elytra{Enchantments:[{id:"binding_curse", lvl:1}, {id:"vanishing_curse", lvl:1}]} 1
function gbr:game/fee
tag @s add jumping
experience set @s 1 levels
experience set @s 0 points
data modify entity @s AbsorptionAmount set value 8
scoreboard players add @s stat_total 1
