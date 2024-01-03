package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;

public interface PigZombie extends Entity {
    boolean isAngry();
    boolean setAngry(boolean angry);
}
