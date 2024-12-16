import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UseCase useCase = new UseCase();
        Scanner input = new Scanner(System.in);
//        DatabaseRepository repository = new DatabaseRepository(); // Opretter en ny Databaserepository instans

        System.out.println("---Initializing Inventory---");
        useCase.initiateInventory(1);// Initialiserer inventory med id 1


        while (true) {
            System.out.println("Welcome to Legend of Zel... GameCraft\nChoose your poison\n1 to forge a new item" +
                    "\n2 to see a list of items\n3 to update an existing an item\n4 to delete an item\n5 to create a new inventory" +
                    "\n6 to select and display inventory" +
                    "\n7 to add an item to inventory\n8 to remove item from your inventory\n9 to show items in inventory" +
                    "\n10 to increase slot size by 10\n11 to write out a text file of your inventory\n12 to sort after weight" +
                    "\n13 to search for an item\n14 to exit" +
                    "\n");
            try {
                int choice = input.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("You have chosen to forge a new item.");
                        System.out.println("Enter item id");
                        int id = input.nextInt(); // Læser bruger input for "id" til et nyt item
                        input.nextLine();
                        System.out.println("Enter item name");
                        String name = input.nextLine(); // Læser bruger input for "name" til et nyt item
                        System.out.println("Enter item type");
                        String type = input.nextLine(); // Læser bruger input for "type" til et nyt item
                        System.out.println("Enter item weight");
                        int itemWeight = input.nextInt(); // Læser bruger input for "weight" til et nyt item
                        System.out.println("Enter item description");
                        input.nextLine();
                        String itemDescription = input.nextLine(); // Læser bruger input for "description" til et nyt item
                        System.out.println("Enter item effect");
                        int itemEffect = input.nextInt(); // Læser bruger input for "effect" til et nyt item
                        String answer = useCase.createNewItem(id, name, type, itemWeight, itemDescription,itemEffect);
                        System.out.println(answer);
                    }
                    case 2 -> {
                        System.out.println("You have chosen to view all items in the list");
                        List<Item> items1 = useCase.getAllItems();// Henter alle items fra databasen
                        for (Item item : items1) {
                            System.out.println(item);
                        }
                    }
                    case 3 -> {
                        System.out.println("You have chosen to update an existing item.");
                        System.out.println("Enter the id of the item you want to update: ");
                        int id = input.nextInt(); // Læser bruger input for "id" som skal opdateres
                        input.nextLine();
                        System.out.println("Enter the name of the item: ");
                        String name = input.nextLine(); // Læser bruger input for "name" som skal opdateres
                        System.out.println("enter the new type of the item: ");
                        String type = input.nextLine(); // Læser bruger input for "type" som skal opdateres
                        System.out.println("Enter the new weight of the item: ");
                        int itemWeight = input.nextInt(); // Læser bruger input for "weight" som skal opdateres
                        input.nextLine();
                        System.out.println("Enter the new description of the item: ");
                        String itemDescription = input.nextLine(); // Læser bruger input for "description" som skal opdateres
                        System.out.println("Enter the new effect of the item: ");
                        int itemEffect = input.nextInt(); // Læser bruger input for "effect" som skal opdateres
                        String updated = useCase.updateItem(id, name, type, itemWeight, itemDescription, itemEffect); // Kalder metoden updateItem
                        System.out.println(updated);
                    }
                    case 4 -> {
                        System.out.println("You have chosen to delete an item.");
                        System.out.println("Enter the id of the item to delete");
                        int id = input.nextInt(); // Læser bruger input for "id" som skal slettes
                        String deleted = useCase.deleteItem(id);
                        System.out.println(deleted);
                    }
                    case 5 -> {
                        System.out.println("You have chosen to create a new inventory.");
                        System.out.println("Enter user id for new inventory");
                        int id = input.nextInt(); // Læser bruger input for brugerid til ny inventory
                        int inventoryId = useCase.createNewInventory(id); //Opretter ny inventory i database
                        String initiated = useCase.initiateInventory(inventoryId);// Initialiserer det nye inventory
                        System.out.println(initiated);
                    }
                    case 6 -> {
                        System.out.println("You have chosen to select an inventory.");
                        System.out.println("Enter the id of the inventory to use");
                        int id = input.nextInt(); // Læser bruger input "id" for den inventory som skal vises
                        if (useCase.initiateInventory(id) == null) { // Tjekker om inventory findes og initialisere det
                            System.out.println("No inventory found");
                        }
                        else {
                            String initiated = useCase.initiateInventory(id);
                            System.out.println(initiated + " inventory " + id);
                            System.out.println("\nSlots used: " + useCase.inventory.getSlotCurrent() + // Viser nuværende slots og vægt i inventory
                                    " out of " + useCase.inventory.getSlotCurrentMax() +
                                    "\n \nCurrent weight: " + useCase.inventory.getWeightCurrent() +
                                    " out of " + useCase.inventory.getWeightMax() + "\n");
                        }
                    }
                    case 7 -> {
                        System.out.println("You have chosen to add an item to your inventory.");
                        List<Item> items1 = useCase.getAllItems();// Henter alle items fra databasen
                        for (Item item : items1) {
                            System.out.println(item);
                        }
                        System.out.println("Enter the id of the item to add: ");
                        int itemId = input.nextInt(); // læser bruger input for "id" af item som skal tilføjes til inventory
                        String addToInventory = useCase.addItemToInventory(useCase.inventory.getId(), itemId); // Tilføjer den valgte item til inventory
                        System.out.println(addToInventory);
                    }

                    case 8 -> {
                        System.out.println("You have chosen to remove and item from your inventory.");
                        System.out.println("Enter the id of the item to remove");
                        int itemId = input.nextInt(); // Læser bruger input for "id" af det item der skal fjernes
                        String removeFromInventory = useCase.removeItemFromInventory(useCase.inventory.getId(), itemId); // Fjerner item fra inventory
                        System.out.println(removeFromInventory);
                    }

                    case 9 -> {
                        System.out.println("You have chosen to display all items in your inventory.");

                        Map<Item, Integer> consumables = useCase.showConsumables(); // Viser consumables i inventory !=!=!=! Evt tilføj mere til den her, forstår den ikke godt nok !=!=!=!=!
                        List<Item> nonConsumableItems = useCase.showArmorAndWeapons(); // viser armor and weapons i inventory
                        System.out.println("Armor and weapons in inventory: ");
                        System.out.println("Name------------Type------------Description----------Effect");
                        for (Item item : nonConsumableItems) {
                            System.out.printf("%-15s %-15s %-20s %-15d%n",
                                    item.getName(),
                                    item.getType(),
                                    item.getDescription(),
                                    item.getEffect());
                        }
                        System.out.println("\nConsumables: ");
                        System.out.println("Name------------Type------------Description----------Amount");
                        for (Map.Entry<Item, Integer> entry : consumables.entrySet()) {
                            System.out.printf("%-15s %-15s %-20s %-15d%n",
                                    entry.getKey().getName(),
                                    entry.getKey().getType(),
                                    entry.getKey().getDescription(),
                                    entry.getValue());
                        }
                        System.out.println("\n" + useCase.inventory.getSlotCurrent() + " slots are used out of " + useCase.inventory.getSlotCurrentMax());
                        System.out.println("Current weight is " + useCase.inventory.getWeightCurrent() + "\n");
                    }
                    case 10 -> {
                        System.out.println("You have chosen to expand your inventory capacity.");
                        System.out.println("Updating inventory size...");
                        String slotSize = useCase.increaseMaxSlot(useCase.inventory.getSlotCurrentMax(), useCase.inventory.getSlotMax(), useCase.inventory.getId()); // Øger nuværende max slot
                        System.out.println(slotSize + " slots");
                    }
                    case 11 -> {
                        System.out.println("You have chosen to save your inventory to a text file.");
                        String exported = useCase.exportInventory(); // Expoterer inventory til en tekstfil
                        System.out.println(exported);
                    }
                    case 12 -> { // Case til at søge baseret på vægt
                        System.out.println("You have chosen to sort items by weight.");
                        System.out.println("Press 1 to sort from highest weight to lowest\nPress 2 to sort from lowest weight to highest");
                        int searchType = input.nextInt();
                        if (searchType == 2) {
                            useCase.highestWeight(); // Søger og viser items fra højeste til laveste vægt
                            for (Item item : useCase.inventory.getItems()) {
                                System.out.println(item.getName() + ": " + item.getWeight());
                            }
                        }
                        if (searchType == 1) {
                            useCase.lowestWeight(); // Søger og viser items fra laveste til højeste vægt
                            for (Item item : useCase.inventory.getItems()) {
                                System.out.println(item.getName() + ": " + item.getWeight());
                            }
                        }
                    }
                    case 13 -> {
                        System.out.println("You have chosen to search for an item.");
                        System.out.println("Press 1 to search by item type\nPress 2 to search by name");
                        int searchType = input.nextInt();
                        input.nextLine();
                        if (searchType == 1) {
                            System.out.println("Enter type to search for: Weapon, Armor or Consumable");
                            String type = input.nextLine();
                            List<Item> searchByType = useCase.searchByType(type); // Søger og viser items efter type
                            for (Item searchByTypes : searchByType) {
                                System.out.println(searchByTypes);
                            }
                        }
                        if (searchType == 2) {
                            System.out.println("Enter name or part of name to search for: ");
                            String name = input.nextLine();
                            List <Item> searchByName = useCase.searchByName(name); // Søger og viser items efter navn
                            for (Item searchByNames : searchByName) {
                                System.out.println(searchByNames);
                            }
                        }
                    }

                    case 14 -> { // Afslutter programmet
                        System.out.println("Exiting...");
                        return;
                    }

                    default -> { // Standard til at håndere et ugyldigt valg
                        System.out.println("Invalid choice try again");
                    }
                }
            } catch (InputMismatchException ime) { // Håndterer forkerte input mismatchs
                System.out.println("Invalid choice try again ----" + ime);
                input.nextLine(); // Rydder scanner
            }
        }
    }
}