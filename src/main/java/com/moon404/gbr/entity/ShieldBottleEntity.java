package com.moon404.gbr.entity;

import com.mojang.math.Vector3f;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ShieldBottleEntity extends Marker
{
    public ShieldBottleEntity(EntityType<?> p_147250_, Level p_147251_)
    {
        super(p_147250_, p_147251_);
    }
    
    @Override
    public void tick()
    {
        if (this.tickCount > 80)
        {
            this.kill();
            return;
        }
        if (this.level instanceof ServerLevel level)
        {
            Vector3f color = new Vector3f(1.00F, 0.67F, 0);
            DustParticleOptions options = new DustParticleOptions(color, 2.0F);
            level.sendParticles(options, this.getX(), this.getY() + 0.2, this.getZ(), 1, 1, 0, 1, 0.5);
            if (this.tickCount % 10 == 0)
            {
                for (Player player : level.players())
                {
                    if (this.distanceTo(player) <= 4)
                    {
                        float amount = player.getAbsorptionAmount();
                        amount += 1;
                        float maxShield = player.experienceLevel > 0 ? player.experienceLevel * 4 + 4 : 0;
                        if (amount > maxShield)
                        {
                            amount = maxShield;
                        }
                        player.setAbsorptionAmount(amount);
                    }
                }
            }
        }
    }
}
