public class Driver {
    public static void main(String[] args) {
        // Create a new Hanoi puzzle with 3 disks,
        // starting at pillar 1 and ending at pillar 3
        Hanoi mySolver = new Hanoi(3, 1, 3);

        // Print the solution
        mySolver.printSolution();
    }
}