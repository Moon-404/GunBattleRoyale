package com.moon404.gbr.handler;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moon404.gbr.struct.DamageInfo;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "gbr", value = Dist.CLIENT)
public class RenderGuiHandler
{
    public static DamageInfo lastDamage = new DamageInfo();

    @SubscribeEvent
    public static void onRenderGui(CustomizeGuiOverlayEvent event)
    {
        Minecraft mc = Minecraft.getInstance();
        PoseStack poseStack = event.getPoseStack();

        if (lastDamage.startTick == -1)
            lastDamage.startTick = RenderLevelHandler.curTick;

        if (RenderLevelHandler.curTick < lastDamage.startTick + 20)
        {
            int xc = mc.getWindow().getGuiScaledWidth() / 2;
            int yc = mc.getWindow().getGuiScaledHeight() / 2;
            String s = Float.toString(lastDamage.amount);
            mc.font.draw(poseStack, s, xc - mc.font.width(s) / 2, yc, 0xFFFFFF);
            System.out.println(lastDamage.amount);
        }
        else
        {
            lastDamage.amount = 0;
        }
    }
}
