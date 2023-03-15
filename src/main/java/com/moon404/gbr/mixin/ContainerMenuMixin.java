package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import net.minecraft.world.inventory.AbstractContainerMenu;

@Mixin(AbstractContainerMenu.class)
public class ContainerMenuMixin
{
    @ModifyVariable(method = "moveItemStackTo", at = @At("HEAD"), ordinal = 0)
    protected boolean fixDirection(boolean pReverseDirection)
    {
        return false;
    }
}
