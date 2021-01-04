package rhickersberger;

/**
 * Testet den generischen Stapel.
 * @author René Hickersberger
 * @version 2020-01-04
 */
public class StackTest {
    /**
     * Testet den generischen Stapel.
     *
     * @param args Keine Bedeutung.
     */
    public static void main(String[] args) {
        /**********************
         * Dynamischer Stapel *
         **********************/
        {
            System.out.println(
                    "Dynamischer Stapel\n" +
                            "==================");
            Stack<Integer> s = new Stack<Integer>();

            try {
                s.push(Integer.valueOf(5));
                s.push(Integer.valueOf(-15));
                s.push(Integer.valueOf(23));
                s.push(Integer.valueOf(12));
                System.out.println("Stack: " + s.list());
                System.out.println("Peek:  " + s.peek().toString());
                System.out.println("Stack: " + s.list());
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
            } catch (StackFullException | StackEmptyException e) {
                e.printStackTrace();
            }
        }

        /***************************
         * Stapel mit fester Länge *
         ***************************/
        {
            System.out.println(
                    "Stapel mit fester Länge\n" +
                            "=======================");
            Stack<Integer> s = new Stack<Integer>(4);
            try {
                s.push(Integer.valueOf(1));
                s.push(Integer.valueOf(2));
                s.push(Integer.valueOf(3));
                s.push(Integer.valueOf(4));
                System.out.println("Stack: " + s.list());
            } catch (StackFullException e) {
                e.printStackTrace();
            }
            try {
                s.push(Integer.valueOf(5));
            } catch (StackFullException e) {
                System.out.println("Hinzufügen fehlgeschlagen,\nStack: " + s.list());
            }

            try {
                System.out.println("Stack: " + s.list());
                System.out.println("Peek:  " + s.peek().toString());
                System.out.println("Stack: " + s.list());
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
            } catch (StackEmptyException e) {
                e.printStackTrace();
            }
        }

        // String:

        {
            Stack<String> s = new Stack<String>(10);
            try {
                s.push("Hallo");
                s.push("Welt");
                s.push("Test");
            } catch (StackFullException e) {
                e.printStackTrace();
            }
            System.out.println(s.list());
            try {
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
                System.out.println("Pop:   " + s.pop().toString());
                System.out.println("Stack: " + s.list());
            } catch (StackEmptyException e) {
                e.printStackTrace();
            }
        }
    }
}
