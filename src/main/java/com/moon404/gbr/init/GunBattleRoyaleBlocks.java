package com.moon404.gbr.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GunBattleRoyaleBlocks
{
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, "gbr");

    public static final RegistryObject<Block> SMOKE = REGISTER.register("smoke", () -> new Block(BlockBehaviour.Properties.of(Material.AIR).noCollission()));
}
