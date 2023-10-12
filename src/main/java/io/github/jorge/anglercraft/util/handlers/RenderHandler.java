package io.github.jorge.anglercraft.util.handlers;

import io.github.jorge.anglercraft.entity.EntityRainbowTrout;
import io.github.jorge.anglercraft.entity.render.RenderRainbowTrout;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.GeckoLib;

public class RenderHandler {
    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRainbowTrout.class, RenderRainbowTrout::new);
            
    }

    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void registerReplacedRenderers(){
        GeckoLib.initialize();
        //RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        
        
    }
}
    
