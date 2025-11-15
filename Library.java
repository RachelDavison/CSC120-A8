import java.util.Hashtable;
public class Library extends Building implements LibraryRequirements{

  private Hashtable<String, Boolean> collection;
  private boolean hasElevator;

  /**
   * Constructor for Library class
   * @param name
   * @param address
   * @param nFloors
   */
  public Library(String name, String address, int nFloors) {
    super(name, address, nFloors);
    this.collection = new Hashtable<String, Boolean>();
    this.hasElevator = false;
  }

  /**
   * Overloaded constructor for Library class with elevator option
   * @param name
   * @param address
   * @param nFloors
   * @param hasElevator
   */
  public Library(String name, String address, int nFloors, boolean hasElevator) {
    this(name, address, nFloors);
    this.hasElevator = hasElevator;
  }

  /**
   * Overridden method, displays the options available in the Library class
   */
  public void showOptions(){
    super.showOptions();
    System.out.println(" + addTitle(title)");
    System.out.println(" + removeTitle(title)");
    System.out.println(" + isAvailable(title)");
    System.out.println(" + checkOut(title)");
    System.out.println(" + returnBook(title)");
    System.out.println(" + containsTitle(title)");
    System.out.println(" + printCollection()");
  }

  /**
   * Overrides goToFloor to account for elevator presence
   * @param floorNum
   */
  public void goToFloor(int floorNum) {
    if (!this.hasElevator && floorNum > activeFloor+1 || floorNum < activeFloor-1) {
      throw new RuntimeException("This library does not have an elevator. You can only go up or down one floor at a time.");
    }
    super.goToFloor(floorNum);
  }

  /**
   * Adds a title to the library collection
   * @param title
   */
  public void addTitle(String title) {
    this.collection.put(title, true);
  }

  /**
   * Overloaded method to add multiple titles at once
   * @param titles
   */
  public void addTitle(String[] titles) {
    for (String title : titles) {
      this.collection.put(title, true);
    }
  }

  /**
   * Removes a title from the library collection
   * @param title
   * @return the removed title
   */
  public String removeTitle(String title) {
    if (!this.collection.containsKey(title)) {
      throw new RuntimeException("Title not found in collection.");
    }
    this.collection.remove(title);
    return title;
  }

  /**
   * Overloaded method to remove multiple titles at once
   * @param titles
   * @return array of removed titles
   */
  public String[] removeTitle(String[] titles) {
    String[] removedTitles = new String[titles.length];
    for (int i = 0; i < titles.length; i++) {
      try {
        removedTitles[i] = removeTitle(titles[i]);
      } catch (RuntimeException e) {
        System.out.println("Could not remove \"" + titles[i] + "\": " + e.getMessage());
        removedTitles[i] = null;
      }
    }
    return removedTitles;
  }

  /**
   * Checks if a title is available for checkout
   * @param title
   * @return true if available, false otherwise   
   */
  public boolean isAvailable(String title) {
    if (this.collection.containsKey(title)) {
      return this.collection.get(title);
    }
    return false;
  }

  /**
   * Checks out a title from the library
   * @param title
   */
  public void checkOut(String title) {
    if (this.collection.containsKey(title) && this.collection.get(title)) {
      this.collection.put(title, false);
    } else {
      throw new RuntimeException("Title is not available for checkout.");
    }
  }

  /**
   * Returns a title to the library
   * @param title
   */
  public void returnBook(String title) {
    if (this.collection.containsKey(title) && !this.collection.get(title)) {
      this.collection.put(title, true);
    } else if (this.collection.containsKey(title)) {
       throw new RuntimeException("Title is not checked out.");
    } else {
       throw new RuntimeException("Title does not belong to this library.");
    }
  }

  /**
   * Returns a title to the library, removes it if damaged
   * @param title
   * @param isDamaged
   */
  public void returnBook(String title, boolean isDamaged) {
    if (this.collection.containsKey(title) && isDamaged) {
      this.collection.remove(title);
      System.out.println("The book \"" + title + "\" has been removed from the collection due to damage.");
    } else {
      returnBook(title);
    }
  }

  /**
   * Overloaded method to return multiple books at once
   * @param titles
   */
  public void returnBook(String[] titles) {
    for (String title : titles) {
      try {
        returnBook(title);
      } catch (RuntimeException e) {
        System.out.println("Could not return \"" + title + "\": " + e.getMessage());
      }
    }
  }

  /**
   * Checks if the library contains a title
   * @param title
   * @return true if the title is in the collection, false otherwise
   */
  public boolean containsTitle(String title) {
    return this.collection.containsKey(title);
  }

  /**
   * Prints the entire collection with availability status
   */
  public void printCollection() {
    for (String title : this.collection.keySet()) {
      String status = this.collection.get(title) ? "Available" : "Checked Out";
      System.out.println(title + ": " + status);
    }
  }
  
  public static void main(String[] args) {
    Library library = new Library("Neilson", "7 Neilson Drive, Northampton, MA 01063", 4);
    library.addTitle("anne of Green Gables");
    library.addTitle("the great gatsby");
    library.printCollection();
    library.checkOut("anne of Green Gables");
    library.printCollection();
  }
  
}