package net.redstoneparadox.traits

import net.fabricmc.api.ModInitializer
import net.redstoneparadox.traits.traitspackage.TraitFactory

/**
 * Created by RedstoneParadox on 12/12/2018.
 */
class TraitsMain : ModInitializer {

    override fun onInitialize() {

        TraitFactory.initialize()
    }

    companion object Instance {
        const val MOD_ID : String = "traitspackage"
    }

}