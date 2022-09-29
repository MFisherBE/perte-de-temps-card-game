//  CARD. A playing card from a standard deck but not a joker.

// Michael Fisher


import java.util.Random;

final class Card
{

//  RANK NAME. Printable names of card ranks.

    private static final String [] rankName =
            {
                    "ace",       //   0
                    "two",       //   1
                    "three",     //   2
                    "four",      //   3
                    "five",      //   4
                    "six",       //   5
                    "seven",     //   6
                    "eight",     //   7
                    "nine",      //   8
                    "ten",       //   9
                    "jack",      //  10
                    "queen",     //  11
                    "king"       //  12
            };

//  SUIT NAME. Printable names of card suits.

    private static final String [] suitName =
            {
                    "clubs",     //  0
                    "diamonds",  //  1
                    "hearts",    //  2
                    "spades"     //  3
            };

    private int rank;  //  Rank of this CARD, between 0 and 12.
    private int suit;  //  Suit of this CARD, between 0 and 3.

//  CARD. Constructor. Make a new CARD, with a given RANK and SUIT.

    public Card(int rank, int suit)
    {
        if (0 <= rank && rank <= 12 && 0 <= suit && suit <= 3)
        {
            this.rank = rank;
            this.suit = suit;
        }
        else
        {
            throw new IllegalArgumentException("Illegal rank or suit.");
        }
    }

//  GET RANK. Return the RANK of this CARD.

    public int getRank()
    {
        return rank;
    }

//  GET SUIT. Return the SUIT of this CARD.

    public int getSuit()
    {
        return suit;
    }

//  TO STRING. Return a STRING that describes this CARD. For printing only!

    public String toString()
    {
        return rankName[rank] + " (" + rank + ") of " + suitName[suit];
    }
}


// Declare Pile class
public class Pile
{
    // Declare new instance of Layer class to use for top card
    private Layer topCard;

    // Declare private class Layer
    private class Layer
    {
        private Card card;
        private Layer next;
        private Layer(Card card, Layer next)
        {
            this.card = card;
            this.next = next;
        }
    }

    // Declare method to initialize the pile
    public Pile()
    {
        topCard = null;
    }

    // Declare method to add card to deck
    public void add(Card card)
    {
        // Create new instance of Layer
        Layer newLayer = new Layer(card, null);

        // If top card is empty, create new layer
        if(topCard == null)
        {
            topCard = newLayer;
        }

        // Else next layer gets current top card
        else
        {
            newLayer.next = topCard;
        }
    }

    // Declare method to draw cards from each pile in the game
    public Card draw()
    {
        // If there are no cards in a pile, throw an error
        if (topCard == null)
        {
            throw new IllegalStateException("Can not draw. The pile is empty.");
        }

        // If there are cards in pile...
        else
        {
            Card card = topCard.card; // card to return is the top card being drawn
            topCard = topCard.next;   // Set new top card to the next card down
            return card;              // return card being drawn
        }
    }

    // Declare method for checking if pile is empty
    public boolean isEmpty()
    {
        return topCard == null;
    }
}

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

public class PerteDeTemps
{
    public static void main(String params[])
    {
        Tableau tableau = new Tableau();
        tableau.play();
    }
}

public class Deck
{


    public static Random r = new Random();
    private static Card[] deckArray = new Card[52];
    int topCard = -1;
    boolean alreadyDealt;

    public Deck()
    {


        //Suit #1 - Clubs
        for(int i = 0; i < 13; i++)
        {
            deckArray[i] = new Card(i,0);
        }

        //Suit #2 - Diamonds
        for(int i = 13; i < 26; i++)
        {
            deckArray[i] = new Card((i-13),1);
        }

        //Suit #3 - Hearts
        for(int i = 27; i < 40; i++)
        {
            deckArray[i] = new Card((i-27),2);
        }

        //Suit #1 - Spades
        for(int i = 40; i < 52; i++)
        {
            deckArray[i] = new Card((i-40),0);
        }
    }

    public Card deal()
    {
        topCard++;
        if(topCard > 51)
        {
            alreadyDealt = true;
            throw new IllegalStateException("All cards have been dealt.");
        }
        return deckArray[topCard];
    }

    public void shuffle()
    {
        if(alreadyDealt)
        {
            throw new IllegalStateException("Can not shuffle again after already dealing.");
        }
        else
        {
            int j;
            Card holder;
            for(int i = (52-1); i >= 1; i--)
            {
                j = Math.abs(r.nextInt()) % 52;
                holder = deckArray[j];
                deckArray[j] = deckArray[i];
                deckArray[i] = holder;
            }
        }
    }
}
 Testing shown below... didnt work all the way...
Pile #: 0
        ace (0) of clubs
        Pile #: 1
        five (4) of diamonds
        Pile #: 2
        ten (9) of hearts
        Pile #: 3
        seven (6) of clubs
        Pile #: 4
        eight (7) of clubs
        Pile #: 5
        three (2) of hearts
        Pile #: 6
        five (4) of clubs
        Pile #: 7
        six (5) of clubs
        Pile #: 8
        seven (6) of hearts
        Pile #: 9
        nine (8) of diamonds
        Pile #: 10
        eight (7) of hearts
        Pile #: 11
        four (3) of hearts
        Pile #: 12
        eight (7) of clubs
        Drew ace (0) of clubsfrom pile0
        All Piles are Empty! You win PerteDeTemps.

        Process finished with exit code 0


