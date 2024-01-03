package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;

public interface Zombie extends Entity {
    boolean isBaby();
    void setBaby(boolean baby);

    boolean isVillager();
    boolean setVillager(boolean villager);

}
