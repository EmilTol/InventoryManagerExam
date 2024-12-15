import java.util.Objects;
//Superklasse
public class Item {
    private int id;
    private String name;
    private String description;
    private String type;
    private int weight;
    private int effect;
//Konstruktør
    public Item(int id, String name, String type, int weight, String description, int effect) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.description = description;
        this.effect = effect;
    }

    public int getWeight() {
        return weight;
    }
    String getType(){
        return type;
    }
    public int getEffect() {
        return effect;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }
    @Override
    public String toString() { //Overskriver toString, så den udskriver et Item som vi har oprettet det
        return String.format("id: %d, name: %s, type: %s, weight: %d, description: %s, effect: %d", +
                id, name, type, weight, description, effect);
    }
    @Override
    public boolean equals(Object o) { //Overskriver equals metoden, så den virker med vores Item
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override  //Overskriver hashcode for at equals-metoden fungerer korrekt
    public int hashCode() {
        return Objects.hash(id, name, type, weight, description, effect);
    }
}
