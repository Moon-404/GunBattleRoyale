package com.moon404.gbr.item.skill;

import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SkillItem extends Item
{
    public ClassType classType;

    public SkillItem(Properties properties, ClassType type)
    {
        super(properties);
        this.classType = type;
    }
    
    // 技能物品被扔下时触发
    // player 扔物品的玩家
    // return 是否清除掉落物
    public boolean onToss(Player player)
    {
        return false;
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity)
    {
        if (entity.level.isClientSide) return false;
        if (entity.isOnGround()) return onLand(entity);
        return false;
    }

    // return true to skip any further update code.
    public boolean onLand(ItemEntity entity)
    {
        return false;
    }
}
