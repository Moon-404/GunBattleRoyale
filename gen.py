def tick(name):
    print("""scoreboard players reset %s team_alive
execute as @a[gamemode=adventure, team=%s] run scoreboard players add %s team_alive 1
execute if score %s team_alive matches 1..2147483647 run scoreboard players add total team_alive 1
""" % (name, name, name, name))

def color(name):
    print("team modify %s color %s" % (name, name))

def teamwin(name):
    print("execute if score %s team_alive matches 1..2147483647 run function gbr:team/winner_%s" % (name, name))

def teammax(name):
    print("execute if score %s team_alive > game_team_max global run scoreboard players set game_startable global 2" % name)

def empty(name):
    print("team empty %s" % name)

def exist(name):
    print("execute if score %s team_alive matches 1..2147483647 run scoreboard players set %s team_exist 1" % (name, name))

def death(name):
    print("execute if score %s team_exist matches 1 unless score %s team_alive matches 1..2147483647 run function gbr:team/death_%s" % (name, name, name))

teams = ["green", "yellow", "orange", "lime", "pink", "brown", "red", "blue", "black", "magenta", "purple", "cyan"]
teamid = []
for i in range(len(teams)):
    teamid.append(i + 1)

for i in range(12):
    t = teams[i]
    tid = teamid[i]
    print("summon marker ~ ~ ~ {Tags:[\"team\", \"%s\"], CustomName:%s}" % (t, t))
    # print("summon marker ~ ~ ~ {Tags:[\"team\", \"%s\"]}" % (t))
    # print("scoreboard players set @e[tag=%s] teamid %s" % (t, tid))
#     with open("winner_%s.mcfunction" % team, "w", encoding="UTF-8") as f:
#         f.write("""tellraw @a [{"selector": "@a[team=%s]"}, " 是胜利者！排名分：", {"score": {"name": "game_rank", "objective": "global"}}]
# scoreboard players add @a[team=%s] stat_win 1
# scoreboard players operation @a[team=%s] rank += game_rank global""" % (team, team, team))