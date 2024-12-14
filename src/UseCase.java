import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class UseCase {
    DatabaseRepository repository = new DatabaseRepository();
    List <Item> items = new ArrayList<>(); // Opretter en tom liste af Item objekter
    Inventory inventory = new Inventory(0,0, 0,0, items);// Opretter en ny inventory instans med "tomme" værdier

    public String initiateInventory(int id) {
        int inventoryId = id;
        int weight = 0;
        int slot = repository.initiateSlots(id);
        int slotMax = repository.initiateMaxSlots(id);
        items = repository.initiateInventory(id);

        if (items.isEmpty()) {
            inventory = new Inventory(inventoryId, slot, slotMax, weight, items);
        } else {
        for (Item item : items) {
            weight += item.getWeight();
        }
            inventory.setSlotCurrent(slot);
            inventory.setWeightCurrent(weight);
            inventory.setSlotCurrentMax(slotMax);
            inventory.setItems(items);

           inventory = new Inventory(inventoryId, slot, slotMax, weight, items);

//            System.out.println(inventory.getId());
//            System.out.println("Inventory initiated");
        return "Inventory initiated";
    }
        return null;
    }

    public String createNewItem(Item item) {
        String answer = repository.addItem(item);// Tilføjer det nye item objekt til databasen
        return answer;
    }
    public String deleteItem(int id){
        String deleted = repository.deleteItem(id); // Sletter item fra database
        return deleted;
    }
    public List <Item> getAllItems() {
        List<Item> items1 = repository.getAllItems(); // Henter alle items fra databasen
        return items1;
    }
    public String updateItem(Item item) {
        String updated = repository.updateItem(item); // Tilføjer den opdateret item til database
        return updated;
    }
    public int createNewInventory(int id){
        int inventoryId = repository.createNewInventory(id); //Opretter ny inventory i database
        return inventoryId;
    }

    public String addItemToInventory(int invId, int itemId) {
        int newWeight = 0;
        Item item = repository.getOneItem(itemId);
        System.out.println(item.getType());
        if (checkWeight(item, inventory.getWeightCurrent(), inventory.getWeightMax())) {
            if (checkItemStack(items, item)) { // Kalder CheckItemStack korrekt
                if (checkAvailableSlotConsumable(inventory.getSlotCurrent(), inventory.getSlotCurrentMax())) {
                    String added = repository.addItemToInventory(invId, itemId);
                    newWeight = (inventory.getWeightCurrent() + item.getWeight());
                    inventory.setWeightCurrent(newWeight);
                    items.add(item);
                    System.out.println("Item added: " + item);
                    return added;
                } else {
                    return "No available slots.";
                }
            } else if (checkAvailableSlot(inventory.getSlotCurrent(), inventory.getSlotCurrentMax())) {
                int newSlot = inventory.getSlotCurrent();
                String added = repository.addItemToInventory(invId, itemId);
                items.add(item);
                newSlot ++;
                inventory.setSlotCurrent(newSlot);
                String setSlot = repository.setSlot(newSlot, invId);
                System.out.println("Slot set: " + setSlot);
                newWeight = (inventory.getWeightCurrent() + item.getWeight());
                inventory.setWeightCurrent(newWeight);
                System.out.println("Item added: " + item);
                return added;
            } else {
                return "No available slots.";
            }
        } else {
            return "Inventory Full";
        }
    }

    public String removeItemFromInventory(int invId, int itemId) {
        Item item = repository.getOneItem(itemId);
        String removed = repository.removeItemFromInventory(invId, itemId);
        if (removed != null) {
            for (int i = 0; i < items.size(); i++) {
                Item obj = items.get(i);
                if (obj.getId() == itemId) {
                    items.remove(i);
                    inventory.setSlotCurrent (inventory.getSlotCurrent() - 1);
                    if (checkItemStack(items, item)) {
                        inventory.setSlotCurrent(inventory.getSlotCurrent() + 1);
                    }
                    inventory.setWeightCurrent(inventory.getWeightCurrent() - item.getWeight());
                    String slotSet = repository.setSlot(inventory.getSlotCurrent(), invId);
                    return "Item removed from inventory.";
                }
            }
        } else {
            return "Item cannot be removed";
        }
        return null;
    }

    public boolean checkItemStack(List<Item> items, Item item) {
        for (Item currentItem : items) {
            System.out.println("Checking item: " + currentItem + " against " + item);
            if (currentItem.equals(item)) { // Sammenlign objekter
                if (item.getType().equals("Consumable")) {
                    System.out.println("Success: Item found in stack: " + currentItem.getName());
                    return true;
                }
            }
        }
        System.out.println("Item doesn't stack.");
        return false;
    }

    public boolean checkWeight(Item item, int weightCurrent, int weightMax) {
        if (item.getWeight() + weightCurrent <= weightMax) {
            return true;
        }
        return false;
    }

    public boolean checkAvailableSlot(int slotCurrent, int slotCurrentMax) {
        if ((slotCurrent < slotCurrentMax)) {
            return true;
        }
        return false;
    }
    public boolean checkAvailableSlotConsumable(int slotCurrent, int slotCurrentMax) {
        if ((slotCurrent <= slotCurrentMax)) {
            return true;
        }
        return false;
    }

    public boolean checkGold(int invId) {
        int gold = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == 1) {
                gold++;
            }
        }
        if (gold > 9) {
            for (int i = 0; i <= 10; i++) {
                removeItemFromInventory(invId, 1);
            }
            return true;
        }
        return false;
    }

    public String increaseMaxSlot(int slotCurrentMax, int slotMax, int invId) {
        int slotNewCurrentMax;
        if ((slotCurrentMax <= slotMax - 10) && (checkGold(invId))) {
            slotNewCurrentMax = (slotCurrentMax + 10);
            inventory.setSlotCurrentMax(slotNewCurrentMax);
            String newSlotSize = repository.setSlotSize(slotNewCurrentMax, inventory.getId());
            //System.out.println(newSlotSize);

            return "Slot size is now " + slotNewCurrentMax;
        } else {
            return "Slot size couldn't be changed";
        }
    }

    public String exportInventory() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Inventory.txt"));
            for (Item item : items) {
                writer.write(item.toString() + "\n");
            }
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Fejl i export af fil");
            ioe.printStackTrace();
        }
        return "Inventory exported to file";
    }

    public List<Item> highestWeight() {
        bubbleSortbyWeightHi(items);
        return items;
    }

    public void bubbleSortbyWeightHi(List<Item> items) {
        int n = items.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (items.get(j).getWeight() > items.get(j + 1).getWeight()) {
                    Item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
    }

    public List<Item> lowestWeight() {
        bubbleSortbyWeightLo(items);
        return items;
    }

    public void bubbleSortbyWeightLo(List<Item> items) {
        int n = items.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (items.get(j).getWeight() < items.get(j + 1).getWeight()) {
                    Item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
    }

    public List<Item> searchByType(String type) {
        List<Item> temp = new ArrayList<>();
        for (Item item : items) {
            if (item.getType().toLowerCase().equals(type.toLowerCase())) {
                temp.add(item);
            }
        }
        if (temp.size() == 0) {
            System.out.println("No items found");
        }
        return temp;
    }

    public List<Item> searchByName(String name) {
        List<Item> temp = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                temp.add(item);
            }
        }
        if (temp.size() == 0) {
            System.out.println("No items found");
        }
        return temp;
    }

    public Map<Item, Integer> showConsumables() {
        items = inventory.getItems();
        System.out.println("Following is in your inventory...\n");
        Map<Item, Integer> countItems = new HashMap<>();
        for (Item item : items) {
            if (item.getType().equals("Consumable")) {
                if (!countItems.containsKey(item)) {
                    countItems.put(item, 1);
                } else {
                    countItems.put(item, countItems.get(item) + 1);
                }
            }
        }
        return countItems;
    }

    public List<Item> showArmorAndWeapons() {
        items = inventory.getItems();
        ArrayList<Item> armorWeapons = new ArrayList<>();
        for (Item item : items) {
            if (item.getType().equals("Weapon") || item.getType().equals("Armor")) {
                armorWeapons.add(item);
            }
        }
        return armorWeapons;
    }
//    public List<Item> showAllItems(){
//        items = inventory.getItems();
//        return items;
//    }
}
