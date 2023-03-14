package com.moon404.gbr.handler;

import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.attachment.IAttachment.Type;

import de.maxhenkel.corpse.corelib.death.Death;
import de.maxhenkel.corpse.corelib.death.PlayerDeathEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CorpseDeathHandler
{
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onCorpseDeath(PlayerDeathEvent event)
    {
        Death death = event.getDeath();
        for (ItemStack itemStack : death.getAllItems())
        {
            if (itemStack.getItem() instanceof GunItem item)
            {
                Gun gun = item.getGun();
                for (Type type : Type.values())
                {
                    if (Gun.hasAttachmentEquipped(itemStack, gun, type))
                    {
                        ItemStack attachmentStack = Gun.getAttachment(type, itemStack);
                        death.getAdditionalItems().add(attachmentStack);
                    }
                }
                itemStack.getOrCreateTag().remove("Attachments");
            }
        }
    }
}
