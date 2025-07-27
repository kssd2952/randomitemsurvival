package io.github.kssd2952.event

import io.github.kssd2952.Main
import io.papermc.paper.event.player.PlayerPickItemEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.player.PlayerItemConsumeEvent

class RISEvent : Listener {
    @EventHandler
    fun e(event: EntityPickupItemEvent) {
        if (event.entity !is Player) return
        if (!Main.started) return

        val player = event.entity as Player
        val item = event.item.itemStack

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