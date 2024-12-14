import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Scanner;
//import java.util.HashMap;
//import java.util.Map;

public class Inventory {
    private int id;
    private int slotCurrent;
    private int slotCurrentMax = 32;
    private final int slotMax = 192;
    private int weightCurrent;
    private final int weightMax = 50;
    private List<Item> items;
//    Scanner input = new Scanner(System.in);


//    DatabaseRepository repository = new DatabaseRepository();

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

//    public Inventory initiateInventory(int id) {
//        int inventoryId = id;
//        int weight = 0;
//        int slot = repository.initiateSlots(id);
//        int slotMax = repository.initiateMaxSlots(id);
//        List<Item> items = repository.initiateInventory(inventoryId);
//        if (items.isEmpty()) {
//            return new Inventory(inventoryId, slot, slotMax, weight, items);
//        } else {
//            for (Item item : items) {
//                weight += item.getWeight();
//            }
//            setSlotCurrent(slot);
//            setWeightCurrent(weight);
//            setSlotCurrentMax(slotMax);
//            return new Inventory(inventoryId, slot, slotMax, weight, items);
//        }
//    }
//
//    public String addItemToInventory(int invId, int itemId) {
//        Item item = repository.getOneItem(itemId);
//        System.out.println(item.getType());
//        if (checkWeight(item, weightCurrent, weightMax)) {
//            if (checkItemStack(items, item)) { // Kalder CheckItemStack korrekt
////                System.out.println(item.getName());
//                if (slotCurrent <= slotCurrentMax) {
//                    String added = repository.addItemToInventory(invId, itemId);
//                    weightCurrent += item.getWeight();
//                    items.add(item);
//                    System.out.println("Item added: " + item);
//                    return added;
//                } else {
//                    return "No available slots.";
//                }
//            } else if (checkAvailableSlot(slotCurrent, slotCurrentMax)) {
//                String added = repository.addItemToInventory(invId, itemId);
//                items.add(item);
//                slotCurrent++;
//                String slotSet = repository.setSlot(slotCurrent, invId);
//                System.out.println("Slot set: " + slotSet);
//                weightCurrent += item.getWeight();
//                System.out.println("Item added: " + item);
//                return added;
//            } else {
//                return "No available slots.";
//            }
//        } else {
//            return "Inventory Full";
//        }
//    }
//
//    public String removeItemFromInventory(int invId, int itemId) {
//        Item item = repository.getOneItem(itemId);
//        String removed = repository.removeItemFromInventory(invId, itemId);
//        if (removed != null) {
//            System.out.println(item + " to be removed");
//            for (int i = 0; i < items.size(); i++) {
//                Item obj = items.get(i);
//                if (obj.getId() == itemId) {
//                    items.remove(i);
//                    slotCurrent--;
//                    if (checkItemStack(items, item)) {
//                        slotCurrent++;
//                    }
//                    weightCurrent -= item.getWeight();
//                    String slotSet = repository.setSlot(slotCurrent, invId);
//                    System.out.println("Slot set: " + slotSet);
//                    return "Item removed from inventory";
//                }
//            }
//
////            items.remove(items.indexOf(item));
////            System.out.println(item.getName());
//
//
//        } else {
//            return "Item cannot be removed";
//        }
//        return null;
//    }
//
//    public boolean checkItemStack(List<Item> items, Item item) {
//        for (int i = 0; i < items.size(); i++) {
//            Item obj = items.get(i);
//            if (obj.getId() == item.getId()) {
////        for (Item currentItem : items) {
////            System.out.println("Checking item: " + currentItem + " against " + item);
////            if (currentItem.equals(item)) { // Sammenlign objekter
////                System.out.println("Success: Item found in stack - " + currentItem.getName());
//                System.out.println("Success");
//                if (item.getType().equals("Consumable")) {
//                    //slotCurrent--;
//                    return true;
//                }
//            }
//        }
//        System.out.println("Item not found in stack.");
//        return false;
//    }
//
//    public boolean checkWeight(Item item, int weightCurrent, int weightMax) {
//        if (item.getWeight() + weightCurrent <= weightMax) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean checkAvailableSlot(int slotCurrent, int slotCurrentMax) {
//        if ((slotCurrent < slotCurrentMax)) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean checkGold(int invId) {
//        int gold = 0;
//        for (int i = 0; i < items.size(); i++) {
//            if (items.get(i).getId() == 1) {
//                gold++;
//            }
//        }
//        if (gold > 9) {
//            for (int i = 0; i <= 10; i++) {
//                removeItemFromInventory(invId, 1);
//
//            }
//            return true;
//        }
//        return false;
//    }
//
//    public String increaseMaxSlot(int slotCurrentMax, int slotMax, int invId) {
//        int slotNewCurrentMax;
//        if ((slotCurrentMax <= slotMax - 10) && (checkGold(invId))) {
//            slotNewCurrentMax = (slotCurrentMax + 10);
//            setSlotCurrentMax(slotNewCurrentMax);
//            String newSlotSize = repository.setSlotSize(slotNewCurrentMax, getId());
//            System.out.println(newSlotSize);
//
//            return "Slot size is now " + slotNewCurrentMax;
//        } else {
//            return "Slot size couldn't be changed";
//        }
//    }
//
//    public String exportInventory() {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("Inventory.txt"));
//            for (Item item : items) {
//                writer.write(item.toString() + "\n");
//            }
//            writer.close();
//        } catch (IOException ioe) {
//            System.out.println("Fejl i export af fil");
//            ioe.printStackTrace();
//        }
//        return "Inventory exported to file";
//    }
//
//    public List<Item> highestWeight() {
//        bubbleSortbyWeightHi(items);
//        return items;
//    }
//
//    public void bubbleSortbyWeightHi(List<Item> items) {
//        int n = items.size();
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < n - i - 1; j++) {
//                if (items.get(j).getWeight() > items.get(j + 1).getWeight()) {
//                    Item temp = items.get(j);
//                    items.set(j, items.get(j + 1));
//                    items.set(j + 1, temp);
//                }
//            }
//        }
//    }
//
//    public List<Item> lowestWeight() {
//        bubbleSortbyWeightLo(items);
//        return items;
//    }
//
//    public void bubbleSortbyWeightLo(List<Item> items) {
//        int n = items.size();
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < n - i - 1; j++) {
//                if (items.get(j).getWeight() < items.get(j + 1).getWeight()) {
//                    Item temp = items.get(j);
//                    items.set(j, items.get(j + 1));
//                    items.set(j + 1, temp);
//                }
//            }
//        }
//    }
//
//    public List<Item> searchByType() {
//        List<Item> temp = new ArrayList<>();
//        System.out.println("Enter type to search for: Weapon, Armor or Consumable");
//        String type = input.nextLine();
//        for (Item item : items) {
//            //System.out.println(item.getType().toLowerCase());
//            if (item.getType().toLowerCase().equals(type.toLowerCase())) {
//                temp.add(item);
//            }
//        }
//        if (temp.size() == 0) {
//            System.out.println("No items found");
//        }
//        return temp;
//    }
//
//    public List<Item> searchByName() {
//        List<Item> temp = new ArrayList<>();
//        System.out.println("Enter name or part of name to search for: ");
//        String name = input.nextLine();
//        for (Item item : items) {
//            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
//                temp.add(item);
//            }
//        }
//        if (temp.size() == 0) {
//            System.out.println("No items found");
//        }
//        return temp;
//    }
//
//    public Map<Item, Integer> showConsumables() {
//        System.out.println("Following is in your inventory:\n");
//        Map<Item, Integer> countItems = new HashMap<>();
//        for (Item item : items) {
//            if (item.getType().equals("Consumable")) {
//                if (!countItems.containsKey(item)) {
//                    countItems.put(item, 1);
//                } else {
//                    countItems.put(item, countItems.get(item) + 1);
//                }
//            }
//        }
//        return countItems;
//    }
//
//    public List<Item> showArmorAndWeapons() {
//        ArrayList<Item> armorWeapons = new ArrayList<>();
//
//        for (Item item : items) {
//            if (item.getType().equals("Weapon") || item.getType().equals("Armor")) {
//                armorWeapons.add(item);
//            }
//        }
//        return armorWeapons;
//    }
}