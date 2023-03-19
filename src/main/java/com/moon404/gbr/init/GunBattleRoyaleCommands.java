package com.moon404.gbr.init;

import com.moon404.gbr.ClassCommand;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = "gbr", bus = Bus.FORGE)
public class GunBattleRoyaleCommands
{
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event)
    {
        ClassCommand.register(event.getDispatcher());
    }
}
