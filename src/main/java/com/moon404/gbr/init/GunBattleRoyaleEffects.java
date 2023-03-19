package com.moon404.gbr.init;

import com.moon404.gbr.mobeffect.Silence;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GunBattleRoyaleEffects
{
    public static final DeferredRegister<MobEffect> REGISTER = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "gbr");

    public static final RegistryObject<MobEffect> SILENCE = REGISTER.register("silence", () -> new Silence(MobEffectCategory.HARMFUL, 0xAA0000));
}
