package com.moon404.gbr.struct;

import net.minecraft.world.phys.Vec3;

public class LaserInfo
{
    public static final int DURATION_TICK = 11;

    public float startTick;
    public Vec3 from;
    public float length;
    public float xRot;
    public float yRot;
    public int aiming;
    public float size;
    public int isShooter;

    public LaserInfo()
    {
        this.startTick = -1;
        this.from = new Vec3(0, 0, 0);
    }
}
