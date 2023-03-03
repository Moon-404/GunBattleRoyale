package com.moon404.gbr.handler;

import com.moon404.gbr.init.GunBattleRoyaleItems;
import com.mrcrayfish.guns.Config;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.common.ProjectileManager;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import com.mrcrayfish.guns.interfaces.IProjectileFactory;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.network.PacketHandler;
import com.mrcrayfish.guns.network.message.S2CMessageGunSound;
import com.mrcrayfish.guns.util.GunModifierHelper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.PacketDistributor;

public class ChargeTickHandler
{
    private static ResourceLocation getFireSound(ItemStack stack, Gun modifiedGun)
    {
        ResourceLocation fireSound = null;
        if (GunModifierHelper.isSilencedFire(stack))
        {
            fireSound = modifiedGun.getSounds().getSilencedFire();
        }
        else if (stack.isEnchanted())
        {
            fireSound = modifiedGun.getSounds().getEnchantedFire();
        }
        if (fireSound != null)
        {
            return fireSound;
        }
        return modifiedGun.getSounds().getFire();
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event)
    {
        if (event.phase != Phase.END) return;
        if (event.side != LogicalSide.SERVER) return;
        Player player = event.player;
        CompoundTag compoundTag = player.getPersistentData();
        int charging = compoundTag.getInt("charging");
        if (charging <= 0) return;
        System.out.println(charging);
        compoundTag.putInt("charging", charging - 1);

        Level world = player.level;
        ItemStack heldItem = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (heldItem.getItem() != GunBattleRoyaleItems.CHARGE_RIFLE.get()) return;
        
        if (heldItem.getItem() instanceof GunItem item)
        {
            Gun modifiedGun = item.getModifiedGun(heldItem);
            if (modifiedGun != null)
            {
                int count = modifiedGun.getGeneral().getProjectileAmount();
                Gun.Projectile projectileProps = modifiedGun.getProjectile();
                ProjectileEntity[] spawnedProjectiles = new ProjectileEntity[count];
                for (int i = 0; i < count; i++)
                {
                    IProjectileFactory factory = ProjectileManager.getInstance().getFactory(projectileProps.getItem());
                    ProjectileEntity projectileEntity = factory.create(world, player, heldItem, item, modifiedGun);
                    projectileEntity.setWeapon(heldItem);
                    projectileEntity.setAdditionalDamage(Gun.getAdditionalDamage(heldItem));
                    world.addFreshEntity(projectileEntity);
                    spawnedProjectiles[i] = projectileEntity;
                    projectileEntity.tick();
                }

                ResourceLocation fireSound = getFireSound(heldItem, modifiedGun);
                if (fireSound != null)
                {
                    double posX = player.getX();
                    double posY = player.getY() + player.getEyeHeight();
                    double posZ = player.getZ();
                    float volume = GunModifierHelper.getFireSoundVolume(heldItem);
                    float pitch = 0.9F + world.random.nextFloat() * 0.2F;
                    double radius = GunModifierHelper.getModifiedFireSoundRadius(heldItem, Config.SERVER.gunShotMaxDistance.get());
                    boolean muzzle = modifiedGun.getDisplay().getFlash() != null;
                    S2CMessageGunSound messageSound = new S2CMessageGunSound(fireSound, SoundSource.PLAYERS, (float) posX, (float) posY, (float) posZ, volume, pitch, player.getId(), muzzle, false);
                    PacketDistributor.TargetPoint targetPoint = new PacketDistributor.TargetPoint(posX, posY, posZ, radius, player.level.dimension());
                    PacketHandler.getPlayChannel().send(PacketDistributor.NEAR.with(() -> targetPoint), messageSound);
                }
            }
        }
    }
}
