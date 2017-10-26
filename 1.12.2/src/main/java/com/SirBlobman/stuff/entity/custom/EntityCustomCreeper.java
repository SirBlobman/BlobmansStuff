package com.SirBlobman.stuff.entity.custom;

import com.SirBlobman.stuff.entity.custom.ai.EntityAICustomCreeperSwell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityCustomCreeper extends EntityMob {
    private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityCustomCreeper.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> POWERED = EntityDataManager.createKey(EntityCustomCreeper.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IGNITED = EntityDataManager.createKey(EntityCustomCreeper.class, DataSerializers.BOOLEAN);
    
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 30;
    private int explosionRadius = 3;
    
    public EntityCustomCreeper(World world) {
        super(world);
        setSize(0.6F, 1.7F);
    }
    
    public int getMaxFallHeight() {return 0;}
    public void fall(float f, float g) {}
    public SoundEvent getHurtSound(DamageSource ds) {return SoundEvents.ENTITY_SLIME_HURT;}
    public SoundEvent getDeathSound() {return SoundEvents.ENTITY_SLIME_DEATH;}
    public boolean attackEntityAsMob(Entity en) {return true;}
    public ResourceLocation getLootTable() {return LootTableList.ENTITIES_CREEPER;}
    
    public boolean getPowered() {return dataManager.get(POWERED);}
    public int getCreeperState() {return dataManager.get(STATE);}
    public void setCreeperState(int i) {dataManager.set(STATE, i);}
    public boolean hasIgnited() {return dataManager.get(IGNITED);}
    public void ignite() {dataManager.set(IGNITED, true);}
    public abstract void explode();
    
    @Override
    public void initEntityAI() {
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAICustomCreeperSwell(this));
        tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(6, new EntityAILookIdle(this));
        
        targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }
    
    @Override
    public void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }
    
    @Override
    public void entityInit() {
        super.entityInit();
        dataManager.register(STATE, -1);
        dataManager.register(POWERED, false);
        dataManager.register(IGNITED, false);
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        if(dataManager.get(POWERED)) nbt.setBoolean("powered", true);
        nbt.setShort("Fuse", (short) fuseTime);
        nbt.setByte("ExplosionRadius", (byte) explosionRadius);
        nbt.setBoolean("ignited", hasIgnited());
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        dataManager.set(POWERED, nbt.getBoolean("powered"));
        if(nbt.hasKey("Fuse", 99)) fuseTime = nbt.getShort("Fuse");
        if(nbt.hasKey("ExplosionRadius", 99)) explosionRadius = nbt.getByte("ExplosionRadius");
        if(nbt.getBoolean("ignited")) ignite();
    }
    
    @Override
    public void onUpdate() {
        if(isEntityAlive()) {
            lastActiveTime = timeSinceIgnited;
            if(hasIgnited()) setCreeperState(1);
            int i = getCreeperState();
            if(i > 0 && timeSinceIgnited == 0) playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
            timeSinceIgnited += i;
            if(timeSinceIgnited < 0) timeSinceIgnited = 0;
            if(timeSinceIgnited >= fuseTime) {
                timeSinceIgnited = fuseTime;
                explode();
            }
        } super.onUpdate();
    }
    
    @SideOnly(Side.CLIENT)
    public float getCreeperFlashIntensity(float f) {
        float fLastActiveTime = lastActiveTime;
        float fTimeSinceIgnited = timeSinceIgnited;
        float fFusetime = (fuseTime - 2);
        return ((fLastActiveTime + (fTimeSinceIgnited - fLastActiveTime) * f) / fFusetime);
    }
    
    @Override
    public void onStruckByLightning(EntityLightningBolt elb) {
        super.onStruckByLightning(elb);
        dataManager.set(POWERED, true);
    }
    
    @Override
    public boolean processInteract(EntityPlayer ep, EnumHand eh) {
        ItemStack held = ep.getHeldItem(eh);
        if(held.getItem() == Items.FLINT_AND_STEEL) {
            world.playSound(ep, posX, posY, posZ, SoundEvents.ITEM_FLINTANDSTEEL_USE, getSoundCategory(), 1.0F, rand.nextFloat() * 0.4F + 0.8F);
            ep.swingArm(eh);
            
            if(!world.isRemote) {
                ignite();
                held.damageItem(1, ep);
                return true;
            }
        } return super.processInteract(ep, eh);
    }
}