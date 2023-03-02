package com.moon404.gbr;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import com.moon404.gbr.entity.ChargingLaserEntity;
import com.mrcrayfish.guns.common.ProjectileManager;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GunBattleRoyale.MODID)
public class GunBattleRoyale
{
    public static final String MODID = "gbr";

    public GunBattleRoyale()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        GunBattleRoyaleEntities.REGISTER.register(modEventBus);
        GunBattleRoyaleItems.REGISTER.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(PlayerTickHandler.class);
        MinecraftForge.EVENT_BUS.register(RankParticles.class);
        MinecraftForge.EVENT_BUS.register(GunFireHandler.class);
        MinecraftForge.EVENT_BUS.register(ChargeTickHandler.class);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ProjectileManager.getInstance().registerFactory(GunBattleRoyaleItems.CHARGE_BULLET.get(), (worldIn, entity, weapon, item, modifiedGun) -> new ChargingLaserEntity(GunBattleRoyaleEntities.CHARGING_LASER.get(), worldIn, entity, weapon, item, modifiedGun));
        });
    }
}
