package com.moon404.gbr;

import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WheelGui extends Screen
{
    public static boolean active = false;
    public static final WheelGui INSTANCE = new WheelGui();
    public static List<Item> itemList;

    public WheelGui()
    {
        super(Component.literal("Choose"));
    }

    public static void activate(List<Item> list)
    {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen == null)
        {
            active = true;
            mc.setScreen(INSTANCE);
        }
        itemList = list;
    }

    public static void deactivate()
    {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen == INSTANCE)
        {
            active = false;
            mc.setScreen(null);
        }
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton)
    {
        int index = getMouseIndex(pMouseX, pMouseY, true);
        Inventory inventory = this.minecraft.player.getInventory();
        for (int i = 0; i < 36; i++)
        {
            ItemStack itemStack = inventory.getItem(i);
            if (itemStack.getItem().equals(itemList.get(index)))
            {
                Collections.swap(inventory.items, i, inventory.selected);
                deactivate();
                return true;
            }
        }
        deactivate();
        return false;
    }

    @Override
    public void removed()
    {
        active = false;
    }

    public static int getMouseIndex(double pMouseX, double pMouseY, boolean scale)
    {
        int num = itemList.size();
        double singleRadian = 3.14 * 2 / num;
        Minecraft mc = Minecraft.getInstance();
        int xc = (scale ? mc.getWindow().getGuiScaledWidth() : mc.getWindow().getScreenWidth()) / 2;
        int yc = (scale ? mc.getWindow().getGuiScaledHeight() : mc.getWindow().getScreenHeight()) / 2;
        double radian = Math.atan2(pMouseX - xc, pMouseY - yc);
        if (radian < 0) radian += 6.28;
        radian += singleRadian / 2;
        int index = (int)(radian / singleRadian);
        if (index >= num) index -= num;
        return index;
    }
}
