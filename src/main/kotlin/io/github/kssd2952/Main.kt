package io.github.kssd2952

import io.github.kssd2952.commands.RISCommand
import io.github.kssd2952.commands.RISTabComplete
import io.github.kssd2952.event.RISEvent
import org.bukkit.Material
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        val itemList = mutableMapOf<String, Material>()
        var friend = true
        var started = false
    }

    override fun onEnable() {
        logger.info("랜덤 아이템 빨리찾기 플러그인 v" + pluginMeta.version)
        getCommand("ris")?.setExecutor(RISCommand())
        getCommand("ris")?.tabCompleter = RISTabComplete()
        server.pluginManager.registerEvents(RISEvent(), this)
    }
}