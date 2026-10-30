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

    public RingBuffer(int capacity)
    {
        values = new double[capacity];
        head = 0;
        tail = 0;
    }

    public int size()
    {
        return (head - tail) % values.length;
    }
    
    public boolean isEmpty()
    {
        return head == tail;
    }
    
    public boolean isFull()
    {
        return head == tail - 1;
    }
    
    public void add(double value) throws QueueOverflowException
    {
        if(isFull()) {
            throw new QueueOverflowException("Tried to add to a full array");
        }

        values[tail] = value;
        
        tail++;
        if(tail == values.length) {
            tail = 0;
        }
    }
    
    public double peek() throws QueueUnderflowException
    {
        return values[head];
    }
    
    public double remove()
    {
        return 0.0;
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
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
