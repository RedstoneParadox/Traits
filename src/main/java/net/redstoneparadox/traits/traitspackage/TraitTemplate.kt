package net.redstoneparadox.traits.traitspackage

import java.lang.reflect.Constructor

/**
 * Created by RedstoneParadox on 12/16/2018.
 */
class TraitTemplate {

    lateinit var constructor : Constructor<Trait>
    var weight: Int = 1
    var tickable: Boolean = false
    var maxLevel: Int = 1

    constructor(trait: Trait, weight: Int, tickable: Boolean = false, maxLevel: Int = 1) {

        this.constructor = trait.javaClass.getDeclaredConstructor(String::class.java, Boolean::class.java, Int::class.java)
        this.weight = weight
        this.tickable = tickable
        this.maxLevel = maxLevel
    }
}