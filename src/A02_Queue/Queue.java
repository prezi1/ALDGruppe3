package A02_Queue;

public class Queue<T>
{
    private Node<T> first;
    private int counter;
    
    private Node<T> last;

    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zur�ckliefern.
     * Existiert kein Element, wird eine Exception ausgel�st.
     * @throws QueueEmptyException 
     */
    public T dequeue() throws QueueEmptyException {
        // Exception wenn das erste Element null ist


        if(first == null){
            throw new QueueEmptyException();
        }

        Node<T> temp;
        temp = first;
        first = first.getNext();
        counter--;

    	return (T) temp.getData();
    }
    
    
    
    /**
     * �bergebenen Integer am Ende der Queue anh�ngen.
     * @param i Zahl
     */
    public void enqueue(T i) {

        Node<T> n = new Node(i);
        counter++;
        if(first == null){
            first = n;
            last = n;

        }else{

                last.setNext(n);
                last = n;

        }


    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
    	return this.counter;
    }
}
