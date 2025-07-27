package io.github.kssd2952.commands

import io.github.kssd2952.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import kotlin.random.Random

class RISCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("잘못된 인자입니다")
        }

        when (args[0].lowercase()) {
            "start" -> {
                val materialList = Material.entries.filter { it.isLegacy.not() } // 블록 제외 및 레거시 아이템 제외
                for (player in Bukkit.getOnlinePlayers()) {
                    Main.itemList[player.name] = materialList[Random.nextInt(materialList.size)]
                    player.sendMessage(Component.text("랜덤 아이템 빨리찾기 게임이 시작되었습니다!", NamedTextColor.GREEN))
                }

                if(Main.friend) {
                }
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