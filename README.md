# 枪战大逃杀(GunBattleRoyale)

## 游戏简介

这个 branch 是将 master 分支模组中的数据包单独提取出来构成的，方便移植到其它玩法中。

本分支是随机地图生成的分支，仅适用于 GunSkills 0.6.0 及以上版本。

## 数据包功能

### 空投

在 airdrop 文件夹内。

当要生成空投时，会生成一个盔甲架，第一批会生成在中心点150格范围内，第二批会生成在中心点50格范围内。生成时，只有当盔甲架的y值在0-12之间时才会成功，否则会重新尝试生成，最多尝试10次，均失败则取消生成。

## 使用方式

### 关于服务器

server.properties 推荐修改以下项目

```conf
allow-flight=true
difficulty=peaceful
enable-command-block=true
force-gamemode=true
gamemode=spectator
simulation-distance=12
view-distance=12
```

在本教程的设置下，3核6G15M的服务器可以维持18人流畅运行，可以参照此标准配置。

### 地图设置

创建一个**虚空档**，然后把 generated 文件夹放到存档目录，GunBattleRoyale 文件夹放到数据包目录，在游戏中执行：

```mcfunction
function gbr:map_init
```

该分支的数据包只需要进行以上操作即可正常游玩。

### 其它指令

建议将以下函数使用命令方块供玩家使用，其余函数一般不需要手动调用。

- 若要查看玩家生涯数据，请运行 `function gbr:stat`
- 游戏开始，请运行 `function gbr:start`

如果您需要以单人模式开始游戏以测试功能，请创建一个 tag 为 testplayer 的实体，这个实体会加入一个名为 test 的队伍，以便测试。如果您需要结束测试，击杀这个实体或者自己就行。

```mcfunction
summon armor_stand ~ ~ ~ {Tags:["testplayer"]}
```
