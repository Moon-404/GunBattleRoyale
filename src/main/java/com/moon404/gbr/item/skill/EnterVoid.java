package com.moon404.gbr.item.skill;

import com.moon404.gbr.init.GunBattleRoyaleEffects;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class EnterVoid extends SkillItem
{
    public EnterVoid(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onToss(Player player)
    {
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 100));
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
        return true;
    }
}
