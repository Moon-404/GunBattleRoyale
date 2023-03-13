package com.moon404.gbr.handler;

import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.init.ModSounds;
import com.mrcrayfish.guns.item.AttachmentItem;
import com.mrcrayfish.guns.item.BarrelItem;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.ScopeItem;
import com.mrcrayfish.guns.item.StockItem;
import com.mrcrayfish.guns.item.UnderBarrelItem;
import com.mrcrayfish.guns.item.attachment.IAttachment.Type;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemPickupHandler
{
    private static Type getAttachmentType(AttachmentItem item)
    {
        if (item instanceof ScopeItem) return Type.SCOPE;
        if (item instanceof StockItem) return Type.STOCK;
        if (item instanceof BarrelItem) return Type.BARREL;
        if (item instanceof UnderBarrelItem) return Type.UNDER_BARREL;
        return null;
    }

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event)
    {
        Player player = event.getEntity();
        if (player.level.isClientSide) return;
        Inventory inventory = player.getInventory();
        ItemStack itemStack = event.getItem().getItem();
        if (itemStack.getItem() instanceof AttachmentItem attachment)
        {
            for (int i = 0; i < 3; i++)
            {
                ItemStack inventoryStack = inventory.getItem(i);
                if (inventoryStack.getItem() instanceof GunItem item)
                {
                    Gun gun = item.getGun();
                    Type type = getAttachmentType(attachment);
                    if (gun.canAttachType(type) && !Gun.hasAttachmentEquipped(inventoryStack, gun, type))
                    {
                        CompoundTag tag = inventoryStack.getOrCreateTagElement("Attachments");
                        tag.put(type.getTagKey(), itemStack.save(new CompoundTag()));
                        inventoryStack.getOrCreateTag().put("Attachments", tag);
                        player.level.playSound(null, player.getX(), player.getY() + 1.0, player.getZ(), ModSounds.UI_WEAPON_ATTACH.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
                        event.getItem().kill();
                        event.setCanceled(true);
                        return;
                    }
                }
            }
        }
    }
}
