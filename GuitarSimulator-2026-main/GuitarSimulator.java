/**
 * This GuitarSimulator object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarSimulator
{
    public static void main(String[] args) 
    {
        // Create two guitar strings, for concert A and C
        GuitarString[] strings = new GuitarString[37];
        for(int i = 0; i < 37; i++) {
            strings[i] = new GuitarString(((770.0 * i) / 37) + 110.0);
        }
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; 

        // the main input loop
        while (true) 
        {
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) 
            {
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if(keyboard.indexOf(key) != -1) {
                    int index = keyboard.indexOf(key);
                    strings[index].pluck();
                }
            }

            // compute the superposition of the samples
            double sample = 0;
            for(GuitarString g : strings) {
                sample += g.sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for(GuitarString g : strings) {
                g.tic();
            }
        }
    }
}
