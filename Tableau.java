import java.util.Random;

// Declare game class Tableau
public class Tableau
{
    // Create new array of piles
    private Pile[] pileArray = new Pile[13];

    // Create constructor for Tableau
    public Tableau()
    {
        // For loop to create 13 elements of piles, or array of 13 piles
        for(int i=0; i < 13; i++)
        {
            pileArray[i] = new Pile();
        }

        // Declare new deck of cards
        Deck newDeck = new Deck();

        // Shuffle deck
        newDeck.shuffle();

        // Declare new Card type for cards to deal
        Card dealCard;

        // For loop to add cards to each of the 13 piles
        for(int pileNum = 0; pileNum < 13; pileNum++)
        {
            // Add 4 cards to each pile with this loop
            for(int i = 0; i < 4; i++)
            {
                dealCard = newDeck.deal();
                pileArray[pileNum].add(dealCard);
            }
        }
    }

    // Declare method to play the actual game
    public void play()
    {
        boolean Win;    // Declare flag to determine game status
        int c2Suit;     // Declare int to hold suit of card 2
        int c2Rank;     // Declare int to hold rank of card 2
        int c1Suit;     // Declare int to hold suit of card 1
        int c1Rank;     // Declare int to hold rank of card 1
        int p = 0;      // Declare int to start with the first pile (pile #0)

        // Draw card 1 from pile #0
        Card c1 = pileArray[p].draw();

        // Print statement to check what card you drew from what pile
        System.out.println("Got " + c1.toString() + "from pile #" + p);

        // Infinite loop until win or lose
        while(true)
        {
            // If pile #p is empty
           if(pileArray[p].isEmpty())
           {
               // Scan the other piles for empty
               for(int i = 0; i < 13; i++)
               {
                   // If this other pile is NOT empty
                   if(pileArray[i].isEmpty() == false)
                   {
                       // you do not win
                       Win = false;
                       break;   // End game
                   }

                   // If the pile is empty, set Win status flag to true
                   else if(pileArray[i].isEmpty())
                   {
                       // Set Win flag to true
                       Win = true;
                   }
               }

               // After all piles scanned, if Win flag = true, you win
               if(Win = true)
               {
                   System.out.println("All Piles are Empty! You win PerteDeTemps.");
                   break;
               }

               // If Win status flag = false after all piles scanned, you lose.
               else
               {
                   System.out.println("Pile "+p+" is empty. You lose.");
               }
           }

           // Draw a second card from pile 2
           Card c2 = pileArray[p].draw();

           // Print out the card and pile you drew from
           System.out.println("Drew" + c2.toString() + "from pile" + p);

           // Assign respective suits and ranks to the c1 and c2 suit and rank int variables
           c1Suit = c1.getSuit();
           c2Suit = c2.getSuit();
           c1Rank = c1.getRank();
           c2Rank = c2.getRank();

           if(c2Suit == c1Suit)
           {
               p = c1Rank;
           }
           else if(c2Suit != c1Suit)
           {
               p = c2Rank;
           }

           c1 = c2;
        }
    }
}
