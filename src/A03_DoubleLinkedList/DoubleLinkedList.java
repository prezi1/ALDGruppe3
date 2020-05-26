package A03_DoubleLinkedList;

public class DoubleLinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private Node<T> current;

    /**
     * Einfügen einer neuen <T>
     *
     * @param a <T>
     */
    public void add(T a) {

        Node<T> n = new Node<>(a);

        // Erstes Element einfügen
        if (first == null) {
            first = n;
            last = n;
            // Wenn nur ein Element Vorhanden ist dann: (Letztes gleich erstes Element)
        } else if (first == last) {
            last = n;
            first.setNext(last);
            last.setPrevious(first);
            // Element anhängen wenn First und Last vorhanden:
        } else {
            last.setNext(n);
            n.setPrevious(last);
            last = n;

        }
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {
        current = first;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
        current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     *
     * @return Node|null
     */
    public Node<T> getFirst() {

        return first;
    }

    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     *
     * @return Node|null
     */
    public Node<T> getLast() {

        return last;
    }

    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     *
     * @return <T>|null
     */
    public T next() {
        if (current == null) {
            return null;
        }
        Node<T> temp = current; //Aktuelle Node zwischen speichern
        moveNext(); //Funktionsaufruf welcher Zeiger von Current auf das nächste Element setzt
        return temp.getData(); //Zwischengespeicherte Node zurück geben
    }

    /**
     * analog zur Funktion next()
     *
     * @return <T>|null
     */
    public T previous() {
        if (current == null) {
            return null;
        }
        Node<T> temp = current; //Aktuelle Node zwischen speichern
        movePrevious(); //Funktionsaufruf welcher Zeiger von Current auf das vorherige Element setzt
        return temp.getData(); //Zwischengespeicherte Node zurück geben
    }

    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
        if (current != null) {
            current = current.getNext();
        }
    }

    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        if (current != null) {
            current = current.getPrevious();
        }
    }

    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     *
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {
        if (current == null) {
            throw new CurrentNotSetException();
        }
        return current.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     *
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {
        Node<T> temp = first; //Erstes Element ist immer First
        int i = 1;
        while (temp.getNext() != null && i < pos) { //Wenn kein Nachfolger vorhanden ist oder die aktuelle Position erreicht ist wird Schleife beendet
            temp = temp.getNext(); //Auf nächstes Element mittels hochzählen von Position zugreifen
            i++;
        }
        if (i != pos) { // Position nicht gefunden
            return null;
        }
        return temp.getData();
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     *
     * @param pos
     */
    public void remove(int pos) {
        if (first == null) {
            return;
        }
        Node<T> temp = first;
        int i = 1;
        while (temp.getNext() != null && i < pos) { //Wenn kein Nachfolger vorhanden ist oder die aktuelle Position erreicht ist wird Schleife beendet
            temp = temp.getNext(); //Auf nächstes Element mittels hochzählen von Position zugreifen
            i++;
        }
        if (i != pos) { // Wenn Position nicht gefunden wird - ENDE
            return;
        }

        if (temp == first) { // Entfernen vom ersten Element
            first = temp.getNext();
            if (first != null) { //Wenn noch ein Element vorhanden ist, dann wird der aktuell vorherige (previous) Knoten Null! Neuer First Pointer!
                first.setPrevious(null);
            } else {
                last = null; // Wenn das letzte Elemente in der Liste gelöscht wird, dann wird Last gleich Null gesetzt!
            }
        } else if (temp == last) { // Entfernen vom letzten Element
            last = temp.getPrevious();
            last.setNext(null);
        } else {
            //Löschen eines Zwischenelementes
            temp.getPrevious().setNext(temp.getNext());   // Nachfolger-Zeiger von Vorgänger zeigt auf den Nachfolger vom zu entferndenden Element (temp) !
            temp.getNext().setPrevious(temp.getPrevious()); // Vorgänger-Zeiger von Nachfolger zeigt auf den Nachfolger vom zu entferndenden Element (temp) !
        }
        if (temp == current) {
            current = null;
        }

    }

    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element
     *
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
        Node<T> temp = current;

        if (current == null) {
            throw new CurrentNotSetException();
        }

        if (current == first) { // Entfernen vom ersten Element
            first = first.getNext();
            if (first != null) { //Wenn noch ein Element vorhanden ist, dann wird der aktuell vorherige (previous) Knoten Null! Neuer First Pointer!
                first.setPrevious(null);
            } else {
                last = null; // Wenn das letzte Elemente in der Liste gelöscht wird, dann wird Last gleich Null gesetzt!
            }
            current = first; //Current wird auf neues first Element gesetzt
        } else if (current == last) { // Entfernen vom letzten Element
            last = last.getPrevious();
            last.setNext(null);
            current = last; //Current wird auf neues last Element gesetzt
        } else {
            current = current.getNext(); // Current soll auf nächsten Knoten zeigen
            //Löschen eines Zwischenelementes
            temp.getPrevious().setNext(temp.getNext()); // Nachfolger-Zeiger von Vorgänger zeigt auf den Nachfolger vom zu entferndenden Element (temp) !
            temp.getNext().setPrevious(temp.getPrevious()); // Vorgänger-Zeiger von Nachfolger zeigt auf den Nachfolger vom zu entferndenden Element (temp) !
        }

    }

    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     *
     * @throws CurrentNotSetException
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {
        Node<T> temp = new Node<>(a);
        if (current == null) {
            throw new CurrentNotSetException();
        }

        temp.setPrevious(current); //Current wird als Vorgänger von neuem Knoten gesetzt
        temp.setNext(current.getNext()); //Nachfolger von Current wird als Nachfolger von neuem Knoten gesetzt
        if (current.getNext() != null) { // Wenn ein Nachfolger Knoten von Current vorhanden ist, dann soll dieser auf den neuen Knoten zeigen! d.h Current != Last
            current.getNext().setPrevious(temp);
        }
        current.setNext(temp); //Nachfolger von Current ist neuer Knoten
        current = temp; //Current zeigt auf neuen Knoten, ist aber auch neuer Knoten
    }
}
