/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */
public class RingBuffer 
{
    double[] values;
    int head;
    int tail;
    int size;

    public RingBuffer(int capacity)
    {
        values = new double[capacity];
        head = -1;
        tail = 0;
        size = 0;
    }

    public int size()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public boolean isFull()
    {
        return size == values.length;
    }
    
    public void add(double value) throws QueueOverflowException
    {
        if(isFull()) {
            throw new QueueOverflowException("Tried to add to a full RingBuffer");
        }

        values[tail] = value;
        
        if(head == -1) {
            head = tail;
        }

        tail++;
        if(tail == values.length) {
            tail = 0;
        }
        size++;
    }
    
    public double peek() throws QueueUnderflowException
    {
        if(isEmpty()) {
            throw new QueueUnderflowException("Tried to remove to a empty RingBuffer");
        }
        return values[head];
    }
    
    public double remove() throws QueueUnderflowException
    {
        if(isEmpty()) {
            throw new QueueUnderflowException("Tried to remove to a empty RingBuffer");
        }

        double value = values[head];
        
        head++;
        if(head == values.length) {
            head = 0;
        }

        size--;
        
        if(isEmpty()) {
            head = -1;
        }

        return value;
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) throws Exception
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) {
            buffer.add(i);
        }
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
