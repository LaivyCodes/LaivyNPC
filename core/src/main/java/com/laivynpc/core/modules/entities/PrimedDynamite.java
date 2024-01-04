package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;

public interface PrimedDynamite extends Entity {
    int getFuseTicks();
    void setFuseTicks(int ticks);
}
