package com.moon404.gbr.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.mrcrayfish.guns.item.*;
import com.mrcrayfish.guns.item.attachment.impl.*;

public class GunBattleRoyaleItems
{
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, "gbr");
    public static final GunBattleRoyaleTab GROUP = new GunBattleRoyaleTab("gbr");

    public static final RegistryObject<Item> CHARGE_RIFLE = REGISTER.register("charge_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(GROUP)));

    public static final RegistryObject<Item> SPECIAL_BULLET = REGISTER.register("special_bullet", () -> new AmmoItem(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> CHARGE_BULLET = REGISTER.register("charge_bullet", () -> new AmmoItem(new Item.Properties().tab(GROUP)));

    public static final RegistryObject<Item> SHORT_SCOPE = REGISTER.register("short_scope", () -> new ScopeItem(Attachments.SHORT_SCOPE, new Item.Properties().stacksTo(1).tab(GROUP)));
    public static final RegistryObject<Item> MEDIUM_SCOPE = REGISTER.register("medium_scope", () -> new ScopeItem(Attachments.MEDIUM_SCOPE, new Item.Properties().stacksTo(1).tab(GROUP)));
    public static final RegistryObject<Item> LONG_SCOPE = REGISTER.register("long_scope", () -> new ScopeItem(Attachments.LONG_SCOPE, new Item.Properties().stacksTo(1).tab(GROUP)));

    public static final RegistryObject<Item> STABILIZER = REGISTER.register("stabilizer", () -> new BarrelItem(Barrel.create(8.0F, GunModifiers.STABILIZER), new Item.Properties().stacksTo(1).tab(GROUP)));
    public static final RegistryObject<Item> ADVANCED_STABILIZER = REGISTER.register("advanced_stabilizer", () -> new BarrelItem(Barrel.create(8.0F, GunModifiers.ADVANCED_STABILIZER), new Item.Properties().stacksTo(1).tab(GROUP)));

    public static final RegistryObject<Item> STOCK = REGISTER.register("stock", () -> new StockItem(Stock.create(GunModifiers.STOCK), new Item.Properties().stacksTo(1).tab(GROUP), false));
    public static final RegistryObject<Item> ADVANCED_STOCK = REGISTER.register("advanced_stock", () -> new StockItem(Stock.create(GunModifiers.ADVANCED_STOCK), new Item.Properties().stacksTo(1).tab(GROUP), false));

    public static final RegistryObject<Item> GRIP = REGISTER.register("grip", () -> new UnderBarrelItem(UnderBarrel.create(GunModifiers.GRIP), new Item.Properties().stacksTo(1).tab(GROUP)));
    public static final RegistryObject<Item> ADVANCED_GRIP = REGISTER.register("advanced_grip", () -> new UnderBarrelItem(UnderBarrel.create(GunModifiers.ADVANCED_GRIP), new Item.Properties().stacksTo(1).tab(GROUP)));

    public static final RegistryObject<Item> VERSION = REGISTER.register("1.3.3.31", () -> new Item(new Item.Properties()));
}
