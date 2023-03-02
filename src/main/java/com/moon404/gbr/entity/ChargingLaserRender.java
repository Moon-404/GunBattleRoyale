package com.moon404.gbr.entity;

import org.checkerframework.framework.qual.QualifierArgument;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.moon404.gbr.GunBattleRoyaleItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class ChargingLaserRender extends EntityRenderer<ChargingLaserEntity>
{
    public ChargingLaserRender(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ChargingLaserEntity entity)
    {
        return null;
    }

    @Override
    public void render(ChargingLaserEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource renderTypeBuffer, int light)
    {
        if (entity.tickCount <= 1) return;
        poseStack.pushPose();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(entity.getYRot()));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(-entity.getXRot()));
        double speed = entity.getProjectile().getSpeed();
        float move = (entity.tickCount + partialTicks) * (float)speed - 1.0F;
        if (move < 0.0F) move = 0.0F;
        if (move > speed * 2) move = (float)speed * 2;
        poseStack.scale(1.0F, 1.0F, move);
        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(GunBattleRoyaleItems.CHARGE_BULLET.get()), ItemTransforms.TransformType.NONE, light, OverlayTexture.NO_OVERLAY, poseStack, renderTypeBuffer, 0);
        poseStack.popPose();
    }
}
