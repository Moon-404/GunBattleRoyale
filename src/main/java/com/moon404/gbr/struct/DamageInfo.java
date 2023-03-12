package com.moon404.gbr.struct;

public class DamageInfo
{
    public float amount;
    public float startTick;
    public int color;

    public DamageInfo()
    {
        amount = 0;
        startTick = 0;
        getArmorColor(0);
    }

    public void getArmorColor(int level)
    {
        switch (level)
        {
            case 1:
                this.color = 0xFFFFFF;
                return;
            case 2:
                this.color = 0x0000FF;
                return;
            case 3:
                this.color = 0xFF00FF;
                return;
            case 4:
                this.color = 0xFFFF00;
                return;
            default:
                this.color = 0xFF0000;
                return;
        }
    }
}
