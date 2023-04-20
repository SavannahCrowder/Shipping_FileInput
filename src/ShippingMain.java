import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ShippingMain {
    /**
     * Self Explanation
     * A while loop that checks through Name, SKU, price, weight, destination, and quanity. Within the while loop are if- else
     * statments that will check if the line starts with Name, SKU, price, weight, destination, and quanity. Within the if
     * statments there will have to be code that skips the Name, SKU, price, weight, destination, and quanity abd just extracts
     * the sting or integer. Finally a else stament that tells the user if the input is invalid.
     */

    //This method loops through the fileScanner parameter provided, using the Scanner.hasNextLine() method and creates
    // a Product object from the data being read. Each file provided will have multiple lines specifying the NAME, SKU,
    // PRICE, WEIGHT, DESTINATION, and QUANTITY. Each file will contain each of these fields, but lines will be provided
    //in different orders, as they may be when provided by different suppliers. Such as:
    // NAME: Berk's Salt Lamps
    // SKU: 90435765
    // PRICE: 12.99
    // WEIGHT: 22.5
    // DESTINATION: 83128
    // QUANTITY: 22
    //Each of these fields will exist in a given "Product.txt" file. Using if statements and the String.contains() method,
    // determine if the newest line in the loop has one of these fields, and if it does, set the appropriate variable.
    // For setting each variable you will need to use String.substring(). Integer.parseInt(int)
    // and Double.parseDouble(double) will also come in handy for parsing and setting each variable.
    //If the fields is not NAME, SKU, PRICE, WEIGHT, DESTINATION, or QUANTITY print an error, but still create the object.
    //For your loop and reading a line from the Scanner object, the Scanner.hasNextLine() and Scanner.nextLine() methods
    // will help!
    //After reading through the entire file and setting the appropriate variables, create and return the new Product
    // object.
    //Parameters:
    //fileScanner - Scanner set to a File object and ready to be read from.
    //Returns:
    //Product that is created from file contents.
    public static Product createProduct(Scanner fileScanner) {
        String name = "";
        int sku = -1;
        double price = -1;
        double weight = -1;
        int destination = -1;
        int quantity = -1;

        while(fileScanner.hasNextLine()) { //will loop through each line in the fileScanner object
            String line = fileScanner.nextLine();

            if (line.startsWith("NAME:")) { //Check if the line starts with name
                name = line.substring(5).replaceAll("\\s+",""); //skips the first 5 characters and takes the value of the name input
                //substring allows to take the string value
            } else if (line.startsWith("SKU:")) { //Check if the line starts with sku
                sku = Integer.parseInt(line.substring(4).replaceAll("\\s+","")); //skips the first 4 characters and takes the value of the sku input
                //integer.parseInt allows to take the integer value
            } else if (line.startsWith("PRICE:")) { //Checks if the line starts with price
                price = Double.parseDouble(line.substring(6).replaceAll("\\s+","")); //skips the first 6 characters and take the value of price
                // TALK about DOUBLE
            } else if (line.startsWith("WEIGHT:")) { //Check if the line starts with weight
                weight = Double.parseDouble(line.substring(7).replaceAll("\\s+","")); //skips the first 7 characters and take the value of weight
                // TALK about DOUBLE
            } else if (line.startsWith("DESTINATION:")) { //Check if the line starts with destination
                destination = Integer.parseInt(line.substring(12).replaceAll("\\s+","")); //skips the first 12 characters and takes the value of the destination input
                //integer.parseInt allows to take the integer value
            } else if (line.startsWith("QUANTITY:")) { //Check if the line starts with quantity
                quantity = Integer.parseInt(line.substring(9).replaceAll("\\s+","")); //skips the first 9 characters and takes the value of the quantity input
                //integer.parseInt allows to take the integer value
            } else { //if the line doesnt start with an of the feilds it will print an error message
                System.out.println("Invalid field: " + line);
            }
        }

            //TODO Student

        return new Product(name, sku, price, weight, destination, quantity);
    }

    public static ShippingManifest createManifest(ArrayList<File> fileList) {
        FileHelper fileHelper = new FileHelper();
        ShippingManifest shipManifest = new ShippingManifest();

        for(File file: fileList) {
            Scanner fileScanner = fileHelper.getFileScanner(file);

            Product newProd = createProduct(fileScanner);
            shipManifest.addProduct(newProd);

            fileScanner.close();
        }

        return shipManifest;
    }

    public static void printSplash() {
        System.out.println("Please type:");
        System.out.println("\"X\" to exit program.");
        System.out.println("\"D\" to distribute products to addresses.");
        System.out.println("\"F-ZIPCODE\" to forwards products to destinations.");
        System.out.println("\"P\" to print current manifest.");
    }
    /**
     * Self Explanation
     * 
     * 
     * 
     */

    //This method prints the splash and reads from the provided Scanner object, using Scanner.nextLine(). While user
    // input does not start with "X", continue to evaluate the user input. To evaluate the user input, if it begins
    // with "D", "F", or "P" then perform the appropriate ShippingManifest method, ShippingManifest.distributeProducts(),
    // ShippingManifest.forwardProducts(int), or ShippingManifest.printManifest(). If the provided command is not "X", "D",
    // "F", or "P" just print a line saying unrecognized command. For the "F"orward case, the "F" command will provided
    // as the letter F , a hyphen, and then the desired zipcode. Example: "F-81256", use the provided zipcode as the
    // parameter for the forwardProducts(int) function. Some helpful methods to use may be Scanner.nextLine(),
    // String.contains(), String.equals(), String.startsWith(), or String.substring()/ You may use none or all of
    // these for your personal implementation.
    public static void go(Scanner scnr, ShippingManifest shipManifest) {
        printSplash();

        String input = scnr.nextLine();

        while (!input.startsWith("X")) {
            if (input.startsWith("D")) {
                shipManifest.distributeProducts();
            } else if (input.startsWith("F-")) {
                int zipCode = Integer.parseInt(input.substring(2));
                shipManifest.forwardProducts(zipCode);
            } else if (input.startsWith("P")) {
                shipManifest.printManifest();
            } else {
                System.out.println("Unrecognized command");
            }
            input = scnr.nextLine();
        }
    }


        //TODO Student



    public static void main(String[] args) {
        String directoryPath = "ShipmentFolder";
        FileHelper fileHelper = new FileHelper();
        ArrayList<File> fileList = fileHelper.getFileDirectory(directoryPath);

        ShippingManifest shipManifest = createManifest(fileList);
        System.out.println("Manifest created from " + directoryPath + "!");

        shipManifest.printManifest();

        Scanner scnr = new Scanner(System.in);
        go(scnr, shipManifest);
    }
}