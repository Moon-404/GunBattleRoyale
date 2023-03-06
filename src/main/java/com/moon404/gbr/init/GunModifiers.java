package com.moon404.gbr.init;

import com.mrcrayfish.guns.interfaces.IGunModifier;

public class GunModifiers
{
    public static final IGunModifier STABILIZER = new IGunModifier()
    {
        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.8F;
        }
        @Override
        public double modifyProjectileGravity(double gravity)
        {
            return -0.03;
        }
    };
    public static final IGunModifier ADVANCED_STABILIZER = new IGunModifier()
    {
        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.6F;
        }
        @Override
        public double modifyProjectileGravity(double gravity)
        {
            return -0.02;
        }
    };
    public static final IGunModifier GRIP = new IGunModifier()
    {
        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 1.2F;
        }
    };
    public static final IGunModifier ADVANCED_GRIP = new IGunModifier()
    {
        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 1.4F;
        }
    };
    public static final IGunModifier STOCK = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.8F;
        }
        @Override
        public int modifyFireRate(int rate)
        {
            if (rate >= 10) rate -= 1;
            return rate;
        }
        @Override
        public float kickModifier()
        {
            return 0.8F;
        }
    };
    public static final IGunModifier ADVANCED_STOCK = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.6F;
        }
        @Override
        public int modifyFireRate(int rate)
        {
            if (rate >= 10) rate -= 2;
            return rate;
        }
        @Override
        public float kickModifier()
        {
            return 0.6F;
        }
    };
}
