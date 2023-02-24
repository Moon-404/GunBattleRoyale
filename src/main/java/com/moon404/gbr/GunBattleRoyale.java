package com.moon404.gbr;

import net.minecraftforge.fml.common.Mod;
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
        GunBattleRoyaleItems.REGISTER.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
        MinecraftForge.EVENT_BUS.register(RankParticles.class);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
