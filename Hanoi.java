import java.util.ArrayList;
import java.util.Stack;

public class Hanoi {
    private Stack<Integer>[] pillars;
    private ArrayList<String> moves;
    private int numDisks;
    private int startPillar;
    private int targetPillar;

    @SuppressWarnings("unchecked")
    public Hanoi(int numDisks, int startPillar, int targetPillar) {
        this.numDisks = numDisks;
        this.startPillar = startPillar - 1; // Convert to 0-based indexing
        this.targetPillar = targetPillar - 1;
        this.moves = new ArrayList<>();

        // Initialize the pillars
        pillars = new Stack[3];
        for (int i = 0; i < 3; i++) {
            pillars[i] = new Stack<>();
        }

        for (int i = numDisks; i > 0; i--) {
            pillars[this.startPillar].push(i);
        }

        recordState(0);


        solve(numDisks, this.startPillar, this.targetPillar, getAuxiliaryPillar());
    }

    private int getAuxiliaryPillar() {

        for (int i = 0; i < 3; i++) {
            if (i != startPillar && i != targetPillar) {
                return i;
            }
        }
        return -1; // Should never happen
    }

    private void solve(int n, int from, int to, int aux) {
        if (n == 1) {
            // Move single disk from source to target
            pillars[to].push(pillars[from].pop());
            recordState(moves.size());
        } else {

            solve(n - 1, from, aux, to);

            pillars[to].push(pillars[from].pop());
            recordState(moves.size());

            solve(n - 1, aux, to, from);
        }
    }

    private void recordState(int time) {
        StringBuilder state = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            state.append(String.format("t%d Pillar%d: ", time, i + 1));
            Stack<Integer> temp = new Stack<>();

            while (!pillars[i].isEmpty()) {
                temp.push(pillars[i].pop());
            }

            while (!temp.isEmpty()) {
                int disk = temp.pop();
                state.append(disk).append(" ");
                pillars[i].push(disk);
            }

            state.append("\n");
        }
        moves.add(state.toString());
    }

    public void printSolution() {
        System.out.println("My Solution is:");
        for (String state : moves) {
            System.out.print(state);
        }
    }
}