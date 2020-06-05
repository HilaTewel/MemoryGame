package com.example.cardtry;

import android.widget.ImageView;

public class Card {
    private int imageId;
    private boolean isMatched;
    private ImageView cardView;

    public Card() {
        this.imageId = -1;
        this.isMatched = false;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public ImageView getCardView() {
        return cardView;
    }

    public void setCardView(ImageView cardView) {
        this.cardView = cardView;
    }
}
