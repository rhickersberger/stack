package rhickersberger;

import java.util.Arrays;

/**
 * Ein generischer Stack/Stapel, der entweder eine bestimmte Größe haben kann, oder
 * dynamisch wachsen und schrumpfen kann.
 * @param <T> Der Datentyp der Objekte, die in diesem Stack gespeichert werden sollen.
 * @author René Hickersberger
 * @version 2021-01-04
 */
public class Stack<T> {
    /**
     * Die Einträge des Stacks.
     */
        private Object[] eintraege;
    /**
     * Die maximale Anzahl an Array-Einträgen.
     * Wenn es -1 ist, dann wird das Array immer an die benötigte Anzahl
     * an Einträgen angepasst (dynamisch vergrößert und auch verkleinert).
     */
    private int maxAnzahlEintraege = -1;
    /**
     * Die derzeitige Position im Stack (als Arrayindex).
     * Wird nur verwendet, wenn das Array nicht dynamisch wächst.
     */
    private int position = -1;

    /**
     * Erzeugt einen dynamisch wachsenden, leeren Stapel.
     */
    public Stack() {
        eintraege = new Object[0];
        maxAnzahlEintraege = -1;
        position = -1;
    }

    /**
     * Erzeugt einen Stapel mit der übergebenen maximalen Anzahl an Einträgen.
     * @param maxAnzahlEintraege Die maximale Anzahl an Einträgen, muss größer als 0 sein.
     * @throws IllegalArgumentException wenn die Anzahl nicht größer als 0 ist.
     */
    public Stack(int maxAnzahlEintraege) throws IllegalArgumentException {
        if (maxAnzahlEintraege <= 0) {
            throw new IllegalArgumentException("Die maximale Anzahl an Stapeleinträgen muss größer als 0 sein.");
        }
        this.maxAnzahlEintraege = maxAnzahlEintraege;
        eintraege = new Object[maxAnzahlEintraege];
        position = 0;
    }

    /**
     * Legt das übergebene Objekt oben auf den Stapel.
     * @param obj Das auf den Stapel zu legende Objekt.
     * @throws StackFullException wenn der Stapel bereits voll ist.
     */
    public void push(T obj) throws StackFullException {
        if (maxAnzahlEintraege == -1) {
            // Wenn der Stack dynamisch wachsen soll, wird das Array einfach erweitert.
            eintraege = Arrays.copyOf(eintraege, eintraege.length + 1);
            eintraege[eintraege.length - 1] = obj;
        }
        else {
            // Sonst muss eben geprüft werden, ob noch genug Platz im Array ist.
            if (position >= eintraege.length) {
                throw new StackFullException();
            }
            // Der Eintrag wird ins Array geschrieben und anschließend die Position inkrementiert.
            eintraege[position++] = obj;
        }
    }

    /**
     * Entfernt das oberste Objekt am Stapel und gibt es zurück.
     * @return Das (vor dem Löschen) oberste Objekt am Stapel.
     * @throws StackEmptyException wenn der Stapel leer ist.
     */
    public T pop() throws StackEmptyException {
        // Wenn es keine Einträge mehr gibt, ist der Stack leer und die Exception wird geworfen.
        if (eintraege.length == 0 || position == 0) {
            throw new StackEmptyException();
        }

        T obj;
        if (maxAnzahlEintraege == -1) {
            // Wenn der Stack dynamisch wächst, dann wird es einfach verkleinert.
            obj = (T) eintraege[eintraege.length - 1];
            // Oberstes (letztes) Objekt am Stapel löschen.
            eintraege = Arrays.copyOf(eintraege, eintraege.length - 1);
        }
        else {
            // Sonst muss wieder die Position dekrementiert werden.
            obj = (T) eintraege[position-1];
            eintraege[position-1] = null;
            position--;
        }
        return obj;
    }

    /**
     * Gibt das oberste Objekt am Stapel zurück.
     * @return Das oberste Objekt am Stapel.
     * @throws StackEmptyException wenn der Stapel leer ist.
     */
    public T peek() throws StackEmptyException {
        // Wenn es keine Einträge mehr gibt, ist der Stack leer und die Exception wird geworfen.
        if (eintraege.length == 0 || position == 0) {
            throw new StackEmptyException();
        }

        if (maxAnzahlEintraege == -1) {
            // Wenn der Stack dynamisch wächst, dann wird wieder die Länge als Position verwendet.
            return (T) eintraege[eintraege.length - 1];
        }
        else {
            // Sonst wird auf das Attribut position zurückgegriffen.
            return (T) eintraege[position - 1];
        }
    }

    /**
     * Gibt einen String mit allen Einträgen, getrennt mit Kommas, zurück.
     * @return Ein String mit allen Einträgen, getrennt mit Kommas.
     */
    public String list() {
        int maxIdx = (maxAnzahlEintraege == -1) ? eintraege.length-1 : position-1;

        // Einträge mit Kommas zusammenhängen.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= maxIdx; i++) {
            sb.append(eintraege[i].toString());
            if (i < maxIdx) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
