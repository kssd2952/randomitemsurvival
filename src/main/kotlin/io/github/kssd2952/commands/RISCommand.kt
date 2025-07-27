package io.github.kssd2952.commands

import io.github.kssd2952.Main
import io.github.kssd2952.event.GameManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Sound
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
                if (Bukkit.getOnlinePlayers().isEmpty()) {
                    sender.sendMessage("플레이어가 없습니다")
                    return true
                }

                Main.started = true
                Main.players = Bukkit.getOnlinePlayers().size

                val materialList = Material.entries.filter { it.isLegacy.not() } // 블록 제외 및 레거시 아이템 제외
                for (player in Bukkit.getOnlinePlayers()) {
                    Main.itemList[player.name] = materialList[Random.nextInt(materialList.size)]
                    player.sendMessage(Component.text("랜덤 아이템 빨리찾기 게임이 시작되었습니다!", NamedTextColor.GREEN))

                    player.gameMode = GameMode.SURVIVAL
                    player.playSound(player.location, Sound.BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE, 1f, 1f)
                }

                for (player in Bukkit.getOnlinePlayers()) {
                    if (Main.friend) {
                        val matMessage = Component.text(
                            "플레이어 ${player.name}의 지정 아이템은 ${Main.itemList[player.name]?.name}입니다!",
                            NamedTextColor.YELLOW
                        )

                        for (sendPlayer in Bukkit.getOnlinePlayers()) {
                            sendPlayer.sendMessage(matMessage)
                        }
                    } else {
                        player.sendMessage(
                            Component.text(
                                "당신의 지정 아이템은 ${Main.itemList[player.name]?.name}입니다!", NamedTextColor.YELLOW
                            )
                        )
                    }
                }


            }

            "stop" -> {
                if (Bukkit.getOnlinePlayers().isEmpty()) {
                    sender.sendMessage("플레이어가 없습니다")
                    return true
                }

                GameManager.gameStop()
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

            //TODO: 플레이어가 죽었을 때 게임에서 탈락하는 옵션 추가
        }
        return true
    }
}