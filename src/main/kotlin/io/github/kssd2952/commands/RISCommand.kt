package io.github.kssd2952.commands

import io.github.kssd2952.Main
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class RISCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("잘못된 인자입니다")
        }

        when (args[0].lowercase()) {
            "start" -> {

            }

            "stop" -> {

            }

            "friend" -> {
                if (args.size < 2) {
                    sender.sendMessage("잘못된 인자입니다")
                    return true
                }

                if (args[1] == "true") {
                    Main.friend = true
                    sender.sendMessage("friend 설정값을 true로 설정합니다")
                } else if (args[1] == "false") {
                    Main.friend = false
                    sender.sendMessage("friend 설정값을 false로 설정합니다")
                } else {
                    sender.sendMessage("잘못된 인자입니다")
                }
            }
        }
        return true
    }
}