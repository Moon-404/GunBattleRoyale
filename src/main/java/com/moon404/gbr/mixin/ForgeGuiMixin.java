package com.moon404.gbr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;

@Mixin(ForgeGui.class)
public class ForgeGuiMixin extends GuiMixin
{
    @Shadow
    private void bind(ResourceLocation res)
    {
        
    }
    @Shadow
    public int leftHeight;


    @Overwrite(remap = false)
    public void renderHealth(int width, int height, PoseStack pStack)
    {
        bind(GuiComponent.GUI_ICONS_LOCATION);
        Minecraft mc = Minecraft.getInstance();
        mc.getProfiler().push("health");
        RenderSystem.enableBlend();

        Player player = (Player) Minecraft.getInstance().getCameraEntity();
        int health = Mth.ceil(player.getHealth());
        boolean highlight = healthBlinkTime > (long) tickCount && (healthBlinkTime - (long) tickCount) / 3L % 2L == 1L;

        if (health < this.lastHealth && player.invulnerableTime > 0)
        {
            this.lastHealthTime = Util.getMillis();
            this.healthBlinkTime = (long) (this.tickCount + 20);
        }
        else if (health > this.lastHealth && player.invulnerableTime > 0)
        {
            this.lastHealthTime = Util.getMillis();
            this.healthBlinkTime = (long) (this.tickCount + 10);
        }

        if (Util.getMillis() - this.lastHealthTime > 1000L)
        {
            this.lastHealth = health;
            this.displayHealth = health;
            this.lastHealthTime = Util.getMillis();
        }

        this.lastHealth = health;
        int healthLast = this.displayHealth;

        AttributeInstance attrMaxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        float healthMax = Math.max((float) attrMaxHealth.getValue(), Math.max(healthLast, health));
        int absorb = Mth.ceil(player.getAbsorptionAmount());

        int healthRows = Mth.ceil((healthMax + absorb) / 2.0F / 10.0F);
        int rowHeight = Math.max(10 - (healthRows - 2), 3);

        this.random.setSeed((long) (tickCount * 312871));

        int left = width / 2 - 91;
        int top = height - leftHeight;
        leftHeight += (healthRows * rowHeight);
        if (rowHeight != 10) leftHeight += 10 - rowHeight;

        int regen = -1;
        if (player.hasEffect(MobEffects.REGENERATION))
        {
            regen = this.tickCount % Mth.ceil(healthMax + 5.0F);
        }

        int row = 1;
        for (int i = 0; i < 0; i++)
        {
            this.blit(pStack, left + i * 8, top - row * rowHeight, 16, 0, 9, 9);
        }
        this.renderHearts(pStack, player, left, top, rowHeight, regen, healthMax, health, healthLast, absorb, highlight);

        RenderSystem.disableBlend();
        mc.getProfiler().pop();
    }
}
