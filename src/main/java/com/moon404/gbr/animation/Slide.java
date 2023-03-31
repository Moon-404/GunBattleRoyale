package com.moon404.gbr.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.LocalPlayer;
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
    public static boolean sliding = false;
    public static boolean release = true;
    public static Vec3 slidevec;
    public static Vec3 slowdelta;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event)
    {
        if (event.phase != Phase.START) return;
        if (event.player instanceof LocalPlayer player)
        {
            Minecraft minecraft = Minecraft.getInstance();
            Options options = minecraft.options;
            float speed = player.getSpeed();
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
        float xrot = player.getYRot();
        slidevec = new Vec3(speed * Math.sin(Math.toRadians(-xrot)), 0, speed * Math.cos(Math.toRadians(xrot))).scale(4);
        slowdelta = slidevec.scale(-0.025);
        System.out.println("slide starts.");
    }

    public static void end(LocalPlayer player)
    {
        sliding = false;
        player.setDeltaMovement(0, player.getDeltaMovement().y, 0);
        System.out.println("slide ends.");
    }

    public static void jump(LocalPlayer player)
    {
        player.setOnGround(false);
        player.setDeltaMovement(player.getDeltaMovement().x, 0.5F, player.getDeltaMovement().z);
        System.out.println("jump starts");
    }

    // return cancel
    public static void animation(PlayerModel model)
    {
        ModelPart head = model.head;
        ModelPart body = model.body;
        ModelPart leftArm = model.leftArm;
        ModelPart leftLeg = model.leftLeg;
        ModelPart rightArm = model.rightArm;
        ModelPart rightLeg = model.rightLeg;
        head.setPos(0, 3, 6);
        body.xRot = -0.5F;
        body.setPos(0, 3, 6);
        leftArm.xRot = 0;
        leftArm.setPos(5, 3, 6);
        rightArm.xRot = 0;
        rightArm.setPos(-5, 3, 6);
        leftLeg.xRot = -1.0F;
        rightLeg.xRot = -1.0F;
    }
}
