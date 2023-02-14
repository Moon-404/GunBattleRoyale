package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.GameType;

@Mixin(Minecraft.class)
public class GlowingMixin
{
    @Shadow
    public LocalPlayer player;
    @Shadow
    public Options options;

    public boolean isAdventure(LocalPlayer localPlayer)
    {
        if (localPlayer == null) return false;
        ClientPacketListener cpl = Minecraft.getInstance().getConnection();
        if (cpl == null) return false;
        PlayerInfo playerinfo = cpl.getPlayerInfo(localPlayer.getGameProfile().getId());
        return playerinfo != null && playerinfo.getGameMode() == GameType.ADVENTURE;
    }

    @Overwrite
    public boolean shouldEntityAppearGlowing(Entity p_91315_)
    {
        // 高亮玩家（旁观者）
        if (this.player != null && this.player.isSpectator() && this.options.keySpectatorOutlines.isDown() && p_91315_.getType() == EntityType.PLAYER) return true;
        // 有发光效果必定发光
        if (p_91315_.isCurrentlyGlowing()) return true;
        // 自己[在战斗中]，显示所有[同队][玩家]
        if (isAdventure(this.player) && p_91315_.getType() == EntityType.PLAYER && this.player.getTeam() == p_91315_.getTeam()) return true;
        // 否则不发光
        return false;
    }
}
