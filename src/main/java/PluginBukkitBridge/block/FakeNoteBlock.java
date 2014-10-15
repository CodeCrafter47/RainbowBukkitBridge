package PluginBukkitBridge.block;

import PluginBukkitBridge.MyPlugin;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.NoteBlock;

/**
 * Created by florian on 14.10.14.
 */
public class FakeNoteBlock extends FakeBlockState implements NoteBlock {
    public FakeNoteBlock(FakeBlock arg) {
        super(arg);
    }

    @Override
    public Note getNote() {
        MyPlugin.fixme();
        return null;
    }

    @Override
    public byte getRawNote() {
        MyPlugin.fixme();
        return 0;
    }

    @Override
    public void setNote(Note note) {
        MyPlugin.fixme();
    }

    @Override
    public void setRawNote(byte b) {
        MyPlugin.fixme();
    }

    @Override
    public boolean play() {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean play(byte b, byte b2) {
        MyPlugin.fixme();
        return false;
    }

    @Override
    public boolean play(Instrument instrument, Note note) {
        MyPlugin.fixme();
        return false;
    }
}
