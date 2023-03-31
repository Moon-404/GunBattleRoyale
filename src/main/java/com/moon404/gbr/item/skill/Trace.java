package com.moon404.gbr.item.skill;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.message.RenderLaserMessage;
import com.moon404.gbr.struct.ClassType;
import com.moon404.gbr.struct.LaserInfo;

import de.maxhenkel.corpse.entities.CorpseEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

public class Trace extends SkillItem
{
    public Trace(Properties properties)
    {
        super(properties, ClassType.SCOUT);
    }

    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        Level level = player.level;
        for (Entity entity : level.getEntities(null, new AABB(player.blockPosition()).inflate(300)))
        {
            if (entity instanceof CorpseEntity corpse)
            {
                LaserInfo laser = new LaserInfo();
                laser.from = new Vec3(corpse.getX(), 0, corpse.getZ());
                laser.length = 100;
                laser.xRot = -90;
                laser.yRot = 0;
                laser.size = 60; // 5秒
                laser.aiming = 0;
                laser.isShooter = 0;
                RenderLaserMessage.INSTANCE.send(PacketDistributor.PLAYER.with(() ->
                {
                    if (player instanceof ServerPlayer serverPlayer)
                    {
                        return serverPlayer;
                    }
                    return null;
                }), laser);
            }
        }
        return true;
    }
}
