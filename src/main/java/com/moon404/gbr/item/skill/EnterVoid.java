package com.moon404.gbr.item.skill;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class EnterVoid extends SkillItem
{
    public EnterVoid(Properties properties)
    {
        super(properties, ClassType.ROGUE);
    }

    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 100));
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
        return true;
    }
}
