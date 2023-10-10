package io.github.jorge.anglercraft.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFishMob extends EntityAnimal {

    public EntityFishMob(World worldIn) {
        super(worldIn);
        this.ignoreFrustumCheck = true;
        
    }

     protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateSwimmer(this, worldIn);
    }

    public boolean isPushedByWater() {
        return false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public void onEntityUpdate() {
        int a = this.getAir();
        super.onEntityUpdate();
        checkDrown(a);
    }

    public void checkDrown(int air) {
        if(!this.isInWater()) {
            air--;
            this.setAir(air);
            if(this.getAir() <= 0){
                this.attackEntityFrom(DamageSource.DROWN, 1F);
            }
        }
        else
            this.setAir(20);
    }

    @Override
    @Nullable
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }
    
}
