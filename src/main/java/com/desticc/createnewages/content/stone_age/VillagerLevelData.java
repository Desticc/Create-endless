package com.desticc.createnewages.content.stone_age;

import net.minecraft.nbt.CompoundTag;

public class VillagerLevelData {
    private int level = 1;
    private int xp = 0;
    private int xpToNextLevel = 100;


    public void addXp(int amount) {
        xp += amount;
        while (xp >= xpToNextLevel) {
            xp -= xpToNextLevel;
            level++;
            xpToNextLevel = 100 + (level * 50);
        }
    }

    public int getLevel() {
        return level;
    }
    public int getXp () {
        return xp;
    }
    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public void save (CompoundTag tag) {
        tag.putInt("VillagerLevel", level);
        tag.putInt("VillagerXP", xp);
        tag.putInt("VillagerXPToNext", xpToNextLevel);
    }
    public void load(CompoundTag tag) {
        level = tag.getInt("VillagerLevel");
        xp = tag.getInt("VillagerXP");
        xpToNextLevel = tag.getInt("VillagerXPToNext");
    }
}