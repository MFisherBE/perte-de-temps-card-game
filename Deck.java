import java.util.Random;

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
