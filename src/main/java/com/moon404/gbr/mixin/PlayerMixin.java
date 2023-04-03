package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.moon404.gbr.animation.Slide;

import net.minecraft.world.entity.player.Player;

@Mixin(Player.class)
public class PlayerMixin extends EntityMixin
{
    @Shadow
    public int experienceLevel;

    @Overwrite
    public int getXpNeededForNextLevel()
    {
        if (this.experienceLevel == 1) return 2500;
        if (this.experienceLevel == 2) return 5000;
        if (this.experienceLevel == 3) return 12500;
        return Integer.MAX_VALUE;
    }

    @Overwrite
    public int getExperienceReward()
    {
        return 0;
    }

    @Overwrite
    public boolean isStayingOnGroundSurface()
    {
        return this.isShiftKeyDown() && !Slide.sliding;
    }
}
