# 枪战大逃杀(GunBattleRoyale)

## 游戏简介

本 MOD 是 MrCrayfish's Gun Mod 的拓展 MOD，为大逃杀专门适配的一个 MOD。

## 使用方式

### 必装前置

- Minecraft 1.19.2 Forge 43.1.1
- [枪械MOD](https://github.com/MrCrayfish/MrCrayfishGunMod)
    - [枪械MOD前置](https://github.com/MrCrayfish/Framework)

### 可选项

- [关闭玩家名牌](https://github.com/pitbox46/HiddenNames)
- [一键掠夺&快捷栏](https://github.com/blackd/Inventory-Profiles)
    - [一键掠夺&快捷栏前置1](https://github.com/thedarkcolour/KotlinForForge)
    - [一键掠夺&快捷栏前置2](https://github.com/blackd/libIPN)

### server.properties

推荐修改以下项目

```conf
allow-flight=true
difficulty=peaceful
enable-command-block=true
force-gamemode=true
gamemode=spectator
simulation-distance=16
view-distance=16
```

### 地图设置

首先，创建一个虚空档，然后在上面建筑战斗场景，战斗场景推荐大小为 20*20 区块。为了避免频繁加载区块，以下场景应该同一个中心，仅在y轴上有差异。给一个推荐设置：

- 1-40：战斗地图
- 121：跳伞平台
- 161：游戏开始平台

两个平台建议使用玻璃或屏障方块，视距以可以在平台上看到地图全貌为佳。

### 设置盔甲架

```mcfunction
summon armor_stand ~ ~ ~ {Invisible:true, Glowing:true, Tags:["supply"]}
```

上述指令用于在玩家所在位置生成一个盔甲架，Glowing 代表是否发光，建议在布置时选上，游玩时关掉。Tags 中是盔甲架的标签，共需要设置以下几种：

- start: 游戏开始平台（地图仅一个）
- jump: 跳伞平台（地图仅一个）
- ring: 可能的圈中心点（可多个，游戏会随机选择一个；建议在室外，盔甲架下方一圈为草方块）
- supply: 物资点（可多个，会在每个物资点都刷新补给箱）

布置完毕后，可以使用这个指令让所有盔甲架不发光。

```mcfunction
execute as @e[type=armor_stand] run data modify entity @s Glowing set value false
```

### 关于指令

只有几个指令是需要服主运行或准备命令方块的：

- 第一次进入世界，请运行 `function gbr:init`
- 开始游戏的命令方块 `function gbr:start`
- 下一场比赛进入观战 `function gbr:enter_ob`
- 退出下一场比赛观战 `function gbr:leave_ob`