package com.moon404.gbr.init;

import com.moon404.gbr.entity.ChargingLaserEntity;
import com.moon404.gbr.entity.ExhibitEntity;
import com.moon404.gbr.entity.HealthBottleEntity;
import com.moon404.gbr.entity.LiftEntity;
import com.moon404.gbr.entity.PearlEntity;
import com.moon404.gbr.entity.ShieldBottleEntity;
import com.moon404.gbr.entity.TotemEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GunBattleRoyaleEntities
{
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "gbr");

    public static final RegistryObject<EntityType<ChargingLaserEntity>> CHARGING_LASER = 
        REGISTER.register("laser_charging", () ->
            EntityType.Builder.<ChargingLaserEntity>of(ChargingLaserEntity::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .setTrackingRange(100)
                .setUpdateInterval(1)
                .noSummon()
                .fireImmune()
                .setShouldReceiveVelocityUpdates(true)
                .build("laser_charging"));
    public static final RegistryObject<EntityType<LiftEntity>> LIFT =
        REGISTER.register("lift", () ->
            EntityType.Builder.of(LiftEntity::new, MobCategory.MISC).sized(0.0F, 0.0F).clientTrackingRange(0).build("lift"));
    public static final RegistryObject<EntityType<HealthBottleEntity>> HEALTH_BOTTLE =
        REGISTER.register("health_bottle", () ->
            EntityType.Builder.of(HealthBottleEntity::new, MobCategory.MISC).sized(0.0F, 0.0F).clientTrackingRange(0).build("health_bottle"));
    public static final RegistryObject<EntityType<ShieldBottleEntity>> SHIELD_BOTTLE =
        REGISTER.register("shield_bottle", () ->
            EntityType.Builder.of(ShieldBottleEntity::new, MobCategory.MISC).sized(0.0F, 0.0F).clientTrackingRange(0).build("shield_bottle"));
    public static final RegistryObject<EntityType<PearlEntity>> PEARL =
        REGISTER.register("pearl", () ->
            EntityType.Builder.<PearlEntity>of(PearlEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("pearl"));
    public static final RegistryObject<EntityType<TotemEntity>> TOTEM =
        REGISTER.register("totem", () ->
            EntityType.Builder.of(TotemEntity::new, MobCategory.MISC).sized(0.0F, 0.0F).clientTrackingRange(0).build("totem"));
    public static final RegistryObject<EntityType<ExhibitEntity>> EXHHIBIT =
        REGISTER.register("exhibit", () ->
            EntityType.Builder.of(ExhibitEntity::new, MobCategory.MISC).sized(0.0F, 0.0F).clientTrackingRange(0).build("exhibit"));
}
