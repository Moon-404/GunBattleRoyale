package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.moon404.gbr.item.R2R5Item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "gbr", value = Dist.CLIENT, bus = Bus.MOD)
public class ClientSetupHandler
{
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event)
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
