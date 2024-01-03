package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;

public interface Sheep extends Entity {
    boolean isSheared();
    void setSheared(boolean sheared);
}
