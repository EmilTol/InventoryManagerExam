import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseRepository repository = new DatabaseRepository(); // Opretter en ny Databaserepository instans
        List <Item> items = new ArrayList<>(); // Opretter en tom liste af Item objekter
        Scanner input = new Scanner(System.in);
        Inventory inventory = new Inventory(0,0, 0,0, items); // Opretter en ny inventory instans med "tomme" værdier



        System.out.println("---Initializing Inventory---");
        inventory = inventory.initiateInventory(1); // Initialisere inventory med id 1

//        System.out.println("Please choose the id of the inventory you want to use");
//        List<Integer> inventoryIds = repository.getAllInventoryIds();
//        for (Integer id : inventoryIds)
//            System.out.println("Inventory ID: " + id);

//        boolean validInventoryId = false;
//        while (!validInventoryId) {
//            try {
//                int selectedInventoryId = input.nextInt();
//                inventory = repository.getInventoryById(selectedInventoryId);
//                if (inventory != null) {
//                    validInventoryId = true;
//                    System.out.println("Initializing inventory with ID: " + selectedInventoryId);
//                } else {
//                    System.out.println("Invalid inventory ID, please try again");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input, Please type the number of the account ID");
//                input.nextLine();
//            }
//        }

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
                        Item item = new Item(id, name, type, itemWeight, itemDescription, itemEffect); // Opretter et nyt item objekt
                        String answer = repository.addItem(item); // Tilføjer det nye item objekt til databasen
                        System.out.println(answer);
                    }
                    case 2 -> {
                        System.out.println("You have chosen to view all items in the list");
                        List<Item> items1 = repository.getAllItems(); // Henter alle items fra databasen
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
                        System.out.println("Enther the new effect of the item: ");
                        int itemEffect = input.nextInt(); // Læser bruger input for "effect" som skal opdateres
                        Item item = new Item(id, name, type, itemWeight, itemDescription, itemEffect); // Opretter et nyt item objekt
                        String updated = repository.updateItem(item); // Tilføjer den opdateret item til database
                        System.out.println(updated);
                    }
                    case 4 -> {
                        System.out.println("You have chosen to delete an item.");
                        System.out.println("Enter the id of the item to delete");
                        int id = input.nextInt(); // Læser bruger input for "id" som skal slettes
                        String deleted = repository.deleteItem(id); // Sletter item fra database
                        System.out.println(deleted);
                    }
                    case 5 -> {
                        System.out.println("You have chosen to create a new inventory.");
                        System.out.println("Enter new user id");
                        int id = input.nextInt(); // Læser bruger input for brugerid til ny inventory
                        int inventoryId = repository.createNewInventory(id); //Opretter ny inventory i database
                        inventory = inventory.initiateInventory(inventoryId); // Initialisere inventory med det nye brugerid
                    }
                    case 6 -> {
                        System.out.println("You have chosen to select and display an inventory.");
                        System.out.println("Enter the id of the inventory to display");
                        int id = input.nextInt(); // Læser bruger input "id" for den inventory som skal vises
                        if (inventory.initiateInventory(id) == null) { // Tjekker om inventory findes og initialisere det
                            System.out.println("No inventory found");
                        }
                        else {
                            inventory = inventory.initiateInventory(id);

                            for (Item item : inventory.getItems()) { // Viser alle items i det valgte inventory
                                System.out.println(item);
                            }
                            System.out.println("\nSlots used: " + inventory.getSlotCurrent() + // Viser nuværende slots og vægt i inventory
                                    " out of " + inventory.getSlotCurrentMax() +
                                    "\n \nCurrent weight: " + inventory.getWeightCurrent() +
                                    " out of " + inventory.getWeightMax() + "\n");
                        }
                    }
                    case 7 -> {
                        System.out.println("You have chosen to add an item to your inventory.");
                        System.out.println("Enter the id of the item to add: ");
                        int itemId = input.nextInt(); // læser bruger input for "id" af item som skal tilføjes til inventory
                        String a2i = inventory.addItemToInventory(inventory.getId(), itemId); // Tilføjer den valgte item til inventory !=!=!=!Note!=!=!=! Hvad betyder a2i?
                        System.out.println(a2i);
                        System.out.println("The following items are now in your inventory:\n");
                        for (Item item : inventory.getItems()) { // Viser alle items i inventory !=!=!=!=!=!Note=!=!=!!=!= Skal måske fjernes eller ændres?
                            System.out.println(item.getName());
                        }
                        System.out.println("\nSlots used: " + inventory.getSlotCurrent() + // Viser nuværende slots og vægt i inventory ^^^^
                                " out of " + inventory.getSlotCurrentMax() +
                                "\n \nCurrent weight: " + inventory.getWeightCurrent() +
                                " out of " + inventory.getWeightMax() + "\n");
                    }
                    case 8 -> {
                        System.out.println("You have chosen to remove and item from your inventory.");
                        System.out.println("Enter the id of the item to remove");
                        int itemId = input.nextInt(); // Læser bruger input for "id" af det item der skal fjernes
                        String removeFromInventory = inventory.removeItemFromInventory(inventory.getId(), itemId); // Fjerner item fra inventory
                        System.out.println(removeFromInventory);
                    }
                    case 9 -> {
                        System.out.println("You have chosen to display all items in your inventory.");
                        Map<Item, Integer> consumables = inventory.showConsumables(); // Viser consumables i inventory !=!=!=! Evt tilføj mere til den her, forstår den ikke godt nok !=!=!=!=!
                        List <Item> nonConsumableItems = inventory.showArmorAndWeapons(); // viser armor and weapons i inventory
                        System.out.println("Armor and weapons in inventory: ");
                        for (Item item : nonConsumableItems) {
                            System.out.println(item);
                        }
                        System.out.println("\nConsumables: ");
                        for (Map.Entry<Item, Integer> entry : consumables.entrySet()) {
                            System.out.println((entry.getValue()) + " of " + entry.getKey());
                        }
                        System.out.println("\n" + inventory.getSlotCurrent() + " slots are used out of " + inventory.getSlotCurrentMax());
                        System.out.println("Current weight is " + inventory.getWeightCurrent() + "\n");
                    }
                    case 10 -> {
                        System.out.println("You have chosen to expand your inventory capacity.");
                        System.out.println("Updating inventory size...");
                        String slotSize = inventory.increaseMaxSlot(inventory.getSlotCurrentMax(), inventory.getSlotMax(), inventory.getId()); // Er måske dum, men øger vi ikke maxslots?
                        System.out.println(slotSize);
                    }
                    case 11 -> {
                        System.out.println("You have chosen to save your inventory to a text file.");
                        String exported = inventory.exportInventory(); // Expoterer inventory til en tekstfil
                        System.out.println(exported);
                    }
                    case 12 -> { // Case til at søge basseret på vægt
                        System.out.println("You have chosen to sort items by weight.");
                        System.out.println("Press 1 to sort from highest weight to lowest\nPress 2 to sort from lowest weight to highest");
                        int searchType = input.nextInt();
                        if (searchType == 2) {
                            inventory.highestWeight(); // Søger og viser items fra højeste til laveste vægt
                            for (Item item : inventory.getItems()) {
                                System.out.println(item.getName() + ": " + item.getWeight());
                            }
                        }
                        if (searchType == 1) {
                            inventory.lowestWeight(); // Søger og viser items fra laveste til højeste vægt
                            for (Item item : inventory.getItems()) {
                                System.out.println(item.getName() + ": " + item.getWeight());
                            }
                        }
                    }
                    case 13 -> {
                        System.out.println("You have chosen to search for an item.");
                        System.out.println("Press 1 to search by item type\nPress 2 to search by name");
                        int searchType = input.nextInt();
                        if (searchType == 1) {
                            List<Item> searchByType = inventory.searchByType(); // Søger og viser items efter type
                            for (Item searchByTypes : searchByType) {
                                System.out.println(searchByTypes);
                            }
                        }
                        if (searchType == 2) {
                            List <Item> searchByName = inventory.searchByName(); // Søger og viser items efter navn
                            for (Item searchByNames : searchByName) {
                                System.out.println(searchByNames);
                            }
                        }
                    }

                    case 14 -> { // Afslutter programmet ved at returnere? Ved ikke hvordan man bedst skal formulere det!?!!=!=!=!=!=
                        System.out.println("Exiting...");
                        return;
                    }

                    default -> { // Standard til at håndere et ugyldigt valg
                        System.out.println("Invalid choice try again");
                    }
                }
            } catch (InputMismatchException ime) { // Håndtere forkerte input mismatchs
                System.out.println("Invalid choice try again ----" + ime);
                input.nextLine(); // Rydder scanner
            }
        }
    }
}