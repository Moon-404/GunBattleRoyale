package com.moon404.gbr.item.skill;

import java.util.List;
import java.util.ArrayList;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Glow extends SkillItem
{
    public Glow(Properties properties)
    {
        super(properties, ClassType.SCOUT);
    }

    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        Level level = player.level;
        List<Player> players = new ArrayList<>();
        for (Player target : level.players())
        {
            if (!target.isSpectator() && player.distanceTo(target) < 32 && player.getTeam() != target.getTeam())
            {
                players.add(target);
            }
        }
        if (Purify.purified(players))
        {
            player.displayClientMessage(Component.literal("上帝之眼效果被净化"), true);
        }
        else
        {
            for (Player target : players)
            {
                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60));
            }
        }
        return true;
    }
}
