package com.desticc.createnewages.mixin;

import com.desticc.createnewages.content.stone_age.VillagerLevelData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin {
    private final VillagerLevelData customLevelData = new VillagerLevelData();

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    private void onRead(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("VillagerLevel")) {
            customLevelData.load(tag);
        }
    }
    @Inject(method="addAdditionalSaveData", at=@At("HEAD"))
    private void onSave(CompoundTag tag, CallbackInfo ci) {
        customLevelData.save(tag);
    }

    public VillagerLevelData getVillagerLevelData() {
        return customLevelData;
    }
    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        Villager self = (Villager)(Object)this;
        if (!self.level().isClientSide && self.tickCount % 100 == 0) {
            getVillagerLevelData().addXp(5);
            System.out.println("Villager " + self.getId() + " XP: " + getVillagerLevelData().getXp());
        }
    }
}