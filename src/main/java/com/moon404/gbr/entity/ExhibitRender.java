package com.moon404.gbr.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ExhibitRender extends EntityRenderer<ExhibitEntity>
{
    public ExhibitRender(Context pContext)
    {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(ExhibitEntity pEntity)
    {
        return null;
    }

    @Override
    public void render(ExhibitEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight)
    {
        Minecraft.getInstance().getItemRenderer().renderStatic(pEntity.getItem(), ItemTransforms.TransformType.NONE, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, 0);
    }
}
