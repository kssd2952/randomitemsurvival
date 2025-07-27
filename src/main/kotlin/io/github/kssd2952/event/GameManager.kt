package io.github.kssd2952.event

import io.github.kssd2952.Main
import io.github.kssd2952.Main.Companion.players
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.entity.Player

class GameManager {
    companion object {
        fun playerFinished(player: Player) {
            player.gameMode = GameMode.SPECTATOR

            val matMessage = Component.text(
                "플레이어 ${player.name}이 지정 아이템 ${Main.itemList[player.name]?.name}을 획득했습니다!", NamedTextColor.BLUE
            )
            for (sendPlayer in Bukkit.getOnlinePlayers()) {
                sendPlayer.sendMessage(matMessage)
            }

            Main.itemList.remove(player.name)

            if (players == 0) {
                gameStop()

                for (player in Bukkit.getOnlinePlayers()) {
                    player.playSound(player.location, Sound.ITEM_MACE_SMASH_GROUND_HEAVY, 1f, 1f)
                }
            } else {
                players--

                for (player in Bukkit.getOnlinePlayers()) {
                    player.playSound(player.location, Sound.BLOCK_TRIAL_SPAWNER_OPEN_SHUTTER, 1f, 2f)
                }
            }
        }

        fun gameStop() {
            for (player in Bukkit.getOnlinePlayers()) {
                player.sendMessage(Component.text("게임이 종료되었습니다!", NamedTextColor.GREEN))
            }

            Main.itemList.clear()
            Main.started = false
        }
    }
}