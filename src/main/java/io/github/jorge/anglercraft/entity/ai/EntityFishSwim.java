package io.github.jorge.anglercraft.entity.ai;

import javax.annotation.Nullable;

import io.github.jorge.anglercraft.entity.EntityFishMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class EntityFishSwim extends EntityAIBase {
    private final EntityFishMob entity;
    protected double x;
    protected double y;
    protected double z;
    protected final double speed;
    protected int executionChance;
    protected boolean mustUpdate;

    public EntityFishSwim(EntityFishMob entityIn, double speedIn){
        this(entityIn, speedIn, 120);
    }

    public EntityFishSwim(EntityFishMob entityIn, double speedIn, int chance){
        this.entity = entityIn;
        this.speed = speedIn;
        this.executionChance = chance;
        this.setMutexBits(1);

    }

    @Override
    public boolean shouldExecute() {
        if(this.entity.isInWater() || this.entity.isInLava()) {
            if (!this.mustUpdate)
            {
                /*if (this.entity.getIdleTime() >= 100)
                {
                    return false;
                }*/
    
                if (this.entity.getRNG().nextInt(this.executionChance) != 0)
                {
                    return false;
                }
            }
    
            Vec3d vec3d = this.getPosition();
            
    
            if (vec3d == null)
            {
                return false;
            }
            else
            {
                this.x = vec3d.x;
                this.y = vec3d.y;
                this.z = vec3d.z;
                this.mustUpdate = false;
                return true;
            }
        }
        else
            return false;
    }

        @Nullable
    protected Vec3d getPosition()
    {
        return RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
    }

    public boolean shouldContinueExecuting()
    {
        return !this.entity.getNavigator().noPath();
    }

    public void startExecuting()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.speed);
    }

    public void makeUpdate()
    {
        this.mustUpdate = true;
    }

    public void setExecutionChance(int newchance)
    {
        this.executionChance = newchance;
    }
    
}
