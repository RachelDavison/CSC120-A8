import java.util.ArrayList;

public class House extends Building implements HouseRequirements{

  private ArrayList<Student> residents;
  private boolean hasDiningRoom;
  private boolean hasElevator;

  /**
   * Constructor for House class
   * @param name
   * @param address
   * @param nFloors
   * @param hasDiningRoom
   */
  public House(String name, String address, int nFloors, boolean hasDiningRoom) {
    super(name, address, nFloors);
    this.hasDiningRoom = hasDiningRoom;
    this.residents = new ArrayList<Student>();
    this.hasElevator = false;
  }

  /**
   * Overloaded constructor for House class with elevator option
   * @param name
   * @param address
   * @param nFloors
   * @param hasDiningRoom
   * @param hasElevator
   */
  public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) {
    this(name, address, nFloors, hasDiningRoom);
    this.hasElevator = hasElevator;
  }

  /**
   * Overriden method, displays the options available in the House class
   */
  public void showOptions(){
    super.showOptions();
    System.out.println(" + moveIn(student)");
    System.out.println(" + moveOut(student)");
    System.out.println(" + hasDiningRoom()");
    System.out.println(" + nResidents()");
    System.out.println(" + isResident(student)");
  }

  /**
   * Overrides goToFloor to account for elevator presence
   * @param floorNum
   */
  public void goToFloor(int floorNum) {
    if (!this.hasElevator && floorNum > activeFloor + 1 || floorNum < activeFloor - 1) {
      throw new RuntimeException("This house does not have an elevator. You can only go up or down one floor at a time.");
    }
    super.goToFloor(floorNum);
  }

  /**
   * Adds a student to the house residents
   * @param student
   */
  public void moveIn(Student student) {
    if (this.residents.contains(student)) {
      throw new RuntimeException(student.getName() + " is already a resident of this house.");
    }
    this.residents.add(student);
    System.out.println(student.getName() + " has moved into " + this.getName());
  }

  /**
   * Adds multiple students to the house residents
   * @param students
   */
  public void moveIn(ArrayList<Student> students) {
    for (Student student : students) {
      try {
        moveIn(student);
      } catch (RuntimeException e) {
        System.out.println(student.getName() + " could not move in: " + e.getMessage());
      }
    }
  }

  /**
   * Removes a student from the house residents
   * @param student
   * @return the removed student
   */
  public Student moveOut(Student student) {
    if (!this.residents.contains(student)) {
      throw new RuntimeException(student.getName() + " is not a resident of this house.");
    }
    this.residents.remove(student);
    System.out.println(student.getName() + " has moved out of " + this.getName());
    return student;
  }

  /**
   * Removes multiple students from the house residents
   * @param students
   * @return null
   */
  public Student moveOut(ArrayList<Student> students) {
    for (Student student : students) {
      try { 
        moveOut(student);
      } catch (RuntimeException e) {
        System.out.println(student.getName() + "could not move out: " + e.getMessage());
      }
    }
    return null;
    
  }

  /**
   * Checks if the house has a dining room
   * @return true if the house has a dining room, false otherwise
   */
  public boolean hasDiningRoom() {
    return this.hasDiningRoom;
  }

  /**
   * Gets the number of residents in the house
   * @return number of residents
   */
  public int nResidents() {
    return this.residents.size();
  }

  /**
   * Checks if a student is a resident of the house
   * @param student
   * @return true if the student is a resident, false otherwise
   */
  public boolean isResident(Student student) {
    return this.residents.contains(student);
  }

  public static void main(String[] args) {
    new House("house", "123 Main St", 2, true);
  }

}