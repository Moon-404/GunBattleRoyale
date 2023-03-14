package com.moon404.gbr;

import com.moon404.gbr.handler.RenderTickHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class WheelGui extends Screen
{
    public static boolean active = false;
    public static final WheelGui INSTANCE = new WheelGui();

    public WheelGui()
    {
        super(Component.literal("Choose"));
    }

    public static void activate()
    {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen == null)
        {
            active = true;
            mc.setScreen(INSTANCE);
        }
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
        System.out.println(RenderTickHandler.getMouseIndex(pMouseX, pMouseY, true));

        deactivate();
        return false;
    }

    @Override
    public void removed()
    {
        active = false;
    }
}
