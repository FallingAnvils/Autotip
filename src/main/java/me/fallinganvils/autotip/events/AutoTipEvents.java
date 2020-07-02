package me.fallinganvils.autotip.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class AutoTipEvents {

    private boolean isOnHypixel = false;
    private boolean didFirstTimeTip = false;
    
    private int tipTickInterval = 20 * 60 * 15; // every 15 minutes
    private int tipTick = tipTickInterval;
    
    @SubscribeEvent
    public void onTick(ClientTickEvent event) {
        if(tipTick <= 0 && isOnHypixel) {
            tipTick = tipTickInterval;
            if(!doTip()) tipTick = 20 * 10; // try again in 10 seconds
        } else {
            tipTick--;
        }
    }
    
    @SubscribeEvent
    public void loggedIn(ClientConnectedToServerEvent event) {
        ServerData server = Minecraft.getMinecraft().getCurrentServerData();
        if(server.serverIP.toLowerCase().contains("hypixel.net")) {
            isOnHypixel = true;
        }
    }
    
    @SubscribeEvent
    public void chatReceived(ClientChatReceivedEvent event) {
        if(!didFirstTimeTip) {
            didFirstTimeTip = true;
            tipTick = 20 * 5;
        }
    }
    
    @SubscribeEvent
    public void loggedOut(ClientDisconnectionFromServerEvent event) {
        isOnHypixel = false;
        didFirstTimeTip = false;
    }
    
    private boolean doTip() {
        if(Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A7a\u00A7lAutotip is now tipping for you!"));
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/tipall");   
            return true;
        }
        else return false;
    }
    
}
