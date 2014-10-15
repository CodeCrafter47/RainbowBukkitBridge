package PluginBukkitBridge.entity;

import PluginBukkitBridge.MyPlugin;
import PluginBukkitBridge.ReflectionUtil;
import PluginReference.MC_Entity;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.*;

public class FakeLivingEntity extends FakeEntity implements LivingEntity{
    public FakeLivingEntity(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public double getEyeHeight() {
        return ReflectionUtil.getEntityHeight(m_ent);
    }

    @Override
    public double getEyeHeight(boolean ignoreSneaking) {
        MyPlugin.fixme("ignored parameter ignoreSneaking");
        return getEyeHeight();
    }

    @Override
    public Location getEyeLocation()
    {
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
    public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance)
    {
        return getLineOfSight(transparent, maxDistance, 0);
    }

    @Override
    public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance)
    {
        List<Block> blocks = getLineOfSight(transparent, maxDistance, 1);
        return blocks.get(0);
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent, int maxDistance)
    {
        return getLineOfSight(transparent, maxDistance, 2);
    }

    @Override
    public Egg throwEgg()
    {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public Arrow shootArrow()
    {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public Snowball throwSnowball()
    {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public int getRemainingAir()
    {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setRemainingAir(int arg0)
    {
        MyPlugin.fixme("stub method");
    }

    @Override
    public int getMaximumAir()
    {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setMaximumAir(int arg0)
    {
        MyPlugin.fixme("stub method");
    }

    @Override
    public int getMaximumNoDamageTicks()
    {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setMaximumNoDamageTicks(int arg0)
    {
        MyPlugin.fixme("stub method");
    }

    @Override
    public double getLastDamage()
    {
        EntityDamageEvent event = getLastDamageCause();
        if(event == null)return 0;
        return event.getFinalDamage();
    }

    @Override
    public int _INVALID_getLastDamage() {
        return ((int)getLastDamage());
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
    public int getNoDamageTicks()
    {
        MyPlugin.fixme("stub method");
        return 0;
    }

    @Override
    public void setNoDamageTicks(int arg0)
    {
        MyPlugin.fixme("stub method");
    }

    @Override
    public Player getKiller()
    {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public boolean addPotionEffect(PotionEffect arg0)
    {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean addPotionEffect(PotionEffect arg0, boolean arg1)
    {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> arg0)
    {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects()
    {
        MyPlugin.fixme("stub method");
        return null;
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType arg0)
    {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void removePotionEffect(PotionEffectType arg0)
    {
        MyPlugin.fixme("stub method");
    }

    @Override
    public boolean hasLineOfSight(Entity arg0)
    {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public boolean getRemoveWhenFarAway()
    {
        MyPlugin.fixme("stub method");
        return false;
    }

    @Override
    public void setRemoveWhenFarAway(boolean arg0)
    {
        MyPlugin.fixme("stub method");
    }

    @Override
    public EntityEquipment getEquipment()
    {
        FakeDebug("getEquipment");
        return null;
    }

    @Override
    public void setCanPickupItems(boolean arg0)
    {
        FakeDebug("setCanPickupItems");
    }

    @Override
    public boolean getCanPickupItems()
    {
        FakeDebug("getCanPickupItems");
        return false;
    }

    @Override
    public void setCustomName(String name) {
        m_ent.setCustomName(name);
    }

    @Override
    public String getCustomName() {
        return m_ent.getCustomName();
    }

    @Override
    public void setCustomNameVisible(boolean arg0)
    {
        FakeDebug("setCustomNameVisible");
    }

    @Override
    public boolean isCustomNameVisible()
    {
        FakeDebug("isCustomNameVisible");
        return false;
    }

    @Override
    public boolean isLeashed()
    {
        FakeDebug("isLeashed");
        return false;
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException
    {
        FakeDebug("getLeashHolder");
        return null;
    }

    @Override
    public boolean setLeashHolder(Entity arg0)
    {
        FakeDebug("setLeashHolder");
        return false;
    }

    @Override
    public void _INVALID_damage(int arg0)
    {
        FakeDebug("_INVALID_damage");
    }

    @Override
    public void _INVALID_damage(int arg0, Entity arg1)
    {
        FakeDebug("_INVALID_damage");
    }

    @Override
    public void damage(double arg0)
    {
        FakeDebug("damage");
    }

    @Override
    public void damage(double arg0, Entity arg1)
    {
        FakeDebug("damage");
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
    public double getMaxHealth()
    {
        return m_ent.getMaxHealth();
    }

    @Override
    public int _INVALID_getMaxHealth()
    {
        return (int) m_ent.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double arg0)
    {
        m_ent.setMaxHealth((float) arg0);
    }

    @Override
    public void _INVALID_setMaxHealth(int arg0)
    {
        m_ent.setMaxHealth(arg0);
    }

    @Override
    public void resetMaxHealth()
    {
        FakeDebug("resetMaxHealth");
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> arg0)
    {
        FakeDebug("launchProjectile");
        return null;
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> arg0, Vector arg1)
    {
        FakeDebug("launchProjectile");
        return null;
    }
}
