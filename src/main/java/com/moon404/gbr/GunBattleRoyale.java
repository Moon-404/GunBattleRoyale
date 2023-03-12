package com.moon404.gbr;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import com.moon404.gbr.entity.ChargingLaserEntity;
import com.moon404.gbr.handler.ChargeTickHandler;
import com.moon404.gbr.handler.GunFireHandler;
import com.moon404.gbr.handler.KnockbackHandler;
import com.moon404.gbr.handler.PlayerTickHandler;
import com.moon404.gbr.handler.ProjectileHitHandler;
import com.moon404.gbr.handler.ClientTickHandler;
import com.moon404.gbr.handler.HurtHandler;
import com.moon404.gbr.init.GunBattleRoyaleEntities;
import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.init.GunBattleRoyaleSounds;
import com.moon404.gbr.struct.RenderLaserMessage;
import com.moon404.gbr.struct.ShowDamageMessage;
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
        GunBattleRoyaleSounds.REGISTER.register(modEventBus);
        GunBattleRoyaleEntities.REGISTER.register(modEventBus);
        GunBattleRoyaleItems.REGISTER.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(PlayerTickHandler.class);
        MinecraftForge.EVENT_BUS.register(ClientTickHandler.class);
        MinecraftForge.EVENT_BUS.register(GunFireHandler.class);
        MinecraftForge.EVENT_BUS.register(ChargeTickHandler.class);
        MinecraftForge.EVENT_BUS.register(ProjectileHitHandler.class);
        MinecraftForge.EVENT_BUS.register(HurtHandler.class);
        MinecraftForge.EVENT_BUS.register(KnockbackHandler.class);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ProjectileManager.getInstance().registerFactory(GunBattleRoyaleItems.CHARGE_BULLET.get(), (worldIn, entity, weapon, item, modifiedGun) -> new ChargingLaserEntity(GunBattleRoyaleEntities.CHARGING_LASER.get(), worldIn, entity, weapon, item, modifiedGun));
            RenderLaserMessage.register();
            ShowDamageMessage.register();
        });
    }
}
