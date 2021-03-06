package com.example.cardtry;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import static maes.tech.intentanim.CustomIntent.customType;

import androidx.annotation.RequiresApi;

public class SeaLevelEasy extends Level {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_board);
        customType(SeaLevelEasy.this,"fadein-to-fadeout");
        levelTime = 60000;
        numOfLives = 10;
        levelName = "SeaEasy";
        cardBack = R.drawable.sea_card_back;
        sp = getSharedPreferences("details", MODE_PRIVATE);
        coins = sp.getInt("coins", 1000);
        isPassed = sp.getBoolean(levelName, false);
        context = SeaLevelEasy.this;
        thisLevel = SeaLevelEasy.class;
        nextLevel = SeaLevelMedium.class;
        NUM_OF_CARDS = 12;
        cards = new Card[NUM_OF_CARDS];
        pictures = new int[NUM_OF_CARDS / 2];

        LinearLayout levelLayout = findViewById(R.id.easyLayout);
        levelLayout.setBackgroundResource(R.drawable.underwater);

        ImageView card1 = findViewById(R.id.easyCard1);
        ImageView card2 = findViewById(R.id.easyCard2);
        ImageView card3 = findViewById(R.id.easyCard3);
        ImageView card4 = findViewById(R.id.easyCard4);
        ImageView card5 = findViewById(R.id.easyCard5);
        ImageView card6 = findViewById(R.id.easyCard6);
        ImageView card7 = findViewById(R.id.easyCard7);
        ImageView card8 = findViewById(R.id.easyCard8);
        ImageView card9 = findViewById(R.id.easyCard9);
        ImageView card10 = findViewById(R.id.easyCard10);
        ImageView card11 = findViewById(R.id.easyCard11);
        ImageView card12 = findViewById(R.id.easyCard12);

        //initialize cards array
        for( int i = 0; i < NUM_OF_CARDS; i++){
            cards[i] = new Card();
        }
        cards[0].setCardView(card1);
        cards[1].setCardView(card2);
        cards[2].setCardView(card3);
        cards[3].setCardView(card4);
        cards[4].setCardView(card5);
        cards[5].setCardView(card6);
        cards[6].setCardView(card7);
        cards[7].setCardView(card8);
        cards[8].setCardView(card9);
        cards[9].setCardView(card10);
        cards[10].setCardView(card11);
        cards[11].setCardView(card12);


        //pictures array
        pictures[0] =R.drawable.bottle;
        pictures[1]=R.drawable.fishyellow;
        pictures[2]=R.drawable.coral;
        pictures[3]= R.drawable.lifewheel;
        pictures[4]= R.drawable.seastar;
        pictures[5]= R.drawable.seahorse;

        //randomly choose 2 cards for each picture
        setCards();

        card1.setTag(0);
        card2.setTag(1);
        card3.setTag(2);
        card4.setTag(3);
        card5.setTag(4);
        card6.setTag(5);
        card7.setTag(6);
        card8.setTag(7);
        card9.setTag(8);
        card10.setTag(9);
        card11.setTag(10);
        card12.setTag(11);


        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card8.setOnClickListener(this);
        card9.setOnClickListener(this);
        card10.setOnClickListener(this);
        card11.setOnClickListener(this);
        card12.setOnClickListener(this);

        for (Card card: cards){
            card.getCardView().setImageDrawable(getDrawable(cardBack));
        }

        CoinsTV = findViewById(R.id.easyNumOfCoinsTV);
        LivesTV = findViewById(R.id.easyNumOfLivesTV);
        TimerTV = findViewById(R.id.easyTimerTV);

        ScoreTV = findViewById(R.id.score);
        CoinImage = findViewById(R.id.easyCoin);

        CoinsTV.setText(coins + "");
        LivesTV.setText(numOfLives + "");

        myCountDown = new MyCountDown(levelTime, TimerTV, this);
        myCountDown.startPause();

        isSoundOn = sp.getBoolean("soundOn", true);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder().setMaxStreams(2).setAudioAttributes(audioAttributes).build();

        cardSound = soundPool.load(this, R.raw.card_flip, 1);
        cheerSound = soundPool.load(this, R.raw.cheer, 1);


    }
}
