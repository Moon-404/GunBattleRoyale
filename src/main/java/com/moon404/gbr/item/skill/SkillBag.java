package com.moon404.gbr.item.skill;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SkillBag extends SkillItem
{
    public static List<Item> attackItems = Arrays.asList(
        GunBattleRoyaleItems.SILENCE.get(),
        GunBattleRoyaleItems.TOTEM.get(),
        GunBattleRoyaleItems.SNARE.get(),
        GunBattleRoyaleItems.FAST.get()
    );
    public static List<Item> roguekItems = Arrays.asList(
        GunBattleRoyaleItems.VOID.get(),
        GunBattleRoyaleItems.STIM.get(),
        GunBattleRoyaleItems.LIFT.get(),
        GunBattleRoyaleItems.BOOT.get()
    );
    public static List<Item> supportItems = Arrays.asList(
        GunBattleRoyaleItems.PURIFY.get(),
        GunBattleRoyaleItems.HEALTH_BOTTLE.get(),
        GunBattleRoyaleItems.SHIELD_BOTTLE.get(),
        GunBattleRoyaleItems.CHARGE.get()
    );
    public static List<Item> scoutItems = Arrays.asList(
        GunBattleRoyaleItems.SCAN.get(),
        GunBattleRoyaleItems.IRE.get(),
        GunBattleRoyaleItems.EXHIBIT.get(),
        GunBattleRoyaleItems.GLOW.get()
    );

    public SkillBag(Properties properties)
    {
        super(properties, null);
    }

    @Override
    public boolean onToss(Player player)
    {
        List<Item> items = roguekItems;
        if (ClassType.getClass(player) == ClassType.ATTACK) items = attackItems;
        else if (ClassType.getClass(player) == ClassType.SUPPORT) items = supportItems;
        else if (ClassType.getClass(player) == ClassType.SCOUT) items = scoutItems;
        int rand = new Random().nextInt(items.size());
        player.addItem(new ItemStack(items.get(rand)));
        return true;
    }
}
