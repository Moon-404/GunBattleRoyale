package com.moon404.gbr.item.skill;

import java.util.List;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Purify extends SkillItem
{
    public Purify(Properties properties)
    {
        super(properties, ClassType.SUPPORT);
    }

    public static boolean purified(List<Player> players)
    {
        for (Player player : players)
        {
            ItemStack offhandStack = player.getOffhandItem();
            if (offhandStack.getItem() == GunBattleRoyaleItems.PURIFY.get() && !player.hasEffect(GunBattleRoyaleEffects.SILENCE.get()) && ClassType.getClass(player) == ClassType.SUPPORT)
            {
                offhandStack.setCount(offhandStack.getCount() - 1);
                return true;
            }
        }
        return false;
    }
}
