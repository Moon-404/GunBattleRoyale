package com.moon404.gbr.message;

import com.moon404.gbr.struct.SlideInfo;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class C2SSlide
{
    private static final String PROTOCOL_VERSION = "1";
    private static int index = 1;

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("gbr", "c2s_slide"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register()
    {
        INSTANCE.registerMessage(index++, SlideInfo.class,
        (content, buf) ->
        {
            buf.writeUtf(content.name);
            buf.writeInt(content.action);
        },
        (buf) ->
        {
            SlideInfo content = new SlideInfo(buf.readUtf(), buf.readInt());
            return content;
        },
        (content, ctx) ->
        {
            ctx.get().enqueueWork(() ->
            {
                S2CSlide.INSTANCE.send(PacketDistributor.ALL.noArg(), content);
            });
            ctx.get().setPacketHandled(true);
        });
    }
}
