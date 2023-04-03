package com.moon404.gbr.animation;

import java.util.HashSet;
import java.util.Set;

import com.moon404.gbr.message.C2SSlide;
import com.moon404.gbr.struct.SlideInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = "gbr", value = Dist.CLIENT, bus = Bus.FORGE)
public class Slide
{
    public static Set<String> slidingPlayers = new HashSet<>();
    public static boolean sliding = false;
    public static boolean release = true;
    public static Vec3 slidevec;
    public static Vec3 slowdelta;
    public static double tickCount = 0;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event)
    {
        if (event.phase != Phase.START) return;
        if (event.player instanceof LocalPlayer player)
        {
            Minecraft minecraft = Minecraft.getInstance();
            Options options = minecraft.options;
            float speed = player.getSpeed();
            tickCount += 1;
            if (tickCount > 60)
            {
                tickCount = 60;
            }
            if (sliding)
            {
                slidevec = slidevec.add(slowdelta);
                if (!options.keyShift.isDown() || slidevec.multiply(1, 0, 1).length() < 0.1)
                {
                    end(player);
                }
                if (player.isOnGround() && options.keyJump.isDown())
                {
                    jump(player);
                }
                player.setDeltaMovement(slidevec.x, player.getDeltaMovement().y, slidevec.z);
                player.input.forwardImpulse = 0;
                player.input.leftImpulse = 0;
            }
            if (!sliding && speed > 0.11 && player.isOnGround() && options.keyShift.isDown() && release)
            {
                start(player, speed);
            }
            if (!options.keyShift.isDown())
            {
                release = true;
            }
        }
    }

    public static void start(LocalPlayer player, float speed)
    {
        sliding = true;
        release = false;
        slidevec = player.getForward().multiply(1, 0, 1).normalize().scale(speed * tickCount / 15);
        tickCount = 0;
        slowdelta = slidevec.scale(-0.025);
        C2SSlide.INSTANCE.sendToServer(new SlideInfo(player.getScoreboardName(), 1));
    }

    public static void end(LocalPlayer player)
    {
        sliding = false;
        player.setDeltaMovement(0, player.getDeltaMovement().y, 0);
        C2SSlide.INSTANCE.sendToServer(new SlideInfo(player.getScoreboardName(), -1));
    }

    public static void jump(LocalPlayer player)
    {
        player.setOnGround(false);
        double dy = 0.5;
        if (player.hasEffect(MobEffects.JUMP))
        {
            dy *= 1.2 + 0.2 * player.getEffect(MobEffects.JUMP).getAmplifier();
        }
        player.setDeltaMovement(player.getDeltaMovement().x, dy, player.getDeltaMovement().z);
    }

    public static void animationPre(PlayerModel model, Player player)
    {
        if (!slidingPlayers.contains(player.getScoreboardName())) return;
        if (!player.isOnGround()) return;
        model.riding = true;
    }

    public static void animationPost(PlayerModel model, Player player)
    {
        if (!slidingPlayers.contains(player.getScoreboardName())) return;
        if (!player.isOnGround()) return;
        float rot = -0.8F;
        model.leftLeg.xRot = rot;
        model.rightLeg.xRot = rot;
        model.leftPants.xRot = rot;
        model.rightPants.xRot = rot;
    }
}
