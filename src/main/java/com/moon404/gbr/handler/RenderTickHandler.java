package com.moon404.gbr.handler;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.moon404.gbr.WheelGui;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.RenderTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "gbr", value = Dist.CLIENT)
public class RenderTickHandler
{
    public static int holding;
    public static int duration;
    public static final float distance = 30;
    public static List<Item> recoverList;
    public static int recoverNum;
    public static double recoverDegree;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event)
    {
        Minecraft mc = Minecraft.getInstance();
        int lastHolding = holding;
        holding = -1;
        for (int i = 0; i < 9; i++)
        {
            if (mc.options.keyHotbarSlots[i].isDown())
            {
                holding = i;
            }
        }
        if (holding > -1 && lastHolding == holding) duration += 1;
        else duration = 0;
        if (duration > 10) WheelGui.activate();
    }

    @SubscribeEvent
    public static void onRenderTick(RenderTickEvent event)
    {
        if (event.phase == Phase.START) return;
        recoverNum = RenderTickHandler.recoverList.size();
        recoverDegree = 3.14 * 2 / RenderTickHandler.recoverNum;
        Minecraft mc = Minecraft.getInstance();
        if (WheelGui.active)
        {
            int xc = mc.getWindow().getGuiScaledWidth() / 2;
            int yc = mc.getWindow().getGuiScaledHeight() / 2;
            int index = getMouseIndex(mc.mouseHandler.xpos(), mc.mouseHandler.ypos(), false);
            for (int i = 0; i < recoverNum; i++)
            {
                int dx = (int)(distance * Math.sin(i * recoverDegree));
                int dy = (int)(distance * Math.cos(i * recoverDegree));
                if (i == index) renderItem(xc + dx, yc + dy, Items.BLACK_STAINED_GLASS_PANE.getDefaultInstance());
                renderItem(xc + dx, yc + dy, recoverList.get(i).getDefaultInstance());
            }
        }
    }

    public static void renderItem(int x, int y, ItemStack itemStack)
    {
        PoseStack poseStack = RenderSystem.getModelViewStack();
        poseStack.pushPose();
        x -= 8;
        y -= 8;
        Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(itemStack, x, y);
        poseStack.popPose();
    }

    public static int getMouseIndex(double pMouseX, double pMouseY, boolean scale)
    {
        Minecraft mc = Minecraft.getInstance();
        int xc = (scale ? mc.getWindow().getGuiScaledWidth() : mc.getWindow().getScreenWidth()) / 2;
        int yc = (scale ? mc.getWindow().getGuiScaledHeight() : mc.getWindow().getScreenHeight()) / 2;
        double radian = Math.atan2(pMouseX - xc, pMouseY - yc);
        if (radian < 0) radian += 6.28;
        radian += RenderTickHandler.recoverDegree / 2;
        int index = (int)(radian / RenderTickHandler.recoverDegree);
        if (index >= RenderTickHandler.recoverNum) index -= RenderTickHandler.recoverNum;
        return index;
    }
}
