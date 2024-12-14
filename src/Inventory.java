import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int id;
    private int slotCurrent;
    private int slotCurrentMax = 32;
    private final int slotMax = 192;
    private int weightCurrent;
    private final int weightMax = 50;
    private List<Item> items;

    public Inventory(int id, int slotCurrent, int slotCurrentMax, int weightCurrent, List<Item> items) {
        this.id = id;
        this.slotCurrent = slotCurrent;
        this.slotCurrentMax = slotCurrentMax;
        this.weightCurrent = weightCurrent;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setSlotCurrent(int slotCurrent) {
        this.slotCurrent = slotCurrent;
    }

    public void setWeightCurrent(int weightCurrent) {
        this.weightCurrent = weightCurrent;
    }

    public int getSlotCurrent() {
        return slotCurrent;
    }

    public int getWeightCurrent() {
        return weightCurrent;
    }

    public int getWeightMax() {
        return weightMax;
    }

    public int getSlotCurrentMax() {
        return slotCurrentMax;
    }

    public void setSlotCurrentMax(int slotCurrentMax) {
        this.slotCurrentMax = slotCurrentMax;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getSlotMax() {
        return slotMax;
    }
}