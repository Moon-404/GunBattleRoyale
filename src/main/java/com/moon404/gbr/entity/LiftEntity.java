package com.moon404.gbr.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LiftEntity extends Marker
{
    public LiftEntity(EntityType<?> p_147250_, Level p_147251_)
    {
        super(p_147250_, p_147251_);
    }
    
    @Override
    public void tick()
    {
        if (this.tickCount >= 100)
        {
            this.kill();
            return;
        }
        if (this.level instanceof ServerLevel level)
        {
            level.sendParticles(ParticleTypes.POOF, this.getX(), this.getY(), this.getZ(), 0, 0, 1, 0, 1);
            for (Player player : level.players())
            {
                Vec3 delta = this.position().vectorTo(player.position());
                if (delta.x * delta.x + delta.z * delta.z <= 2)
                {
                    int lv = 9 - (int)delta.y;
                    if (!player.isSpectator() && lv >= 0)
                    {
                        player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 2, lv));
                    }
                }
            }
        }
    }
}
