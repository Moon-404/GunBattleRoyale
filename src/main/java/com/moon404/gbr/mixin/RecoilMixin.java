package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.mrcrayfish.guns.Config;
import com.mrcrayfish.guns.client.handler.RecoilHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.RandomSource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mixin(RecoilHandler.class)
public class RecoilMixin
{
    private float hoRecoil;
    private int changeDirCount = 5;
    private float direction = 0.25F;
    RandomSource random = RandomSource.create();
    @Shadow
    private float cameraRecoil;
    @Shadow
    private float progressCameraRecoil;
    @Overwrite
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END || this.cameraRecoil <= 0)
            return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null)
            return;

        if (!Config.SERVER.enableCameraRecoil.get())
            return;
        
        float recoilAmount = this.cameraRecoil * mc.getDeltaFrameTime() * 0.15F;
        float startProgress = this.progressCameraRecoil / this.cameraRecoil;
        float endProgress = (this.progressCameraRecoil + recoilAmount) / this.cameraRecoil;

        if (this.progressCameraRecoil == 0)
        {
            changeDirCount--;
            if (changeDirCount <= 0)
            {
                changeDirCount = random.nextInt(5, 10);
                direction *= -1.0F;
            }
            hoRecoil = this.cameraRecoil * direction;
        }

        float pitch = mc.player.getXRot();
        float yaw = mc.player.getYRot();
        if (startProgress < 0.2F)
        {
            mc.player.setXRot(pitch - ((endProgress - startProgress) / 0.2F) * this.cameraRecoil);
            mc.player.setYRot(yaw - ((endProgress - startProgress) / 0.2F) * hoRecoil);
        }
        else
        {
            mc.player.setXRot(pitch + ((endProgress - startProgress) / 0.8F) * this.cameraRecoil);
            mc.player.setYRot(yaw + ((endProgress - startProgress) / 0.8F) * hoRecoil);
        }

        this.progressCameraRecoil += recoilAmount;

        if (this.progressCameraRecoil >= this.cameraRecoil)
        {
            this.cameraRecoil = 0;
            this.progressCameraRecoil = 0;
        }
    }
}
