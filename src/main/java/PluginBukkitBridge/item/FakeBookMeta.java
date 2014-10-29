package PluginBukkitBridge.item;

import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.inventory.meta.BookMeta;
import org.jnbt.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class FakeBookMeta extends FakeItemMeta implements BookMeta{

    public FakeBookMeta(CompoundTag tag) {
        super(tag);
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && isBookEmpty();
    }

    boolean isBookEmpty() {
        return !(hasPages() || hasAuthor() || hasTitle());
    }

    @Override
    public boolean applicableTo(Material type) {
        switch (type) {
            case WRITTEN_BOOK:
            case BOOK_AND_QUILL:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean hasTitle() {
        return tag != null && tag.getValue().containsKey("title");
    }

    @Override
    public String getTitle() {
        if(!hasTitle())return null;
        return ((StringTag)tag.getValue().get("title")).getValue();
    }

    @Override
    public boolean setTitle(String s) {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        tag.getValue().put("title", new StringTag("title", s));
        return true;
    }

    @Override
    public boolean hasAuthor() {
        return tag != null && tag.getValue().containsKey("author");
    }

    @Override
    public String getAuthor() {
        if(!hasAuthor())return null;
        return ((StringTag)tag.getValue().get("author")).getValue();
    }

    @Override
    public void setAuthor(String s) {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        tag.getValue().put("author", new StringTag("author", s));
    }

    @Override
    public boolean hasPages() {
        return tag != null && tag.getValue().containsKey("pages");
    }

    @Override
    public String getPage(int i) {
        if(!hasPages())return null;
        return ((StringTag)((ListTag)tag.getValue().get("pages")).getValue().get(i)).getValue();
    }

    @Override
    public void setPage(int i, String s) {
        List<String> pages = getPages();
        pages.remove(i);
        pages.add(i, s);
    }

    @Override
    public List<String> getPages() {
        if(!hasPages())return null;
        List<String> pages = new ArrayList<>();
        for(Tag t: ((ListTag)tag.getValue().get("pages")).getValue()){
            pages.add(((StringTag)t).getValue());
        }
        return pages;
    }

    @Override
    public void setPages(List<String> strings) {
        if(tag == null){
            tag = new CompoundTag("tag", new HashMap<String, Tag>());
        }
        List<Tag> list = new ArrayList<>();
        for(String page: strings){
            list.add(new StringTag("", page));
        }
        tag.getValue().put("pages", new ListTag("pages", StringTag.class, list));
    }

    @Override
    public void setPages(String... strings) {
        setPages(Arrays.asList(strings));
    }

    @Override
    public void addPage(String... strings) {
        List<String> pages = getPages();
        pages.addAll(Arrays.asList(strings));
        setPages(pages);
    }

    @Override
    public int getPageCount() {
        if(!hasPages())return 0;
        return ((ListTag)tag.getValue().get("pages")).getValue().size();
    }

    @Override
    @SneakyThrows
    public FakeBookMeta clone() {
        if(tag == null)return new FakeBookMeta(null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        NBTOutputStream out = new NBTOutputStream(os);
        out.writeTag(tag);
        out.close();
        byte[] bytes = os.toByteArray();
        NBTInputStream in = new NBTInputStream(new ByteArrayInputStream(bytes));
        return new FakeBookMeta((CompoundTag) in.readTag());
    }

    @Override
    boolean equalsCommon(FakeItemMeta meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof FakeBookMeta) {
            FakeBookMeta that = (FakeBookMeta) meta;

            return (hasTitle() ? that.hasTitle() && this.getTitle().equals(that.getTitle()) : !that.hasTitle())
                    && (hasAuthor() ? that.hasAuthor() && this.getAuthor().equals(that.getAuthor()) : !that.hasAuthor())
                    && (hasPages() ? that.hasPages() && this.getPages().equals(that.getPages()) : !that.hasPages());
        }
        return true;
    }

    @Override
    boolean notUncommon(FakeItemMeta meta) {
        return super.notUncommon(meta) && (meta instanceof FakeBookMeta || isBookEmpty());
    }
}
