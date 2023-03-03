package com.moon404.gbr.handler;

import java.util.ArrayList;
import java.util.List;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.struct.LaserInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent.Stage;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderLevelHandler
{
    public static List<LaserInfo> lasers = new ArrayList<>();

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event)
    {
        if (event.getStage() != Stage.AFTER_SOLID_BLOCKS) return;
        PoseStack poseStack = event.getPoseStack();
        Vec3 pos = event.getCamera().getPosition();
        Minecraft mc = Minecraft.getInstance();
        for (int i = 0; i < lasers.size(); i++)
        {
            LaserInfo laser = lasers.get(i);
            Vec3 from = laser.from;
            poseStack.pushPose();
            poseStack.translate(-pos.x, -pos.y, -pos.z);
            poseStack.translate(from.x, from.y, from.z);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(-laser.yRot));
            poseStack.mulPose(Vector3f.XP.rotationDegrees(laser.xRot));
            poseStack.mulPose(Vector3f.XP.rotation(-(float)Math.atan(1 / laser.length)));
            poseStack.translate(0, -1, 1);
            poseStack.scale(1, 1, laser.length - 1);
            Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(GunBattleRoyaleItems.CHARGE_BULLET.get()), ItemTransforms.TransformType.NONE, 0xFFFFFF, OverlayTexture.NO_OVERLAY, poseStack, mc.renderBuffers().bufferSource(), 0);
            poseStack.popPose();

            if (laser.startTick == -1)
            {
                laser.startTick = event.getRenderTick() + event.getPartialTick();
                lasers.set(i, laser);
            }
        }
        float curTick = event.getRenderTick() + event.getPartialTick();
        for (int i = 0; i < lasers.size(); i++)
        {
            LaserInfo laser = lasers.get(i);
            if (curTick > laser.startTick + 1)
            {
                lasers.remove(i);
                i--;
            }
        }
    }
}
