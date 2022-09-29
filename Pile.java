import java.util.Random;

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
