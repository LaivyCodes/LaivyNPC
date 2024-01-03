package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;

public interface Guardian extends Entity {
    boolean isElder();
    void setElder(boolean elder);
}
