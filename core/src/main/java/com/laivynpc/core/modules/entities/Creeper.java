package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;

public interface Creeper extends Entity {
    boolean isPowered();
    void setPowered(boolean powered);
}
