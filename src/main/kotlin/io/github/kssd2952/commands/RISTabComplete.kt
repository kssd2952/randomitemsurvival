package io.github.kssd2952.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class RISTabComplete : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender, command: Command, label: String, args: Array<out String>
    ): List<String?>? {
        if (command.name != "ris") return null

        return when (args.size) {
            1 -> listOf("start", "stop", "friend").filter {
                it.startsWith(
                    args[0], ignoreCase = true
                )
            }.toMutableList()


            2 -> if (args[0] == "friend") {
                mutableListOf("true", "false")
            } else {
                mutableListOf()
            }

            else -> mutableListOf()
        }
    }
}