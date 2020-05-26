package A01_Stack;


public class Stack<T>
{
	 private Node<T> first;
	 private int counter;
    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws StackEmptyException 
     */
    public T pop() throws StackEmptyException {

        // Falls keine First Node vorhanden ist == Stack ist Leer
        if(first == null){
            throw new StackEmptyException();
        }
       // Oberste Element zwischen speichern
        Node<T> result = first;
        // Darunterliegende  Element auf First setzen
        first = first.getNext();
        counter--;
        return  result.getData();
    }

    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i) {
        counter++;
        // Neues Node Objekt erzeugen

        Node<T> n = new Node(i);
        // Falls Stack leer dann ist Node == Erstes Element im Stack
        if(first ==  null){
            first = n;
        }
        // Wenn Element vorhanden dann, aktuelles Element wird der Zeiger auf darunterliegendesw Element gesetzt.
        else {
            n.setNext(first);
           // Aktuelles Element wird zum obersten Element !!! Happy Coding !!!
            first = n;
        }


    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
    	return counter;
    }
}
