package net.redstoneparadox.traits.traitspackage

import net.minecraft.entity.LivingEntity

/**
 * Created by RedstoneParadox on 12/14/2018.
 */
open class BaseTrait(var weight: Int, var tickable: Boolean, var name : String) {

    lateinit var targets : ArrayList<LivingEntity>

    fun addTarget(target : LivingEntity) {
        targets.add(target)
    }

    fun removeTarget(target: LivingEntity) {
        targets.remove(target)
    }

    open fun copy() : BaseTrait {
        return BaseTrait(0, false, "")
    }

    fun onTick() {
        if (tickable) {
            for (target in targets) {
                tickTarget(target)
            }
        }
    }

    open fun tickTarget(target: LivingEntity) {

    }

}