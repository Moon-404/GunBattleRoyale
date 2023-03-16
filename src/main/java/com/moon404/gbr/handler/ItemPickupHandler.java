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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
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

    public static void addAttachment(ItemStack gunStack, Type type, ItemStack attachmentStack, Player player)
    {
        CompoundTag tag = gunStack.getOrCreateTagElement("Attachments");
        tag.put(type.getTagKey(), attachmentStack.save(new CompoundTag()));
        gunStack.getOrCreateTag().put("Attachments", tag);
        player.level.playSound(null, player.getX(), player.getY() + 1.0, player.getZ(), ModSounds.UI_WEAPON_ATTACH.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
    }

    public static boolean autoAttachment(AttachmentItem attachmentItem, Inventory inventory, ItemStack attachmentStack, Player player)
    {
        for (int i = 0; i < 3; i++)
        {
            ItemStack inventoryStack = inventory.getItem(i);
            if (inventoryStack.getItem() instanceof GunItem item)
            {
                Gun gun = item.getGun();
                Type type = getAttachmentType(attachmentItem);
                if (gun.canAttachType(type))
                {
                    // 没有已经装备的配件
                    if (!Gun.hasAttachmentEquipped(inventoryStack, gun, type))
                    {
                        addAttachment(inventoryStack, type, attachmentStack, player);
                        return true;
                    }
                    // 配件更好
                    else
                    {
                        ItemStack oldAttachStack = Gun.getAttachment(type, inventoryStack);
                        Item oldAttachment = oldAttachStack.getItem();
                        if (attachmentItem.getRarity(attachmentStack) == Rarity.EPIC && oldAttachment.getRarity(oldAttachStack) == Rarity.COMMON)
                        {
                            addAttachment(inventoryStack, type, attachmentStack, player);
                            if (!autoAttachment((AttachmentItem)oldAttachment, inventory, oldAttachStack, player))
                            {
                                inventory.add(oldAttachStack);
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event)
    {
        Player player = event.getEntity();
        if (player.level.isClientSide) return;
        Inventory inventory = player.getInventory();
        ItemEntity itemEntity = event.getItem();
        ItemStack itemStack = itemEntity.getItem();
        if (itemStack.getItem() instanceof AttachmentItem attachment)
        {
            if (autoAttachment(attachment, inventory, itemStack, player))
            {
                itemEntity.kill();
                event.setCanceled(true);
            }
        }
    }
}
