package com.moon404.gbr.handler;

import java.util.ArrayList;
import java.util.List;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.struct.LaserInfo;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent.Stage;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "gbr", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
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

            if (laser.isShooter == 1 && mc.options.getCameraType() == CameraType.FIRST_PERSON)
            {
                if (laser.aiming == 1)
                {
                    poseStack.mulPose(Vector3f.XP.rotation((float)Math.atan(-0.3 / laser.length)));
                    poseStack.translate(0, -0.3, 0);
                }
                else
                {
                    poseStack.mulPose(Vector3f.YP.rotation((float)Math.atan(0.15 / laser.length)));
                    poseStack.mulPose(Vector3f.XP.rotation((float)Math.atan(-0.1 / laser.length)));
                    poseStack.translate(-0.15, -0.1, 0);
                }
            }
            else
            {
                if (laser.aiming == 1)
                {
                    poseStack.mulPose(Vector3f.YP.rotation((float)Math.atan(0.1 / laser.length)));
                    poseStack.mulPose(Vector3f.XP.rotation((float)Math.atan(-0.3 / laser.length)));
                    poseStack.translate(-0.1, -0.3, 0);
                }
                else
                {
                    poseStack.mulPose(Vector3f.YP.rotation((float)Math.atan(0.15 / laser.length)));
                    poseStack.mulPose(Vector3f.XP.rotation((float)Math.atan(-0.5 / laser.length)));
                    poseStack.translate(-0.15, -0.5, 0);
                }
            }
            
            float size = laser.size * 0.03F;
            poseStack.scale(size, size, laser.length);
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
                if (laser.size > LaserInfo.DURATION_TICK)
                {
                    laser.size -= 0.5F;
                    laser.startTick += 1;
                    lasers.set(i, laser);
                }
                else
                {
                    lasers.remove(i);
                    i--;
                }
            }
        }
    }
}
