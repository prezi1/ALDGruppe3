package A02_Queue;

public class Queue<T>
{
    private Node<T> first;
    
    private Node<T> last;

    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws QueueEmptyException 
     */
    public T dequeue() throws QueueEmptyException {
        // Exception wenn das erste Element null ist
        if(first == null){
            throw new QueueEmptyException();
        }




    	return null;
    }
    
    
    
    /**
     * Übergebenen Integer am Ende der Queue anhängen.
     * @param i Zahl
     */
    public void enqueue(T i) {

        Node<T> n = new Node(i);

        if(first == null){
            first = n;
            last = n;
        }else{

                first.setNext(n);
                last = n;

        }


    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
    	return 0;
    }
}
