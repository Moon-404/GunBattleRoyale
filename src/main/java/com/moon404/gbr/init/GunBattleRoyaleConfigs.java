package com.moon404.gbr.init;

import net.minecraftforge.common.ForgeConfigSpec;

public class GunBattleRoyaleConfigs
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> RECOVER_INDEX;
    public static final ForgeConfigSpec.ConfigValue<Integer> SKILL_INDEX;

    static
    {
        RECOVER_INDEX = BUILDER.comment(" 回复品轮盘的快捷栏位（取值：0-9，0代表不启用此功能）").defineInRange("Recover hotbar", 4, 0, 9);
        SKILL_INDEX = BUILDER.comment(" 技能轮盘的快捷栏位（取值：0-9，0代表不启用此功能）").defineInRange("Skill hotbar", 5, 0, 9);
        SPEC = BUILDER.build();
    }
}
