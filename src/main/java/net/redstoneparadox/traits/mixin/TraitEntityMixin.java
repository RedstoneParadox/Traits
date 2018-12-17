package net.redstoneparadox.traits.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.World;
import net.redstoneparadox.traits.traitspackage.ITraitEntity;
import net.redstoneparadox.traits.traitspackage.Trait;
import net.redstoneparadox.traits.traitspackage.TraitFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

/**
 * Created by RedstoneParadox on 12/14/2018.
 */

@Mixin(MobEntity.class)
public abstract class TraitEntityMixin extends LivingEntity implements ITraitEntity {

    public ArrayList<Trait> traits = new ArrayList<>();

    public TraitEntityMixin(EntityType<?> var1, World var2) {
        super(var1, var2);
    }

    @Inject(at = @At("HEAD"), method = "updateLogic")
    protected void updateLogic(CallbackInfo info) {

        if (!traits.isEmpty()) {

            for (Trait trait : traits) {
                if (trait.getTickable()) {

                    trait.tickTarget((MobEntity) (Object) this);
                }
            }

        }
    }

    @Inject(at = @At("HEAD"), method = "readCustomDataFromTag")
    public void readCustomDataFromTag(CompoundTag var1, CallbackInfo info) {
        if (var1.containsKey("Traits")) {

            ListTag traitsTag = var1.getList("Traits", 9);

            traits = TraitFactory.INSTANCE.getTraitsFromTag(traitsTag);
        }
        else {
            traits = TraitFactory.INSTANCE.applyRandomTraits(this);
        }
    }

    @Inject(at = @At("HEAD"), method = "writeCustomDataToTag")
    public void writeCustomDataToTag(CompoundTag var1, CallbackInfo info) {

        ListTag traitsTag = new ListTag();

        for (Trait trait : traits) {

            String name = trait.getName();

            traitsTag.add(new StringTag(name));
        }

        var1.put("Traits", traitsTag);
    }

    @Override
    public void addTrait(Trait trait) {
        traits.add(trait);
    }
}
