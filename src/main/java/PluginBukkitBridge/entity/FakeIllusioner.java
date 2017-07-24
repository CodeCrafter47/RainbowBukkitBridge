package PluginBukkitBridge.entity;

import org.bukkit.entity.Illusioner;

import PluginReference.MC_Entity;

public class FakeIllusioner extends FakeCreature implements Illusioner {
    private Spell spell = Spell.NONE;
    
    public FakeIllusioner(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public Spell getSpell() {
        return spell;
    }

    @Override
    public void setSpell(Spell arg0) {
        this.spell = arg0;
    }
}
