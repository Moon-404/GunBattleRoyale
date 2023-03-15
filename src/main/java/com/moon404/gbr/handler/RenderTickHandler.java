package com.moon404.gbr.handler;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.moon404.gbr.WheelGui;
import com.moon404.gbr.init.GunBattleRoyaleConfigs;
import com.moon404.gbr.struct.WheelItemList;

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

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event)
    {
        Minecraft mc = Minecraft.getInstance();
        int lastHolding = holding;
        holding = -1;
        List<Item> list = null;
        int recoverIndex = GunBattleRoyaleConfigs.RECOVER_INDEX.get().intValue() - 1;
        int skillIndex = GunBattleRoyaleConfigs.SKILL_INDEX.get().intValue() - 1;
        if (recoverIndex > -1 && mc.options.keyHotbarSlots[recoverIndex].isDown())
        {
            holding = recoverIndex;
            list = WheelItemList.recoverList;
        }
        if (skillIndex > -1 && mc.options.keyHotbarSlots[skillIndex].isDown())
        {
            holding = skillIndex;
            list = WheelItemList.skillList;
        }
        if (holding > -1 && lastHolding == holding) duration += 1;
        else duration = 0;
        if (duration > 10) WheelGui.activate(list);
    }

    @SubscribeEvent
    public static void onRenderTick(RenderTickEvent event)
    {
        if (event.phase == Phase.START) return;
        Minecraft mc = Minecraft.getInstance();
        if (WheelGui.active)
        {
            int xc = mc.getWindow().getGuiScaledWidth() / 2;
            int yc = mc.getWindow().getGuiScaledHeight() / 2;
            int index = WheelGui.getMouseIndex(mc.mouseHandler.xpos(), mc.mouseHandler.ypos(), false);
            int num = WheelGui.itemList.size();
            double singleRadian = 3.14 * 2 / num;
            for (int i = 0; i < num; i++)
            {
                int dx = (int)(distance * Math.sin(i * singleRadian));
                int dy = (int)(distance * Math.cos(i * singleRadian));
                if (i == index) renderItem(xc + dx, yc + dy, Items.BLACK_STAINED_GLASS_PANE.getDefaultInstance());
                renderItem(xc + dx, yc + dy, WheelGui.itemList.get(i).getDefaultInstance());
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
}
