/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    RingBuffer buffer;
    int sampleRate;
    int ticCount;
    
    public GuitarString(double frequency) 
    {
        sampleRate = (int) (44100 / frequency);
        buffer = new RingBuffer(sampleRate);
        while(!buffer.isFull()) {
            try {buffer.add(0.0);}
            catch(QueueOverflowException e) {System.out.println("The intitilization failed somehow idk bro");}
        }
        ticCount = 0;
    }

    public GuitarString(double[] array) 
    {
    }

    public void pluck() 
    {
        for(int i = 0; i < sampleRate; i++) {
            try {buffer.remove();}
            catch (QueueUnderflowException e) {System.out.println("The pluck failed during remove somehow idk bro");}
            double randDouble = Math.random() - 0.5;
            try {buffer.add(randDouble);}
            catch(QueueOverflowException e) {System.out.println("The pluck failed during adding somehow idk bro");}
        }
    }

    // advance the simulation one time step
    public void tic() throws QueueOverflowException, QueueUnderflowException
    {
        double d1 = buffer.remove();
        double d2 = buffer.peek();

        double newD = ((d1 + d2) / 2) * 0.994;
        
        buffer.add(newD);

        ticCount++;
    }

    // return the current sample
    public double sample() 
    {
        try{return buffer.peek();}
        catch (QueueUnderflowException e) {return 0.0;}
    }

    // return number of times tic was called
    public int time() 
    {
        return ticCount;
    }

    public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
