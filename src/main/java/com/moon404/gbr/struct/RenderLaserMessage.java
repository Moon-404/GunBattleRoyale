package com.moon404.gbr.struct;

import com.moon404.gbr.handler.RenderLevelHandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class RenderLaserMessage
{
    private static final String PROTOCOL_VERSION = "1";
    private static int index = 1;

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("gbr", "render_laser"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register()
    {
        INSTANCE.registerMessage(index++, LaserInfo.class,
        (content, buf) ->
        {
            buf.writeDouble(content.from.x());
            buf.writeDouble(content.from.y());
            buf.writeDouble(content.from.z());
            buf.writeFloat(content.length);
            buf.writeFloat(content.xRot);
            buf.writeFloat(content.yRot);
            buf.writeInt(content.aiming);
            buf.writeInt(content.size);
            buf.writeInt(content.isShooter);
        },
        (buf) ->
        {
            LaserInfo content = new LaserInfo();
            content.from = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
            content.length = buf.readFloat();
            content.xRot = buf.readFloat();
            content.yRot = buf.readFloat();
            content.aiming = buf.readInt();
            content.size = buf.readInt();
            content.isShooter = buf.readInt();
            return content;
        },
        (content, ctx) ->
        {
            ctx.get().enqueueWork(() ->
            {
                LaserInfo laser = new LaserInfo();
                laser.from = content.from;
                laser.length = content.length;
                laser.xRot = content.xRot;
                laser.yRot = content.yRot;
                laser.aiming = content.aiming;
                laser.size = content.size;
                laser.isShooter = content.isShooter;
                RenderLevelHandler.lasers.add(laser);
            });
            ctx.get().setPacketHandled(true);
        });
    }
    
}
