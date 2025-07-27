package io.github.kssd2952.event

import io.github.kssd2952.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent

class RISEvent : Listener {
    @EventHandler
    fun onPlayerItemConsume(event: PlayerItemConsumeEvent) {
        if (!Main.started) {
            return
        }

        val player = event.player
        val item = event.item

        if (item.type == Main.itemList[player.name]) {
            player.gameMode = GameMode.SPECTATOR

            val matMessage = Component.text(
                "플레이어 ${player.name}이 지정 아이템 ${Main.itemList[player.name]?.name}을 획득했습니다!", NamedTextColor.BLUE
            )
            for (sendPlayer in Bukkit.getOnlinePlayers()) {
                sendPlayer.sendMessage(matMessage)
            }

            Main.itemList.remove(player.name)
        }
    }
}