package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;

public interface Bat extends Entity {
    boolean isAwake();
    void setAwake(boolean awake);
}
