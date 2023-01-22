package com.moon404.gbr;

import com.mrcrayfish.guns.item.attachment.impl.Scope;

public class Attachments
{
    public static final Scope SHORT_SCOPE = Scope.builder().aimFovModifier(0.7F).build();
    public static final Scope MEDIUM_SCOPE = Scope.builder().aimFovModifier(0.5F).build();
    public static final Scope LONG_SCOPE = Scope.builder().aimFovModifier(0.25F).build();
}
