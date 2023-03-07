package com.moon404.gbr.struct;

public class ArmorInfo
{
    public static ArmorInfo instance = new ArmorInfo();

    public int level;
    public float upgrade;

    public float getLevelUpgrade()
    {
        if (level == 0) return 30;
        if (level == 1) return 60;
        if (level == 2) return 150;
        return Float.POSITIVE_INFINITY;
    }

    public ArmorInfo()
    {
        level = -2;
        upgrade = getLevelUpgrade();
    }
    
    public void addUpgrade(float amount)
    {
        upgrade -= amount;
        if (upgrade <= 0)
        {
            level += 1;
            upgrade += getLevelUpgrade();
        }
    }
}
