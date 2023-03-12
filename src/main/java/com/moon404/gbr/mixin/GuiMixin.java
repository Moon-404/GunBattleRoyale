package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;

@Mixin(Gui.class)
public class GuiMixin extends GuiComponentMixin
{
    @Shadow
    protected long healthBlinkTime;
    @Shadow
    protected int tickCount;
    @Shadow
    protected int lastHealth;
    @Shadow
    protected long lastHealthTime;
    @Shadow
    protected int displayHealth;
    @Shadow
    protected RandomSource random;
    @Shadow
    protected void renderHearts(PoseStack pPoseStack, Player pPlayer, int pX, int pY, int pHeight, int p_168694_, float p_168695_, int p_168696_, int p_168697_, int p_168698_, boolean p_168699_)
    {
        
    }
}
