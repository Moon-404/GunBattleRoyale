package com.moon404.gbr.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GunBattleRoyaleSounds
{
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "gbr");

    public static final RegistryObject<SoundEvent> ITEM_HEAVY_RIFLE_FIRE = REGISTER.register("item.charge_rifle.fire", () -> new SoundEvent(new ResourceLocation("gbr", "item.charge_rifle.fire")));
    public static final RegistryObject<SoundEvent> ITEM_NEMESIS_FIRE = REGISTER.register("item.nemesis.fire", () -> new SoundEvent(new ResourceLocation("gbr", "item.nemesis.fire")));
    public static final RegistryObject<SoundEvent> ITEM_PISTOL_FIRE = REGISTER.register("item.pistol.fire", () -> new SoundEvent(new ResourceLocation("gbr", "item.pistol.fire")));
}
