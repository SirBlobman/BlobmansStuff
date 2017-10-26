package com.SirBlobman.stuff.entity.custom;

import com.SirBlobman.stuff.item.custom.ItemCustomSlimeball;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileItem extends EntitySnowball {
    private static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(EntityProjectileItem.class, DataSerializers.FLOAT);
    private static final DataParameter<ItemStack> ITEM = EntityDataManager.createKey(EntityProjectileItem.class, DataSerializers.ITEM_STACK);
    
    public EntityProjectileItem(World world) {super(world);}
    public EntityProjectileItem(World world, EntityLivingBase elb) {super(world, elb);}
    
    @Override
    public void entityInit() {
        super.entityInit();
        dataManager.register(DAMAGE, 0.0F);
        dataManager.register(ITEM, ItemStack.EMPTY);
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        NBTTagCompound itemNBT = dataManager.get(ITEM).serializeNBT();
        nbt.setTag("Item", itemNBT);
        nbt.setFloat("damage", dataManager.get(DAMAGE));
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        if(nbt.hasKey("damage")) dataManager.set(DAMAGE, nbt.getFloat("damage"));
        if(nbt.hasKey("Item")) {
            NBTTagCompound itemNBT = nbt.getCompoundTag("Item");
            ItemStack is = new ItemStack(itemNBT);
            dataManager.set(ITEM, is);
        }
    }
    
    @Override
    public void onImpact(RayTraceResult trace) {
        if(!world.isRemote) {
            Entity en = trace.entityHit;
            if(en != null) {
                if(en instanceof EntityLivingBase) {
                    EntityLivingBase elb = (EntityLivingBase) en;
                    float damage = getDamage();
                    DamageSource ds = new EntityDamageSourceIndirect("blobman.slimeball", getThrower(), elb);
                    elb.attackEntityFrom(ds, damage);
                    applyItemEffect(elb);
                }
            }
        }
    }
    
    private void applyItemEffect(EntityLivingBase elb) {
        ItemStack is = getItem();
        if(is != null && !is.isEmpty()) {
            Item item = is.getItem();
            if(item == Items.SLIME_BALL || item instanceof ItemCustomSlimeball) {
                PotionEffect slow = new PotionEffect(MobEffects.SLOWNESS, 20 * 20, 0);
                elb.addPotionEffect(slow);
            }
        }
    }
    
    public ItemStack getItem() {return dataManager.get(ITEM);}
    public void setItem(ItemStack is) {dataManager.set(ITEM, is);}
    public float getDamage() {return dataManager.get(DAMAGE);}
    public void setDamage(float f) {dataManager.set(DAMAGE, f);}
}