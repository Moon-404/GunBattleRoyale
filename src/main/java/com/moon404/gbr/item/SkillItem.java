package com.moon404.gbr.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class SkillItem extends Item
{
    public SkillItem(Properties properties)
    {
        super(properties);
    }
    
    // 技能物品被扔下时触发
    // player 扔物品的玩家
    // return 是否清除掉落物
    public boolean onToss(Player player)
    {
        return false;
    }
}
