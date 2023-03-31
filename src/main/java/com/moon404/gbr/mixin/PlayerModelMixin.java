package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.moon404.gbr.animation.Slide;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

@Mixin(PlayerModel.class)
public class PlayerModelMixin<T extends LivingEntity>
{
    @Inject(method = "setupAnim", at = @At("HEAD"), cancellable = true)
    public void setupAnimPre(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo ci)
    {
        if (pEntity instanceof Player player)
        {
            PlayerModel model = (PlayerModel)(Object)this;
            Slide.animationPre(model, player);
        }
    }

    @Inject(method = "setupAnim", at = @At("TAIL"), cancellable = true)
    public void setupAnimPost(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo ci)
    {
        if (pEntity instanceof Player player)
        {
            PlayerModel model = (PlayerModel)(Object)this;
            Slide.animationPost(model, player);
        }
    }
}
