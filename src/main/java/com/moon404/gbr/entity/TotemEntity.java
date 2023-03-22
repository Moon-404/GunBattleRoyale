package com.moon404.gbr.entity;

import com.mojang.math.Vector3f;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TotemEntity extends Marker
{
    public Player player;

    public TotemEntity(EntityType<?> p_147250_, Level p_147251_)
    {
        super(p_147250_, p_147251_);
    }
    
    @Override
    public void tick()
    {
        if (this.tickCount >= 200)
        {
            this.kill();
            return;
        }
        if (this.tickCount % 10 == 0 && this.level instanceof ServerLevel level)
        {
            Vector3f color = new Vector3f(0.33F, 0.33F, 0.33F);
            DustParticleOptions options = new DustParticleOptions(color, 1.5F);
            level.sendParticles(options, this.getX(), this.getY() + 0.2, this.getZ(), 0, 0, 0, 0, 0);
        }
        if (this.player.getAbsorptionAmount() == 0)
        {
            player.teleportTo(this.getX(), this.getY(), this.getZ());
            this.kill();
        }
    }
}
