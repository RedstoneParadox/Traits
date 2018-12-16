package net.redstoneparadox.traits.traitspackage

import net.minecraft.entity.mob.MobEntity

/**
 * Created by RedstoneParadox on 12/14/2018.
 */
open class Trait {

    var isTemplate : Boolean = true

    var name : String
    var weight: Int = 0
    var tickable : Boolean = false
    var level : Int = 1
    var maxLevel : Int = 1

    constructor(name : String, weight: Int, tickable: Boolean, minLevel : Int, maxLevel : Int) {
        this.name = name
        this.weight = weight
        this.tickable = tickable
        this.level = minLevel
        this.maxLevel = maxLevel
    }

    private constructor(name: String, tickable: Boolean, level : Int) {
        this.name = name
        this.tickable = tickable
        this.level = level
        this.isTemplate = false
    }

    fun build(level: Int) : Trait? {
        if (isTemplate) {
            return Trait("trait", tickable, level)
        }
        else {
            return null
        }
    }

    open fun tickTarget(target: MobEntity) {

    }

}