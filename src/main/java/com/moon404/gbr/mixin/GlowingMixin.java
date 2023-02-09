package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

@Mixin(Minecraft.class)
public class GlowingMixin
{
    @Shadow
    public LocalPlayer player;
    @Shadow
    public Options options;

    @Overwrite
    public boolean shouldEntityAppearGlowing(Entity p_91315_)
    {
        // 高亮玩家（旁观者）
        if (this.player != null && this.player.isSpectator() && this.options.keySpectatorOutlines.isDown() && p_91315_.getType() == EntityType.PLAYER) return true;
        // 有发光效果必定发光
        if (p_91315_.isCurrentlyGlowing()) return true;
        // 没有发光效果，相同队伍也会发光
        if (this.player != null && this.player.getTeam() == p_91315_.getTeam()) return true;
        // 否则不发光
        return false;
    }
}
