package com.moon404.gbr;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import com.moon404.gbr.entity.ChargingLaserEntity;
import com.moon404.gbr.handler.AttackEntityHandler;
import com.moon404.gbr.handler.ChargeTickHandler;
import com.moon404.gbr.handler.GunFireHandler;
import com.moon404.gbr.handler.GunReloadHandler;
import com.moon404.gbr.handler.KnockbackHandler;
import com.moon404.gbr.handler.LoginHandler;
import com.moon404.gbr.handler.PlayerTickHandler;
import com.moon404.gbr.handler.ProjectileHitHandler;
import com.moon404.gbr.handler.ClientTickHandler;
import com.moon404.gbr.handler.CorpseDeathHandler;
import com.moon404.gbr.handler.HurtHandler;
import com.moon404.gbr.handler.ItemPickupHandler;
import com.moon404.gbr.handler.ItemTossHandler;
import com.moon404.gbr.init.GunBattleRoyaleConfigs;
import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleEntities;
import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.init.GunBattleRoyaleSounds;
import com.moon404.gbr.item.R2R5Item;
import com.moon404.gbr.message.C2SSlide;
import com.moon404.gbr.message.ChangeItemMessage;
import com.moon404.gbr.message.RenderLaserMessage;
import com.moon404.gbr.message.S2CSlide;
import com.moon404.gbr.message.ShowDamageMessage;
import com.moon404.gbr.message.UpdateItemStackMessage;
import com.moon404.gbr.struct.WheelItemList;
import com.mrcrayfish.guns.common.ProjectileManager;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
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
        GunBattleRoyaleEffects.REGISTER.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(PlayerTickHandler.class);
        MinecraftForge.EVENT_BUS.register(ClientTickHandler.class);
        MinecraftForge.EVENT_BUS.register(GunFireHandler.class);
        MinecraftForge.EVENT_BUS.register(ChargeTickHandler.class);
        MinecraftForge.EVENT_BUS.register(ProjectileHitHandler.class);
        MinecraftForge.EVENT_BUS.register(HurtHandler.class);
        MinecraftForge.EVENT_BUS.register(KnockbackHandler.class);
        MinecraftForge.EVENT_BUS.register(ItemPickupHandler.class);
        MinecraftForge.EVENT_BUS.register(CorpseDeathHandler.class);
        MinecraftForge.EVENT_BUS.register(ItemTossHandler.class);
        MinecraftForge.EVENT_BUS.register(LoginHandler.class);
        MinecraftForge.EVENT_BUS.register(GunReloadHandler.class);
        MinecraftForge.EVENT_BUS.register(AttackEntityHandler.class);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(Type.CLIENT, GunBattleRoyaleConfigs.SPEC, "GunBattleRoyaleConfig.toml");
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onClientSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ProjectileManager.getInstance().registerFactory(GunBattleRoyaleItems.CHARGE_BULLET.get(), (worldIn, entity, weapon, item, modifiedGun) -> new ChargingLaserEntity(GunBattleRoyaleEntities.CHARGING_LASER.get(), worldIn, entity, weapon, item, modifiedGun));
            RenderLaserMessage.register();
            ShowDamageMessage.register();
            ChangeItemMessage.register();
            UpdateItemStackMessage.register();
            C2SSlide.register();
            S2CSlide.register();
            WheelItemList.init();
        });
    }

    private void onClientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ItemProperties.register(GunBattleRoyaleItems.R2R5.get(), new ResourceLocation("gbr", "r2r5_blocking"), (stack, level, living, id) -> {
                return living != null && living.isUsingItem() ? 1.0F : 0.0F;
            });
            ItemProperties.register(GunBattleRoyaleItems.R2R5.get(), new ResourceLocation("gbr", "r2r5_bursting"), (stack, level, living, id) -> {
                return R2R5Item.bursting(stack) || R2R5Item.getEnergy(stack) == R2R5Item.MAX_ENERGY ? 1.0F : 0.0F;
            });
        });
    }
}
