package io.github.jorge.anglercraft.entity.render;

import io.github.jorge.anglercraft.entity.EntityRainbowTrout;
import io.github.jorge.anglercraft.entity.model.RainbowTroutModel;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderRainbowTrout extends GeoEntityRenderer<EntityRainbowTrout> {

    public RenderRainbowTrout(RenderManager renderManager) {
        super(renderManager, new RainbowTroutModel());
    }

    
    
}
