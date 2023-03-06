package com.moon404.gbr.handler;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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
            NumberFormat formatter = new DecimalFormat("0.0");
            String s = formatter.format(lastDamage.amount);
            int width = mc.font.width(s);
            mc.font.draw(poseStack, s, xc - width / 2, yc - mc.font.wordWrapHeight(s, width) * 2, 0xFFFFFF);
        }
        else
        {
            lastDamage.amount = 0;
        }
    }
}
