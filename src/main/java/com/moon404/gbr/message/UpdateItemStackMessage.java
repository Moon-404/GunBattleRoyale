package com.moon404.gbr.message;

import com.moon404.gbr.item.R2R5Item;
import com.moon404.gbr.struct.ItemStackInfo;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class UpdateItemStackMessage
{
    private static final String PROTOCOL_VERSION = "1";
    private static int index = 1;

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("gbr", "update_itemstack"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register()
    {
        INSTANCE.registerMessage(index++, ItemStackInfo.class,
        (content, buf) ->
        {
            buf.writeInt(content.slot);
            buf.writeInt(content.action);
        },
        (buf) ->
        {
            ItemStackInfo content = new ItemStackInfo();
            content.slot = buf.readInt();
            content.action = buf.readInt();
            return content;
        },
        (content, ctx) ->
        {
            ctx.get().enqueueWork(() ->
            {
                ServerPlayer player = ctx.get().getSender();
                if (content.action == 1)
                {
                    ItemStack itemStack = player.getInventory().getItem(content.slot);
                    R2R5Item.setBurst(itemStack, true);
                }
                else if (content.action == 2)
                {
                    ItemStack itemStack = player.getInventory().getItem(content.slot);
                    R2R5Item.setEnergy(itemStack, R2R5Item.getEnergy(itemStack) - R2R5Item.DASH_ENERGY);
                }
            });
            ctx.get().setPacketHandled(true);
        });
    }    
}
