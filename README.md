# 枪战大逃杀(GunBattleRoyale)

## 游戏简介

本 MOD 是 MrCrayfish's Gun Mod 的拓展 MOD，加入了一些 Apex 元素，为大逃杀专门适配的一个 MOD。

## 使用方式

### 必装前置

- Minecraft 1.19.2 Forge 43.1.1
- [枪械MOD](https://github.com/MrCrayfish/MrCrayfishGunMod)
    - [枪械MOD前置](https://github.com/MrCrayfish/Framework)

### 可选项

- [一键掠夺&快捷栏](https://github.com/blackd/Inventory-Profiles)
    - [一键掠夺&快捷栏前置1](https://github.com/thedarkcolour/KotlinForForge)
    - [一键掠夺&快捷栏前置2](https://github.com/blackd/libIPN)

### 关于服务器

server.properties 推荐修改以下项目

```conf
allow-flight=true
difficulty=peaceful
enable-command-block=true
force-gamemode=true
gamemode=spectator
simulation-distance=16
view-distance=16
```

在本教程的设置下，4核4G25M的服务器可以维持24人流畅运行，可以参照此标准配置。

### 地图设置

首先，创建一个虚空档，然后在上面建筑战斗场景，战斗场景推荐大小为 20*20 区块，30 格高。由于重力电梯的极限高度为 10 格，且 4 格起有摔落伤害，建议设计时将希望玩家通过技能抢占的点设置为 6 格高，将玩家不能上的高点设置为 12 格高。

为了避免频繁加载区块，以下场景应该同一个中心，仅在y轴上有差异。给一个推荐设置：

- 1-40：战斗地图
- 121：跳伞平台
- 161：起始平台

跳伞平台最好不要使用玻璃，因为会被打碎。起始平台无所谓，因为本 MOD 中枪的射程没那么远。MOD 中提供了名为 gbr:start 的起始平台，可以作为参考。如果您需要调整y轴，可能需要将函数中的部分数值进行修改。

### 设置盔甲架

```mcfunction
summon armor_stand ~ ~ ~ {Invisible:true, Glowing:true, NoGravity:true, Tags:["supply"]}
```

上述指令用于在玩家所在位置生成一个盔甲架。Glowing 代表是否发光，建议在布置时选上。NoGravity 代表盔甲架不受重力影响，可以避免存档转移过程中盔甲架掉落下去。Tags 中是盔甲架的标签，共需要设置以下几种：

- start: 游戏开始平台（地图仅一个，在结构中保存了一个）
- jump: 跳伞平台（地图仅一个）
- ring: 可能的圈中心点（可多个，游戏会随机选择一个，建议在室外）
- supply: 物资点（可多个，会在每个物资点都刷新补给箱）

布置完毕后，可以使用这个指令让所有盔甲架不发光。

```mcfunction
execute as @e[type=armor_stand] run data modify entity @s Glowing set value false
```

或者为了避免盔甲架阻挡子弹，在布置完成后，可以使用这个指令把盔甲架替换为 marker。

```mcfunction
execute at @e[type=armor_stand, tag=supply] run summon marker ~ ~ ~ {Tags:["supply"]}
```

然后用这个指令清除盔甲架。

```mcfunction
kill @e[type=armor_stand, tag=supply]
```

最后，使用这个指令，给所有补给点位放上箱子。

```mcfunction
execute at @e[tag=supply] unless block ~ ~ ~ chest run setblock ~ ~ ~ chest
```

### 关于指令

- 请确认 mod:gbr 数据包在 mod:cgm 数据包之后被加载，以确保正确覆盖原版设置
- 第一次进入世界，请运行 `function gbr:init`
- 若要查看玩家生涯数据，请运行 `function gbr:stat`
- 游戏开始，请运行 `function gbr:start`

其余函数一般不需要手动调用。