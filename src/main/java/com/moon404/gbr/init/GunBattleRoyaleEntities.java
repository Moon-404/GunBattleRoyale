package com.moon404.gbr.init;

import com.moon404.gbr.entity.ChargingLaserEntity;
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
}
