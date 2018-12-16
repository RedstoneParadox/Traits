package net.redstoneparadox.traits

import net.fabricmc.api.ModInitializer
import net.redstoneparadox.traits.traitspackage.Traits

/**
 * Created by RedstoneParadox on 12/12/2018.
 */
class TraitsMain : ModInitializer {

    val traits : Traits = Traits()

    override fun onInitialize() {

        traits.initTraits()
    }

    companion object Instance {
        const val MOD_ID : String = "traitspackage"
    }

}