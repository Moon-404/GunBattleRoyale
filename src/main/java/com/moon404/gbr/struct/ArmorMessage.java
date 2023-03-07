package com.moon404.gbr.struct;

import com.moon404.gbr.mixin.ForgeGuiMixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ArmorMessage
{
    private static final String PROTOCOL_VERSION = "1";
    private static int index = 1;

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("gbr", "armor_message"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register()
    {
        INSTANCE.registerMessage(index++, ArmorInfo.class,
        (content, buf) ->
        {
            buf.writeInt(content.level);
            buf.writeFloat(content.upgrade);
        },
        (buf) ->
        {
            ArmorInfo content = new ArmorInfo();
            content.level = buf.readInt();
            content.upgrade = buf.readFloat();
            return content;
        },
        (content, ctx) ->
        {
            ctx.get().enqueueWork(() ->
            {
                ArmorInfo.instance.level = content.level;
                ArmorInfo.instance.upgrade = content.upgrade;
            });
            ctx.get().setPacketHandled(true);
        });
    }
}
