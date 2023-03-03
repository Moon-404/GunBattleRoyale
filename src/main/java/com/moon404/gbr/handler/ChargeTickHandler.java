package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.common.ProjectileManager;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import com.mrcrayfish.guns.interfaces.IProjectileFactory;
import com.mrcrayfish.guns.item.GunItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class ChargeTickHandler
{
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event)
    {
        if (event.phase != Phase.END) return;
        if (event.side != LogicalSide.SERVER) return;
        Player player = event.player;
        CompoundTag compoundTag = player.getPersistentData();
        int charging = compoundTag.getInt("charging");
        if (charging <= 0) return;
        compoundTag.putInt("charging", charging - 1);

        Level world = player.level;
        ItemStack heldItem = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (heldItem.getItem() != GunBattleRoyaleItems.CHARGE_RIFLE.get()) return;
        GunItem item = (GunItem)heldItem.getItem();
        Gun modifiedGun = item.getModifiedGun(heldItem);
        if (modifiedGun == null) return;

        Gun.Projectile projectileProps = modifiedGun.getProjectile();
        IProjectileFactory factory = ProjectileManager.getInstance().getFactory(projectileProps.getItem());
        ProjectileEntity projectileEntity = factory.create(world, player, heldItem, item, modifiedGun);
        projectileEntity.setWeapon(heldItem);
        projectileEntity.setAdditionalDamage(Gun.getAdditionalDamage(heldItem));
        world.addFreshEntity(projectileEntity);
        projectileEntity.tick();
    }
}
