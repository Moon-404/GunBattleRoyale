package com.moon404.gbr.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.moon404.gbr.item.Nemesis;
import com.moon404.gbr.item.R2R5Item;
import com.moon404.gbr.item.recover.MedKit;
import com.moon404.gbr.item.recover.PhoenixKit;
import com.moon404.gbr.item.recover.RecoverItem;
import com.moon404.gbr.item.recover.Reviver;
import com.moon404.gbr.item.recover.ShieldBattery;
import com.moon404.gbr.item.recover.ShieldBoost;
import com.moon404.gbr.item.recover.ShieldCell;
import com.moon404.gbr.item.recover.Syringe;
import com.moon404.gbr.item.skill.Boot;
import com.moon404.gbr.item.skill.Charge;
import com.moon404.gbr.item.skill.EnterVoid;
import com.moon404.gbr.item.skill.Exhibit;
import com.moon404.gbr.item.skill.Fast;
import com.moon404.gbr.item.skill.Glow;
import com.moon404.gbr.item.skill.HealthBottle;
import com.moon404.gbr.item.skill.Ire;
import com.moon404.gbr.item.skill.Lift;
import com.moon404.gbr.item.skill.Pearl;
import com.moon404.gbr.item.skill.Purify;
import com.moon404.gbr.item.skill.Scan;
import com.moon404.gbr.item.skill.ShieldBottle;
import com.moon404.gbr.item.skill.Silence;
import com.moon404.gbr.item.skill.SkillBag;
import com.moon404.gbr.item.skill.Snare;
import com.moon404.gbr.item.skill.Stim;
import com.moon404.gbr.item.skill.Totem;
import com.moon404.gbr.item.skill.Trace;
import com.mrcrayfish.guns.item.*;
import com.mrcrayfish.guns.item.attachment.impl.*;

