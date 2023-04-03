package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.world.entity.Entity;

@Mixin(Entity.class)
public class EntityMixin
{
    @Shadow
    public int tickCount;

    @Shadow
    public boolean isShiftKeyDown()
    {
        return false;
    }
}
