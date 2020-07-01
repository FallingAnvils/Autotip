package me.fallinganvils.autotip;

import me.fallinganvils.autotip.events.AutoTipEvents;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AutoTip.MODID, version = AutoTip.VERSION, acceptedMinecraftVersions="[1.8.9]")
public class AutoTip {

    public static final String MODID = "AutoTip";
    public static final String VERSION = "0.1";

    @Mod.Instance
    private static AutoTip instance;
    

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new AutoTipEvents());
    }
}
