package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{
    private Node<T> first;
    private Node<T> last;
    private Node<T> current;

    /**
     * Einfügen einer neuen <T>
     * @param a <T>
     */
    public void add(T a) {

        Node<T> n = new Node<>(a);

       // Erstes Element
        if(first == null){
            first = n;
            last = n;
        // Wenn nur ein Element Vorhanden ist dann:
        }else if(first == last){
            last = n;
            first.setNext(last);
            last.setPrevious(first);
            // Element Anhängen wenn first und Last vorhanden:
        }else {
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
     * @return Node|null
     */
    public Node<T> getFirst() {
    	
    	return first;
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {
    	
    	return last;
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {
       Node<T> temp ;
        if (current == null)
    	    return null;
        temp = current;
        moveNext();
        return temp.getData();
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {

        Node<T> temp ;
        if (current == null)
            return null;
        temp = current;
        movePrevious();
        return temp.getData();
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
            if(current == null){
                current = first;
            }
            else if(current != last)
                current = current.getNext();
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        if(current != null && current != first){
            current = current.getPrevious();
        }

    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        if (current == null){
            throw new CurrentNotSetException();
        }


    	return current.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {
        Node<T> temp = first;
        int i= 0;
        while (temp.getNext() != null && i < pos){
            temp = temp.getNext();
            i++;
        }



        return temp.getData();
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) {

        Node<T> temp = first;
        int i= 0;
        while (temp.getNext() != null && i < pos){
            temp = temp.getNext();

            i++;
        }
        temp.getPrevious().setNext(temp.getNext());
        temp.getNext().setPrevious(temp.getPrevious());

        if (temp == current){
            current = null;

        }
        temp = null;


    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
       Node<T> temp = current;
        if(current == null){
            throw new CurrentNotSetException();
        }
        if (current != first) {
            current.getPrevious().setNext(current.getNext());
            current = temp.getPrevious();
        }if(current != last){
            current.getNext().setPrevious(current.getPrevious());
            current = temp.getNext();
        }


    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {
        Node<T> temp = new Node<>(a);
        if (current == null){
                        throw new CurrentNotSetException();
                    }


        temp.setPrevious(current);
        temp.setNext(current.getNext());
        current.getNext().setPrevious(temp);
        current.setNext(temp);


        current = temp;


    }
}
