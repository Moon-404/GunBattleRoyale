package com.moon404.gbr.mixin;

import java.util.Random;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.item.Nemesis;
import com.mrcrayfish.guns.Config;
import com.mrcrayfish.guns.client.handler.RecoilHandler;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.common.Gun.General;
import com.mrcrayfish.guns.event.GunFireEvent;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.attachment.IAttachment;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mixin(RecoilHandler.class)
public class RecoilMixin
{
    private Random random = new Random();
    private float offsetAngle;
    private int rate;

    @Shadow
    private float cameraRecoil;
    @Shadow
    private float progressCameraRecoil;


    @Inject(method = "onGunFire", at = @At("TAIL"), remap = false)
    private void onGunFireTail(GunFireEvent.Post event, CallbackInfo ci)
    {
        ItemStack heldItem = event.getStack();
        GunItem gunItem = (GunItem)heldItem.getItem();
        Gun modifiedGun = gunItem.getModifiedGun(heldItem);
        General general = modifiedGun.getGeneral();
        ItemStack stack = Gun.getAttachment(IAttachment.Type.UNDER_BARREL, heldItem);
        float maxOffset = 0.5F;
        if (stack.getItem() == GunBattleRoyaleItems.GRIP.get())
        {
            maxOffset *= 0.8F;
        }
        else if (stack.getItem() == GunBattleRoyaleItems.ADVANCED_GRIP.get())
        {
            maxOffset *= 0.6F;
        }
        this.offsetAngle = this.random.nextFloat(-maxOffset, maxOffset); // 大约30度
        this.rate = general.getRate();
        if (!general.isAuto() || gunItem instanceof Nemesis) this.rate = 2;
    }

    @Overwrite(remap = false)
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END || this.cameraRecoil <= 0) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (!Config.SERVER.enableCameraRecoil.get()) return;

        float recoilAmount = this.cameraRecoil * mc.getDeltaFrameTime() * 0.2F / this.rate;
        float startProgress = this.progressCameraRecoil / this.cameraRecoil;
        float endProgress = (this.progressCameraRecoil + recoilAmount) / this.cameraRecoil;

        float pitch = mc.player.getXRot();
        float yaw = mc.player.getYRot();
        float xrecoil = this.cameraRecoil * (float)Math.cos(this.offsetAngle);
        float yrecoil = this.cameraRecoil * (float)Math.sin(this.offsetAngle);
        if (startProgress < 0.2F)
        {
            mc.player.setXRot(pitch - ((endProgress - startProgress) / 0.2F) * xrecoil);
            mc.player.setYRot(yaw - ((endProgress - startProgress) / 0.2F) * yrecoil);
        }
        else
        {
            mc.player.setXRot(pitch + ((endProgress - startProgress) / 0.8F) * xrecoil);
            mc.player.setYRot(yaw + ((endProgress - startProgress) / 0.8F) * yrecoil);
        }

        this.progressCameraRecoil += recoilAmount;

        if (this.progressCameraRecoil >= this.cameraRecoil)
        {
            this.cameraRecoil = 0;
            this.progressCameraRecoil = 0;
        }
    }
}
