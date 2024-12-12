import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseRepository repository = new DatabaseRepository();
        List <Item> items = new ArrayList<>();
        Inventory inventory = new Inventory(0,0, 0,0, items);
        Scanner input = new Scanner(System.in);

        System.out.println("---Initializing---");
        inventory = inventory.initiateInventory(1);

        while (true) {
            System.out.println("Welcome to Legend of Zel... GameCraft\nChoose your poison\n1 to create item" +
                    "\n2 to see a list of items\n3 to update an item\n4 to delete an item\n5 to make a new inventory" +
                    "\n6 to choose inventory and show its items" +
                    "\n7 to add an item to inventory\n8 to remove item from inventory\n9 to show items in inventory" +
                    "\n10 to increase slot size by 10\n11 to write out a text file of your inventory\n12 to sort after weight" +
                    "\n13 to search for an item\n14 to exit" +
                    "\n");
            try {
                int choice = input.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("You chose 1");
                        System.out.println("Enter id");
                        int id = input.nextInt();
                        input.nextLine();
                        System.out.println("Indtast navn");
                        String name = input.nextLine();
                        System.out.println("Indtast type");
                        String type = input.nextLine();
                        System.out.println("Indtast vægt");
                        int itemWeight = input.nextInt();
                        System.out.println("Indtast beskrivelse");
                        input.nextLine();
                        String itemDescription = input.nextLine();
                        System.out.println("Indtast effekt");
                        int itemEffect = input.nextInt();
                        Item item = new Item(id, name, type, itemWeight, itemDescription, itemEffect);
                        String answer = repository.addItem(item);
                        System.out.println(answer);
                    }
                    case 2 -> {
                        System.out.println("You chose 2 see a list of itemskkkfkfkfkfkfkfk");
                        List<Item> items1 = repository.getAllItems();
                        for (Item item : items1) {
                            System.out.println(item);
                        }
                    }
                    case 3 -> {
                        System.out.println("You chose 3");
                        System.out.println("Indtast id på 'Item' du vil opdatere, uasuiauifubf");
                        int id = input.nextInt();
                        input.nextLine();
                        System.out.println("Indtast navn på 'Item'");
                        String name = input.nextLine();
                        System.out.println("Indtast type");
                        String type = input.nextLine();
                        System.out.println("Indtast vægt");
                        int itemWeight = input.nextInt();
                        input.nextLine();
                        System.out.println("Indtast beskrivelse");
                        String itemDescription = input.nextLine();
                        System.out.println("Indtast effekt");
                        int itemEffect = input.nextInt();
                        Item item = new Item(id, name, type, itemWeight, itemDescription, itemEffect);
                        String updated = repository.updateItem(item);
                        System.out.println(updated);
                    }
                    case 4 -> {
                        System.out.println("You chose 4");
                        System.out.println("Indtast id for 'Item', der skal slettes !!=!=!=!=!=!=!=!=!=!=");
                        int id = input.nextInt();
                        String deleted = repository.deleteItem(id);
                        System.out.println(deleted);
                    }
                    case 5 -> {
                        System.out.println("You chose 5");
                        System.out.println("Indtast brugerid");
                        int id = input.nextInt();
                        int inventoryId = repository.createNewInventory(id);
                        inventory = inventory.initiateInventory(inventoryId);
                    }
                    case 6 -> {
                        System.out.println("You chose 6");
                        System.out.println("Indtast id for 'Inventory', der skal vises");
                        int id = input.nextInt();
                        if (inventory.initiateInventory(id) == null) {
                            System.out.println("No inventory found");
                        }
                        else {
                            inventory = inventory.initiateInventory(id);

                            for (Item item : inventory.getItems()) {
                                System.out.println(item);
                            }
                            System.out.println("\nslots used: " + inventory.getSlotCurrent() +
                                    " out of " + inventory.getSlotCurrentMax() +
                                    "\n \nCurrent weight is: " + inventory.getWeightCurrent() +
                                    " out of " + inventory.getWeightMax() + "\n");
                        }
                    }
                    case 7 -> {
                        System.out.println("You chose 7");
                        System.out.println("Indtast id for 'item', der skal tilføjes");
                        int itemId = input.nextInt();
                        String a2i = inventory.addItemToInventory(inventory.getId(), itemId);
                        System.out.println(a2i);
                        System.out.println("Følgende er nu i inventory:\n");
                        for (Item item : inventory.getItems()) {
                            System.out.println(item.getName());
                        }
                        System.out.println("\nslots used: " + inventory.getSlotCurrent() +
                                " out of " + inventory.getSlotCurrentMax() +
                                "\n \nCurrent weight is: " + inventory.getWeightCurrent() +
                                " out of " + inventory.getWeightMax() + "\n");
                    }
                    case 8 -> {
                        System.out.println("You chose 8");
                        System.out.println("Indtast id for 'item', der skal fjernes");
                        int itemId = input.nextInt();
                        String removeFromInventory = inventory.removeItemFromInventory(inventory.getId(), itemId);
                        System.out.println(removeFromInventory);
                    }
                    case 9 -> {
                        System.out.println("You chose 9");
                        Map<Item, Integer> consumables = inventory.showConsumables();
                        List <Item> nonConsumableItems = inventory.showArmorAndWeapons();
                        System.out.println("Armor and weapons in inventory: ");
                        for (Item item : nonConsumableItems) {
                            System.out.println(item);
                        }
                        System.out.println("\nConsumables: ");
                        for (Map.Entry<Item, Integer> entry : consumables.entrySet()) {
                            System.out.println((entry.getValue()) + " of " + entry.getKey());
                        }
                        System.out.println("\n" + inventory.getSlotCurrent() + " slots er brugt af " + inventory.getSlotCurrentMax());
                        System.out.println(inventory.getWeightCurrent() + " er vægten oppe på\n");
                    }
                    case 10 -> {
                        System.out.println("You chose 10\nUpdating inventory size...");
                        String slotSize = inventory.increaseMaxSlot(inventory.getSlotCurrentMax(), inventory.getSlotMax(), inventory.getId());
                        System.out.println(slotSize);
                    }
                    case 11 -> {
                        System.out.println("You chose 11");
                        String exported = inventory.exportInventory();
                        System.out.println(exported);
                    }
                    case 12 -> {
                        System.out.println("You chose 12\nPress 1 to search from highest weight to lowest weight\nPress 2 to search from lowest weight to highest weight");
                        int searchType = input.nextInt();
                        if (searchType == 2) {
                            inventory.highestWeight();
                            for (Item item : inventory.getItems()) {
                                System.out.println(item.getName() + ": " + item.getWeight());
                            }
                        }
                        if (searchType == 1) {
                            inventory.lowestWeight();
                            for (Item item : inventory.getItems()) {
                                System.out.println(item.getName() + ": " + item.getWeight());
                            }
                        }
                    }
                    case 13 -> {
                        System.out.println("You chose 13\nPress 1 to search for item type\nPress 2 to search for name");
                        int searchType = input.nextInt();
                        if (searchType == 1) {
                            List<Item> searchByType = inventory.searchByType();
                            for (Item searchByTypes : searchByType) {
                                System.out.println(searchByTypes);
                            }
                        }
                        if (searchType == 2) {
                            List <Item> searchByName = inventory.searchByName();
                            for (Item searchByNames : searchByName) {
                                System.out.println(searchByNames);
                            }
                        }
                    }

                    case 14 -> {
                        System.out.println("Exiting...");
                        return;
                    }

                    default -> {
                        System.out.println("Invalid choice try again");
                    }
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid choice try again ----" + ime);
                input.nextLine();
            }
        }
    }
}