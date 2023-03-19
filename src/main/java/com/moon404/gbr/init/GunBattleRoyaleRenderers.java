package com.moon404.gbr.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.moon404.gbr.entity.ChargingLaserRender;
import com.moon404.gbr.entity.SnareRender;

@Mod.EventBusSubscriber(modid = "gbr", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GunBattleRoyaleRenderers
{
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(GunBattleRoyaleEntities.CHARGING_LASER.get(), ChargingLaserRender::new);
        event.registerEntityRenderer(GunBattleRoyaleEntities.SNARE.get(), SnareRender::new);
    }
}
