package PluginBukkitBridge.entity;

import org.bukkit.entity.Evoker;

import PluginBukkitBridge.MyPlugin;
import PluginReference.MC_Entity;

@SuppressWarnings("deprecation")
public class FakeEvoker extends FakeCreature implements Evoker {
    private Spell spell = Spell.NONE;
    private org.bukkit.entity.Spellcaster.Spell spell2 = org.bukkit.entity.Spellcaster.Spell.NONE;
    
    public FakeEvoker(MC_Entity argEnt) {
        super(argEnt);
    }

    @Override
    public org.bukkit.entity.Spellcaster.Spell getSpell() {
        return spell2;
    }

    @Override
    public void setSpell(org.bukkit.entity.Spellcaster.Spell arg0) {
        this.spell2 = arg0;
    }

    @Override
    public Spell getCurrentSpell() {
        MyPlugin.fixme();
        return spell;
    }

    @Override
    public void setCurrentSpell(Spell arg0) {
        this.spell = arg0;
    }
}
