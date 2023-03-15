package com.moon404.gbr.struct;

import java.util.Arrays;
import java.util.List;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.mrcrayfish.guns.init.ModItems;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class WheelItemList
{
    public static List<Item> recoverList;
    public static List<Item> skillList;
    public static List<Item> throwList;

    public static void init()
    {
        recoverList = Arrays.asList(
            GunBattleRoyaleItems.SHIELD_BOOST.get(),
            GunBattleRoyaleItems.PHOENIX_KIT.get(),
            GunBattleRoyaleItems.SHIELD_BATTERY.get(),
            GunBattleRoyaleItems.SHIELD_CELL.get(),
            GunBattleRoyaleItems.MED_KIT.get(),
            GunBattleRoyaleItems.SYRINGE.get()
        );

        skillList = Arrays.asList(
            Items.RABBIT_FOOT,
            Items.GHAST_TEAR,
            Items.GLOWSTONE_DUST,
            Items.PHANTOM_MEMBRANE
        );

        throwList = Arrays.asList(
            ModItems.GRENADE.get(),
            ModItems.STUN_GRENADE.get()
        );
    }
}