public class GunBattleRoyaleItems
{
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, "gbr");
    public static final GunBattleRoyaleTab GROUP = new GunBattleRoyaleTab("gbr");

    public static final RegistryObject<Item> CHARGE_RIFLE = REGISTER.register("charge_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(GROUP)));
    public static final RegistryObject<Item> NEMESIS = REGISTER.register("nemesis", () -> new Nemesis(new Item.Properties().stacksTo(1).tab(GROUP)));

    public static final RegistryObject<Item> SPECIAL_BULLET = REGISTER.register("special_bullet", () -> new AmmoItem(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> CHARGE_BULLET = REGISTER.register("charge_bullet", () -> new AmmoItem(new Item.Properties().tab(GROUP)));

    public static final RegistryObject<Item> SHORT_SCOPE = REGISTER.register("short_scope", () -> new ScopeItem(Attachments.SHORT_SCOPE, new Item.Properties().stacksTo(1).tab(GROUP)));
    public static final RegistryObject<Item> MEDIUM_SCOPE = REGISTER.register("medium_scope", () -> new ScopeItem(Attachments.MEDIUM_SCOPE, new Item.Properties().stacksTo(1).tab(GROUP)));
    public static final RegistryObject<Item> LONG_SCOPE = REGISTER.register("long_scope", () -> new ScopeItem(Attachments.LONG_SCOPE, new Item.Properties().stacksTo(1).tab(GROUP)));

    public static final RegistryObject<Item> STABILIZER = REGISTER.register("stabilizer", () -> new BarrelItem(Barrel.create(8.0F, GunModifiers.STABILIZER), new Item.Properties().stacksTo(1).tab(GROUP)));
    public static final RegistryObject<Item> ADVANCED_STABILIZER = REGISTER.register("advanced_stabilizer", () -> new BarrelItem(Barrel.create(8.0F, GunModifiers.ADVANCED_STABILIZER), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).tab(GROUP)));

    public static final RegistryObject<Item> STOCK = REGISTER.register("stock", () -> new StockItem(Stock.create(GunModifiers.STOCK), new Item.Properties().stacksTo(1).tab(GROUP), false));
    public static final RegistryObject<Item> ADVANCED_STOCK = REGISTER.register("advanced_stock", () -> new StockItem(Stock.create(GunModifiers.ADVANCED_STOCK), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).tab(GROUP), false));

    public static final RegistryObject<Item> GRIP = REGISTER.register("grip", () -> new UnderBarrelItem(UnderBarrel.create(GunModifiers.GRIP), new Item.Properties().stacksTo(1).tab(GROUP)));
    public static final RegistryObject<Item> ADVANCED_GRIP = REGISTER.register("advanced_grip", () -> new UnderBarrelItem(UnderBarrel.create(GunModifiers.ADVANCED_GRIP), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).tab(GROUP)));

    public static final RegistryObject<Item> VERSION = REGISTER.register("1.3.3.36d", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> R2R5 = REGISTER.register("r2r5", () -> new R2R5Item(new Item.Properties().stacksTo(1).tab(GROUP)));

    public static final RegistryObject<Item> SHIELD_CELL = REGISTER.register("shield_cell", () -> new ShieldCell(new Item.Properties().food(RecoverItem.RECOVER_ITEM).tab(GROUP)));
    public static final RegistryObject<Item> SHIELD_BATTERY = REGISTER.register("shield_battery", () -> new ShieldBattery(new Item.Properties().food(RecoverItem.RECOVER_ITEM).tab(GROUP)));
    public static final RegistryObject<Item> SYRINGE = REGISTER.register("syringe", () -> new Syringe(new Item.Properties().food(RecoverItem.RECOVER_ITEM).tab(GROUP)));
    public static final RegistryObject<Item> MED_KIT = REGISTER.register("med_kit", () -> new MedKit(new Item.Properties().food(RecoverItem.RECOVER_ITEM).tab(GROUP)));
    public static final RegistryObject<Item> PHOENIX_KIT = REGISTER.register("phoenix_kit", () -> new PhoenixKit(new Item.Properties().food(RecoverItem.RECOVER_ITEM).tab(GROUP)));
    public static final RegistryObject<Item> SHIELD_BOOST = REGISTER.register("shield_boost", () -> new ShieldBoost(new Item.Properties().food(RecoverItem.RECOVER_ITEM).tab(GROUP)));
    public static final RegistryObject<Item> REVIVER = REGISTER.register("reviver", () -> new Reviver(new Item.Properties().food(RecoverItem.RECOVER_ITEM).tab(GROUP)));

    public static final RegistryObject<Item> STIM = REGISTER.register("stim", () -> new Stim(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> LIFT = REGISTER.register("lift", () -> new Lift(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> GLOW = REGISTER.register("glow", () -> new Glow(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> VOID = REGISTER.register("void", () -> new EnterVoid(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> HEALTH_BOTTLE = REGISTER.register("health_bottle", () -> new HealthBottle(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> SHIELD_BOTTLE = REGISTER.register("shield_bottle", () -> new ShieldBottle(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> PEARL = REGISTER.register("pearl", () -> new Pearl(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> TOTEM = REGISTER.register("totem", () -> new Totem(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> FAST = REGISTER.register("fast", () -> new Fast(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> IRE = REGISTER.register("ire", () -> new Ire(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> SCAN = REGISTER.register("scan", () -> new Scan(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> PURIFY = REGISTER.register("purify", () -> new Purify(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> CHARGE = REGISTER.register("charge", () -> new Charge(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> EXHIBIT = REGISTER.register("exhibit", () -> new Exhibit(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> SNARE = REGISTER.register("snare", () -> new Snare(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> SILENCE = REGISTER.register("silence", () -> new Silence(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> TRACE = REGISTER.register("trace", () -> new Trace(new Item.Properties().tab(GROUP)));
    public static final RegistryObject<Item> BOOT = REGISTER.register("boot", () -> new Boot(new Item.Properties().tab(GROUP)));
    // skill_bag 必须最后一个注册
    public static final RegistryObject<Item> SKILL_BAG = REGISTER.register("skill_bag", () -> new SkillBag(new Item.Properties().tab(GROUP)));
}
