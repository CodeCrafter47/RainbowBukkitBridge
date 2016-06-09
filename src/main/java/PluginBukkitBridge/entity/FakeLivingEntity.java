package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.Util;
import PluginBukkitBridge.entity.attribute.FakeAttributeInstance;
import PluginBukkitBridge.inventory.FakeEntityEquipment;
import PluginReference.MC_Entity;
import PluginReference.MC_LivingEntity;
import PluginReference.MC_Player;
import PluginReference.MC_PotionEffect;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FakeLivingEntity extends FakeEntity implements LivingEntity {
    public FakeLivingEntity(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public double getEyeHeight() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public double getEyeHeight(boolean ignoreSneaking) {
        MyPlugin.fixme("ignored parameter ignoreSneaking");
        return getEyeHeight();
    }

    @Override
    public Location getEyeLocation() {
        Location l = getLocation();
        l = l.add(0, getEyeHeight(), 0);
        return l;
    }

    private List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance, int maxLength) {
        if (maxDistance > 120) {
            maxDistance = 120;
        }
        ArrayList<Block> blocks = new ArrayList<Block>();
        Iterator<Block> itr = new BlockIterator(this, maxDistance);
        while (itr.hasNext()) {
            Block block = itr.next();
            blocks.add(block);
            if (maxLength != 0 && blocks.size() > maxLength) {
                blocks.remove(0);
            }
            int id = block.getTypeId();
            if (transparent == null) {
                if (id != 0) {
                    break;
                }
            } else {
                if (!transparent.contains((byte) id)) {
                    break;
                }
            }
        }
        return blocks;
    }

    @Override
    public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance) {
        return getLineOfSight(transparent, maxDistance, 0);
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> set, int i) {
        return getLineOfSight(Sets.newHashSet(Iterables.transform(set, new Function<Material, Byte>() {
            @Override
            public Byte apply(Material material) {
                return (byte)material.getId();
            }
        })), i);
    }

    @Override
    public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance) {
        List<Block> blocks = getLineOfSight(transparent, maxDistance, 1);
        return blocks.get(0);
    }

    @Override
    public Block getTargetBlock(Set<Material> set, int i) {
        return getTargetBlock(Sets.newHashSet(Iterables.transform(set, new Function<Material, Byte>() {
            @Override
            public Byte apply(Material material) {
                return (byte)material.getId();
            }
        })), i);
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent, int maxDistance) {
        return getLineOfSight(transparent, maxDistance, 2);
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> set, int i) {
        return getLastTwoTargetBlocks(Sets.newHashSet(Iterables.transform(set, new Function<Material, Byte>() {
            @Override
            public Byte apply(Material material) {
                return (byte)material.getId();
            }
        })), i);
    }

    @Override
    public int getRemainingAir() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setRemainingAir(int arg0) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public int getMaximumAir() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setMaximumAir(int arg0) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public int getMaximumNoDamageTicks() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setMaximumNoDamageTicks(int arg0) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public double getLastDamage() {
        EntityDamageEvent event = getLastDamageCause();
        if (event == null) return 0;
        return event.getFinalDamage();
    }

    @Override
    public int _INVALID_getLastDamage() {
        return ((int) getLastDamage());
    }

    @Override
    public void setLastDamage(double v) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public void _INVALID_setLastDamage(int i) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public int getNoDamageTicks() {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setNoDamageTicks(int arg0) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public Player getKiller() {
        if(m_ent.getAttacker() != null && m_ent.getAttacker() instanceof MC_Player){
			return (Player) Util.wrapEntity(m_ent.getAttacker());
		}
        return null;
    }

    @Override
    public boolean addPotionEffect(PotionEffect arg0) {
        return addPotionEffect(arg0, false);
    }

    @Override
    public boolean addPotionEffect(PotionEffect arg0, boolean force) {
        if(!force && hasPotionEffect(arg0.getType()))return false;
        // remove old effect
        removePotionEffect(arg0.getType());
        // add new one
        List<MC_PotionEffect> potionEffects = new ArrayList<>(m_ent.getPotionEffects());
        MC_PotionEffect effect = new MC_PotionEffect(Util.getPotionEffectType(arg0.getType()), arg0.getDuration(), arg0.getAmplifier());
        effect.ambient = arg0.isAmbient();
        effect.showsParticles = true;
        potionEffects.add(effect);
        m_ent.setPotionEffects(potionEffects);
        return true;
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        boolean success = true;
        for (PotionEffect effect : effects) {
            success &= addPotionEffect(effect);
        }
        return success;
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        List<PotionEffect> result = new ArrayList<>();
        for(MC_PotionEffect eff: m_ent.getPotionEffects()){
            result.add(new PotionEffect(Util.getPotionEffectType(eff.type), eff.duration, eff.amplifier, eff.ambient));
        }
        return result;
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType arg0) {
        for(PotionEffect p: getActivePotionEffects())if(p.getType() == arg0)return true;
        return false;
    }

    @Override
    public void removePotionEffect(PotionEffectType arg0) {
        List<MC_PotionEffect> potionEffects = new ArrayList<>();
        for(MC_PotionEffect effect: m_ent.getPotionEffects()){
            if(effect.type != Util.getPotionEffectType(arg0))potionEffects.add(effect);
        }
        m_ent.setPotionEffects(potionEffects);
    }

    @Override
    public boolean hasLineOfSight(Entity arg0) {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setRemoveWhenFarAway(boolean arg0) {
        MyPlugin.fixme("stub method");
    }

    @Override
    public EntityEquipment getEquipment() {
        return new FakeEntityEquipment(m_ent);
    }

    @Override
    public void setCanPickupItems(boolean arg0) {
        MyPlugin.fixme();
    }

    @Override
    public boolean getCanPickupItems() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isLeashed() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public boolean setLeashHolder(Entity arg0) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean isGliding() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setGliding(boolean b) {
        MyPlugin.fixme();
    }

    @Override
    public void setAI(boolean b) {
        MyPlugin.fixme();
    }

    @Override
    public boolean hasAI() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void setCollidable(boolean b) {
        MyPlugin.fixme();
    }

    @Override
    public boolean isCollidable() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public void _INVALID_damage(int arg0) {
        damage(arg0);
    }

    @Override
    public void _INVALID_damage(int arg0, Entity arg1) {
        damage(arg0,arg1);
    }

    @Override
    public void damage(double arg0) {
        double newHealth = getHealth()-arg0;
        if(newHealth <= 0){
            m_ent.kill();
            return;
        }
        setHealth(newHealth);
    }

    @Override
    public void damage(double arg0, Entity arg1) {
        damage(arg0);
    }

    @Override
    public double getHealth() {
        return m_ent.getHealth();
    }

    @Override
    public int _INVALID_getHealth() {
        return (int) m_ent.getHealth();
    }

    @Override
    public void setHealth(double health) {
        m_ent.setHealth((float) health);
    }

    @Override
    public void _INVALID_setHealth(int health) {
        m_ent.setHealth(health);
    }

    @Override
    public double getMaxHealth() {
        return m_ent.getMaxHealth();
    }

    @Override
    public int _INVALID_getMaxHealth() {
        return (int) m_ent.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double arg0) {
        m_ent.setMaxHealth((float) arg0);
    }

    @Override
    public void _INVALID_setMaxHealth(int arg0) {
        m_ent.setMaxHealth(arg0);
    }

    @Override
    public void resetMaxHealth() {
        MyPlugin.fixme();
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        return launchProjectile(projectile, null);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector arg1) {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        return new FakeAttributeInstance(((MC_LivingEntity) m_ent).getAttribute(Util.wrapAttributeType(attribute)));
    }
}
