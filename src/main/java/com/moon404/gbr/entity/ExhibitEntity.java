package com.moon404.gbr.entity;

import com.mojang.math.Vector3f;
import com.moon404.gbr.handler.PlayerTickHandler;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ExhibitEntity extends Marker
{
    public ExhibitEntity(EntityType<?> p_147250_, Level p_147251_)
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
            Vector3f color = new Vector3f(0.67F, 0.67F, 0.67F);
            DustParticleOptions options = new DustParticleOptions(color, 1.5F);
            level.sendParticles(options, this.getX(), this.getY() + 0.2, this.getZ(), 0, 0, 0, 0, 0);
        }
        for (Player player : this.level.players())
        {
            if (this.distanceTo(player) <= 16)
            {
                Component component = Component.literal("已进入侦测范围，缓慢移动以避免发光");
                player.displayClientMessage(component, true);
                if (PlayerTickHandler.data.containsKey(player))
                {
                    if (PlayerTickHandler.data.get(player).speed > 0.1)
                    {
                        player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 2));
                    }
                }
            }
        }
    }
}
