package io.github.jorge.anglercraft.entity;

import io.github.jorge.anglercraft.entity.ai.EntityFishSwim;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.world.World;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityRainbowTrout extends EntityFishMob implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = new AnimationFactory(this);
    //private EntityAILookIdle idle = new EntityAILookIdle(this);


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.isHungry()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rainbow_trout.topwater"));
            System.out.println("Trying eat");
            this.setHunger(100);
            return PlayState.CONTINUE;
        }
        else if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rainbow_trout.swim"));
            return PlayState.CONTINUE;
        }
        else if(!this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rainbow_trout.flop"));
            return PlayState.CONTINUE;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rainbow_trout.idle"));
            return PlayState.CONTINUE;
        }
    }

    public EntityRainbowTrout(World worldIn) {
        super(worldIn);
        
        this.setSize(1F, 0.6F);
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<EntityRainbowTrout>(this, "controller", 50, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    @Override
    protected void initEntityAI() {

            this.tasks.addTask(0, new EntityAILookIdle(this));
            //this.tasks.addTask(7, new EntityFishSwim(this, 0.5F));
            this.tasks.addTask(7, new EntityFishSwim(this, 1F, 1));
            super.initEntityAI();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }

    @Override
    public int tickTimer() {
        return ticksExisted;
    }

    @Override
    public void tick() {
        super.onUpdate();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
    }
    
}
