import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {

    public DatabaseRepository() {
    }

    public String addItem(Item item) {
        String sql = "INSERT INTO item (iditem, name, type, weight, description, effect) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getType());
            preparedStatement.setInt(4, item.getWeight());
            preparedStatement.setString(5, item.getDescription());
            preparedStatement.setInt(6, item.getEffect());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return ("New item added");
            } else {
                return ("Item not added");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            return "Error inserting item";
        }
        return null;
    }

    //    //read
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item";
        try (Connection connection = DatabaseConnection.getconnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("iditem");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int weight = resultSet.getInt("weight");
                String description = resultSet.getString("description");
                int effect = resultSet.getInt("effect");

                items.add(new Item(id, name, type, weight, description, effect));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    //update
    public String updateItem(Item item) {
        String sql = "UPDATE item SET name = ?, type = ?, weight = ?, description = ?, effect = ? WHERE iditem = ?";

        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getType());
            preparedStatement.setInt(3, item.getWeight());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setInt(5, item.getEffect());
            preparedStatement.setInt(6, item.getId());

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Item updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ("Item updated");
    }

    //    //delete
    public String deleteItem(int id) {
        String sql = "DELETE FROM item WHERE iditem = ?";

        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int deletedRows = preparedStatement.executeUpdate();
            if (deletedRows > 0) {
                System.out.println("Item number " + id + " deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ("Item deleted");
    }

    public int createNewInventory(int idUser){
        String sql = "INSERT INTO inventory (iduser) VALUES (?);";
        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setInt(1, idUser);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New inventory created");
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        System.out.println("Key generated");
                        int newInventoryId = (generatedKeys.getInt(1));
                        return newInventoryId;
                    }
                    else {
                        throw new SQLException("Creating inventory failed, no ID obtained.");
                    }
                }
            } else {
                return 0;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e) {

        }
        return 0;
    }

    public List <Item> initiateInventory(int inventoryId) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT inventory.idinventory, item.*\n" +
                "FROM inventory\n" +
                "JOIN inventoryhasitem ON inventory.idinventory = inventoryhasitem.fkinventory\n" +
                "JOIN item ON inventoryhasitem.fkitem = item.iditem\n" +
                "WHERE idinventory = ?";

        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, inventoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int iditem = resultSet.getInt("iditem");
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                int weight = resultSet.getInt("weight");
                String description = resultSet.getString("description");
                int effect = resultSet.getInt("effect");

                if (type.equals("Armor")) {
                    Item item = new Armor(iditem, name, type, weight, description, effect);
                    items.add(item);
                }
                else if (type.equals("Weapon")){
                    Item item = new Weapon(iditem, name, type, weight, description, effect);
                    items.add(item);
                }
                else if (type.equals("Consumable")) {
                    Item item = new Consumable(iditem, name, type, weight, description, effect);
                    items.add(item);
                }

                //items.add(new Item(iditemtest, name, type, weight, description, effect));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public int initiateMaxSlots(int inventoryId) {
        String sql = "SELECT slotcurrentmax\n" +
                "FROM inventory\n" +
                "WHERE idinventory = ?";

        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, inventoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int slotCurrentMax = resultSet.getInt("slotcurrentmax");
                return slotCurrentMax;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int initiateSlots(int inventoryId) {
        String sql = "SELECT slotcurrent\n" +
                "FROM inventory\n" +
                "WHERE idinventory = ?";

        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, inventoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int slotCurrent = resultSet.getInt("slotcurrent");
                return slotCurrent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String setSlotSize(int slotNewMax, int inventoryId) {
        String sql = "UPDATE inventory SET slotcurrentmax = ? WHERE idinventory = ?";

        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, slotNewMax);
            preparedStatement.setInt(2, inventoryId);

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Updated in database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Slot size updated in database";
    }

    public String addItemToInventory(int fkinventory, int fkitem) {
        String sql = "INSERT INTO inventoryhasitem (fkinventory, fkitem) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, fkinventory);
            preparedStatement.setInt(2, fkitem);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return ("Item added to the inventory");
            } else {
                return ("Item not added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String setSlot(int currentSlot, int inventoryId){
        String sql = "UPDATE inventory SET slotcurrent = ? WHERE idinventory = ?";

        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, (currentSlot));
            preparedStatement.setInt(2, inventoryId);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                //System.out.println("Slots updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ("Slots updated");
    }

    public String removeItemFromInventory(int fkinventory, int fkitem) {
        String sql = "DELETE FROM inventoryhasitem\nWHERE fkinventory = ? AND fkitem = ?\nLIMIT 1";
        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, fkinventory);
            preparedStatement.setInt(2, fkitem);

            int deletedRows = preparedStatement.executeUpdate();
            if (deletedRows > 0) {
                return ("Item removed from the inventory");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Item getOneItem(int id) {

        String sql = "SELECT * FROM item WHERE iditem = ?";
        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    int idItem = resultSet.getInt("iditem");
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    int weight = resultSet.getInt("weight");
                    String description = resultSet.getString("description");
                    int effect = resultSet.getInt("effect");
                    if (type.equals("Armor")) {
                        Item item = new Armor(idItem, name, type, weight, description, effect);
                        return item;
                    }
                    else if (type.equals("Weapon")){
                        Item item = new Weapon(idItem, name, type, weight, description, effect);
                        return item;
                    }
                    else if (type.equals("Consumable")) {
                        Item item = new Consumable(idItem, name, type, weight, description, effect);
                        return item;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public List<Integer> getAllInventoryIds() {
//        List<Integer> inventoryIds = new ArrayList<>();
//        String sql = "SELECT idinventory FROM inventory";
//        try (Connection connection = DatabaseConnection.getconnection();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(sql)) {
//            while (resultSet.next()) {
//                inventoryIds.add(resultSet.getInt("idinventory"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return inventoryIds;
//    }
//
//    public Inventory getInventoryById(int id) {
//        String sql = "SELECT * FROM inventory WHERE idinventory = ?";
//        try (Connection connection = DatabaseConnection.getconnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                int userId = resultSet.getInt("iduser");
//                List<Item> items = initiateInventory(id);
//                return new Inventory(id, userId, 0, 0, items);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } return null;
//    }
}