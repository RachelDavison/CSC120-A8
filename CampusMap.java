import java.util.ArrayList;
import java.util.Scanner;

public class CampusMap {

    ArrayList<Building> buildings;

    /* Default constructor, initializes empty ArrayList */
    public CampusMap() {
        buildings = new ArrayList<Building>();
    }

    /**
     * Adds a Building to the map
     * @param b the Building to add
     */
    public void addBuilding(Building b) {
        System.out.println("Adding building...");
        buildings.add(b);
        System.out.println("-->Successfully added " + b.getName() + " to the map.");
    }

    /**
     * Removes a Building from the map
     * @param b the Building to remove
     * @return the removed Building
     */
    public Building removeBuilding(Building b) {
        System.out.println("Removing building...");
        buildings.remove(b);
        System.out.println("-->Successfully removed " + b.getName() + " from the map.");
        return b;
    }

    /**
     * To String method
     * @return a list of all the buildings
     */
    public String toString() {
        String mapString = "DIRECTORY of BUILDINGS";

        for (int i = 0; i < this.buildings.size(); i ++) {
            mapString += "\n  " + (i+1) + ". "+ this.buildings.get(i).getName() + " (" + this.buildings.get(i).getAddress() + ")";
        }
        return mapString;
    }

    public static void main(String[] args) {
        CampusMap myMap = new CampusMap();
        // Adding buildings to the campus map
        myMap.addBuilding(new Building("Ford Hall", "100 Green Street Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Bass Hall", "4 Tyler Court Northampton, MA 01063", 4));
        Cafe cafe = new Cafe("Campus Cafe", "100 College St, Northampton, MA 01063", 1);
        myMap.addBuilding(cafe);
        Library library = new Library("Neilson", "7 Neilson Drive, Northampton, MA 01063", 4, true);
        myMap.addBuilding(library);
        House house = new House("Tyler House", "164 Green St, Northampton, MA 01063", 3, true, false);
        myMap.addBuilding(house);
        myMap.addBuilding(new Building("Seelye Hall", "2 Seelye Dr, Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("McConnell Hall", "1 McConnell Dr, Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("John M. Greene Hall", "26 University Dr, Northampton, MA 01063", 3));
        myMap.addBuilding(new Building("Campus Center", "100 Elm St, Northampton, MA 01063", 4));
        myMap.addBuilding(new Library("Hillyer Art Library", "16 Elm St, Northampton, MA 01063", 2));
        myMap.addBuilding(new Cafe("Compass Cafe", "7 Neilson Drive, Northampton, MA 01063"));
        myMap.addBuilding(new House("Chapin House", "3 Chapin Way, Northampton, MA 01063", 3, false));
        cafe.sellCoffee(5); // Selling coffee from Campus Cafe using overloaded method
        String[] booksToReturn = {"1984 by George Orwell", "To Kill a Mockingbird by Harper Lee"};
        library.addTitle("Go Tell it on the Mountain by James Baldwin");
        library.addTitle(booksToReturn); // Adding multiple books using overloaded method
        library.checkOut("1984 by George Orwell");
        library.checkOut("Go Tell it on the Mountain by James Baldwin");
        library.returnBook(booksToReturn); // Returning multiple books using overloaded method
        library.returnBook("Go Tell it on the Mountain by James Baldwin", true); // Returning a damaged book using overloaded method
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Rachel", "9900000", 2029));
        house.moveIn(students);
        System.out.println(myMap);
        // Introducing and setting up the campus map
        String stay = "y";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Campus!");
        // Loop to explore buildings
        while(stay.equals("y")){
            // Prompt user to choose a building, and continue looping until they ask to leave
            System.out.println("Which building would you like to enter? Input the number of the building of your choice. ");;
            System.out.println(myMap);
            int choice = scanner.nextInt();
            Building selectedBuilding = myMap.buildings.get(choice - 1);
            selectedBuilding.enter();
            System.out.println("You are inside of " + selectedBuilding.getName() + ".");
            selectedBuilding.showOptions();
            System.out.println("WARNING: Be sure to input the method exactly as shown, including parentheses. If you do not, the action will not be recognized.");
            System.out.println("WARNING: You have just entered this building. If you wish to enter again, you must exit first.");
            String stayHere = "y";
            while (stayHere.equals("y")){
                // Prompt user to choose an action within the building, and continue looping until they ask to leave the building
                System.out.println("What would you like to do? ");
                String action = scanner.next();
                if (action.equals("enter()")) {
                    // Call enter method
                    selectedBuilding.enter();
                } else if (action.equals("exit()")) {
                    // Call exit method
                    selectedBuilding.exit();
                } else if (action.equals("goUp()")) {
                    // Call goUp method
                    selectedBuilding.goUp();
                } else if (action.equals("goDown()")) {
                    // Call goDown method
                    selectedBuilding.goDown();
                } else if (action.startsWith("goToFloor")) {
                    // Prompt for floor number and call goToFloor method
                    System.out.println("Enter the floor number you want to go to:");
                    int floorNum = scanner.nextInt();
                    selectedBuilding.goToFloor(floorNum);
                } else if (action.startsWith("sellCoffee")) {
                    // Prompt for coffee details and call sellCoffee method
                    System.out.println("Enter the size of your coffee:");
                    int size = scanner.nextInt();
                    System.out.println("Enter the number of sugar packets:");
                    int nSugarPackets = scanner.nextInt();
                    System.out.println("Enter the number of creams:");
                    int nCreams = scanner.nextInt();
                    ((Cafe)selectedBuilding).sellCoffee(size, nSugarPackets, nCreams);
                } else if (action.startsWith("addTitle(")) {
                    // Prompt for book title and call addTitle method
                    System.out.println("What book would you like to add?");
                    String title = scanner.nextLine();
                    ((Library)selectedBuilding).addTitle(title);
                } else if (action.startsWith("returnBook(")) {
                    // Prompt for book title and call returnBook method
                    System.out.println("What book are you returning?");
                    String title = scanner.nextLine();
                    System.out.println("Is it damaged? (true/false)");
                    boolean isDamaged = scanner.nextBoolean();
                    ((Library)selectedBuilding).returnBook(title, isDamaged);
                } else if(action.startsWith("checkOut(")) {
                    // Prompt for book title and call checkOut method
                    System.out.println("What book would you like to check out?");
                    String title = scanner.nextLine();
                    ((Library)selectedBuilding).checkOut(title);
                } else if(action.startsWith("removeTitle(")) {
                    // Prompt for book title and call removeTitle method
                    System.out.println("What book would you like to remove from the collection?");
                    String title = scanner.nextLine();
                    ((Library)selectedBuilding).removeTitle(title);
                } else if (action.startsWith("isAvailable(")) {
                    // Prompt for book title and call isAvailable method
                    System.out.println("What book would you like to check availability for?");
                    String title = scanner.nextLine();
                    boolean available = ((Library)selectedBuilding).isAvailable(title);
                    if (available) {
                        System.out.println(title + " is available for checkout.");
                    } else {
                        System.out.println(title + " is not available for checkout.");
                    }
                } else if (action.startsWith("containsTitle")){
                    // Prompt for book title and call containsTitle method
                    System.out.println("What book would you like to check if it's in the collection?");
                    String title = scanner.nextLine();
                    boolean contains = ((Library)selectedBuilding).containsTitle(title);
                    if (contains) {
                        System.out.println(title + " is in the library's collection.");
                    } else {
                        System.out.println(title + " is not in the library's collection.");
                    }
                } else if (action.startsWith("printCollection()")) {
                    // Call printCollection method
                    ((Library)selectedBuilding).printCollection();
                } else if (action.startsWith("moveOut(")) {
                    // Prompt for student details and number of people and call moveOut method
                    System.out.println("How many people are moving out?");
                    int nPeople = scanner.nextInt();
                    if (nPeople <= 0) {
                        System.out.println("Number of people must be positive.");
                        continue;
                    } else if (nPeople == 1){
                        System.out.println("Enter the name of the person moving out:");
                        String name = scanner.next();
                        System.out.println("Enter their ID number:");
                        String id = scanner.next();
                        System.out.println("Enter their class year:");
                        int year = scanner.nextInt();
                        ((House)selectedBuilding).moveOut(new Student(name, id, year));
                    } else {
                        ArrayList<Student> departingStudents = new ArrayList<Student>();
                        for (int i = 0; i < nPeople; i++) {
                            System.out.println("Enter the name of person " + (i+1) + ":");
                            String name = scanner.next();
                            System.out.println("enter their ID number:");
                            String id = scanner.next();
                            System.out.println("Enter their class year:");
                            int year = scanner.nextInt();
                            departingStudents.add(new Student(name, id, year));
                        }
                        ((House)selectedBuilding).moveOut(departingStudents);
                    }
                } else if (action.startsWith("moveIn(")) {
                    // Prompt for student details and number of people and call moveIn method
                    System.out.println("How many people are moving in?");
                    int nPeople = scanner.nextInt();
                    if (nPeople <= 0) {
                        System.out.println("Number of people must be positive.");
                        continue;
                    } else if (nPeople == 1){
                        System.out.println("Enter the name of the person moving in:");
                        String name = scanner.next();
                        System.out.println("Enter their ID number:");
                        String id = scanner.next();
                        System.out.println("Enter their class year:");
                        int classYear = scanner.nextInt();
                        ((House)selectedBuilding).moveIn(new Student(name, id, classYear));
                    } else {
                        ArrayList<Student> newStudents = new ArrayList<Student>();
                        for (int i = 0; i < nPeople; i++) {
                            System.out.println("Enter the name of person " + (i+1) + ":");
                            String name = scanner.next();
                            System.out.println("Enter their ID number:");
                            String id = scanner.next();
                            System.out.println("Enter their class year:");
                            int classYear = scanner.nextInt();
                            newStudents.add(new Student(name, id, classYear));
                        }
                        ((House)selectedBuilding).moveIn(newStudents);
                    }
                } else if (action.equals("nResidents()")) {
                    // Call nResidents method
                    int nResidents = ((House)selectedBuilding).nResidents();
                    System.out.println("There are " + nResidents + " residents in this house.");
                } else if (action.startsWith("isResident(")) {
                    // Prompt for student details and call isResident method
                    System.out.println("Enter the name of the student to check:");
                    String name = scanner.next();
                    System.out.println("Enter their ID number:");
                    String id = scanner.next();
                    System.out.println("Enter their class year:");
                    int year = scanner.nextInt();
                    boolean isRes = ((House)selectedBuilding).isResident(new Student(name, id, year));
                    if (isRes) {
                        System.out.println(name + " is a resident of this house.");
                    } else {
                        System.out.println(name + " is not a resident of this house.");
                    }
                } else if (action.equals("hasDiningRoom()")) {
                    // Call hasDiningRoom method
                    boolean hasDR = ((House)selectedBuilding).hasDiningRoom();
                    if (hasDR) {
                        System.out.println("This house has a dining room.");
                    } else {
                        System.out.println("This house does not have a dining room.");
                    }
                } else {
                    System.out.println("Invalid action. Please try again.");
                }
                System.out.println("Would you like to do anything else in " + selectedBuilding.getName() + "? (y/n)");
                stayHere = scanner.next();
                while (!stayHere.equals("y") && !stayHere.equals("n")) {
                    // Input validation for continuing in the building
                    System.out.println("That is not a valid input. Please enter 'y' or 'n'.");
                    stayHere = scanner.next();
                }
                if (stayHere.equals("y")){
                    System.out.println("Here are the available options in " + selectedBuilding.getName() + ":");
                    selectedBuilding.showOptions();
                }
            }
            System.out.println("Would you like to continue exploring the campus? (y/n)");
            stay = scanner.next();
        }
        scanner.close();
        System.out.println("Thank you for visiting the campus!"); 
    }
    
}
