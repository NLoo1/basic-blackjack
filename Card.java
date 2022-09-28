public class Card {
    private String suit;
    private String value;
    private Boolean isFaceUp;

    public String toString(){
        if (isFaceUp) return "[" + value + suit + "]";
        else return "[XX]";
    }

    public Card(String s, String v, Boolean i) {
        suit = s;
        value = v;
        isFaceUp = i;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(Boolean faceUp) {
        isFaceUp = faceUp;
    }


}
