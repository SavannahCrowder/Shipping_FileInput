public class Product { //Default constructor for Product, all values are set to be invalid.

    String name;
    int sku;
    double price;
    double weight;
    int destination;
    int quantity;

    public Product() { //Overloaded constructor that requires all values for construction.

        name = "";
        sku = -1;
        price = -1;
        weight = -1;
        destination = -1;
        quantity = -1;
    }

    public Product(String name, int sku, double price, double weight, int destination, int quantity) {
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.weight = weight;
        this.destination = destination;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    } //Gets the name String.


    public int getDestination() {
        return destination;
    } //Gets the destination int.


    public String toString() { //Returns String format of a Product object.

        String str = quantity + " " + name + ", weighing " + weight + " kilograms: To be shipped to " + destination + ", SKU: " + sku;  
        return str;
    }
}
