package net.redstoneparadox.traits.traitspackage

import net.minecraft.entity.mob.MobEntity

/**
 * Created by RedstoneParadox on 12/14/2018.
 */
open class Trait() {

    lateinit var name : String
    var tickable : Boolean = false
    var level : Int = 1

    constructor(name: String, tickable: Boolean, level : Int) : this() {
        this.name = name
        this.tickable = tickable
        this.level = level
    }

    open fun tickTarget(target: MobEntity) {

    }

}