package com.moon404.gbr.struct;

import java.util.Collections;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ChangeItemMessage
{
    private static final String PROTOCOL_VERSION = "1";
    private static int index = 1;

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("gbr", "change_item"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register()
    {
        INSTANCE.registerMessage(index++, ChangeItem.class,
        (content, buf) ->
        {
            buf.writeInt(content.itemid);
        },
        (buf) ->
        {
            ChangeItem content = new ChangeItem();
            content.itemid = buf.readInt();
            return content;
        },
        (content, ctx) ->
        {
            ctx.get().enqueueWork(() ->
            {
                ServerPlayer player = ctx.get().getSender();
                Inventory inventory = player.getInventory();
                for (int i = 0; i < 36; i++)
                {
                    ItemStack itemStack = inventory.getItem(i);
                    if (Item.getId(itemStack.getItem()) == content.itemid)
                    {
                        Collections.swap(inventory.items, i, inventory.selected);
                    }
                }
            });
            ctx.get().setPacketHandled(true);
        });
    }

    public static class ChangeItem
    {
        public int itemid;
    }
}
