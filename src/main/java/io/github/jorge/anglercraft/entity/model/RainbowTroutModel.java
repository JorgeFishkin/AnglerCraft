package io.github.jorge.anglercraft.entity.model;

import io.github.jorge.anglercraft.entity.EntityRainbowTrout;
import io.github.jorge.anglercraft.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class RainbowTroutModel extends AnimatedTickingGeoModel<EntityRainbowTrout> {

    @Override
    public ResourceLocation getAnimationFileLocation(EntityRainbowTrout entity) {
       return new ResourceLocation(Reference.MODID, "animation/rainbow_trout.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(EntityRainbowTrout object) {
        return new ResourceLocation(Reference.MODID, "geo/rainbow_trout.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityRainbowTrout object) {
        return new ResourceLocation(Reference.MODID, "textures/model/entity/trout_rainbow.png");
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setLivingAnimations(EntityRainbowTrout entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
