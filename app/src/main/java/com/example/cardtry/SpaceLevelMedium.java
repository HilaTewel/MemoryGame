package com.example.cardtry;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import static maes.tech.intentanim.CustomIntent.customType;

import androidx.annotation.RequiresApi;

public class SpaceLevelMedium extends Level {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medium_board);
        customType(SpaceLevelMedium.this,"fadein-to-fadeout");
        levelTime = 60000;
        numOfLives = 15;
        levelName = "SpaceMedium";
        cardBack = R.drawable.space_card_back_purple;
        sp = getSharedPreferences("details", MODE_PRIVATE);
        coins = sp.getInt("coins", 1000);
        isPassed = sp.getBoolean(levelName, false);
        context = SpaceLevelMedium.this;
        thisLevel = SpaceLevelMedium.class;
        nextLevel = SpaceLevelHard.class;
        NUM_OF_CARDS = 16;
        cards = new Card[NUM_OF_CARDS];
        pictures = new int[NUM_OF_CARDS / 2];

        LinearLayout levelLayout = findViewById(R.id.mediumLayout);
        levelLayout.setBackgroundResource(R.drawable.spacebackground2);

        ImageView card1 = findViewById(R.id.mediumCard1);
        ImageView card2 = findViewById(R.id.mediumCard2);
        ImageView card3 = findViewById(R.id.mediumCard3);
        ImageView card4 = findViewById(R.id.mediumCard4);
        ImageView card5 = findViewById(R.id.mediumCard5);
        ImageView card6 = findViewById(R.id.mediumCard6);
        ImageView card7 = findViewById(R.id.mediumCard7);
        ImageView card8 = findViewById(R.id.mediumCard8);
        ImageView card9 = findViewById(R.id.mediumCard9);
        ImageView card10 = findViewById(R.id.mediumCard10);
        ImageView card11 = findViewById(R.id.mediumCard11);
        ImageView card12 = findViewById(R.id.mediumCard12);
        ImageView card13 = findViewById(R.id.mediumCard13);
        ImageView card14 = findViewById(R.id.mediumCard14);
        ImageView card15 = findViewById(R.id.mediumCard15);
        ImageView card16 = findViewById(R.id.mediumCard16);

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
        cards[12].setCardView(card13);
        cards[13].setCardView(card14);
        cards[14].setCardView(card15);
        cards[15].setCardView(card16);

        //pictures array
        pictures[0] =R.drawable.star2;
        pictures[1]=R.drawable.spaceship;
        pictures[2]=R.drawable.astronaut;
        pictures[3]= R.drawable.astroid;
        pictures[4]= R.drawable.fallingstar;
        pictures[5]= R.drawable.solarsystem;
        pictures[6]= R.drawable.spaceship2;
        pictures[7]= R.drawable.redstar;

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
        card13.setTag(12);
        card14.setTag(13);
        card15.setTag(14);
        card16.setTag(15);

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
        card13.setOnClickListener(this);
        card14.setOnClickListener(this);
        card15.setOnClickListener(this);
        card16.setOnClickListener(this);

        for (Card card: cards){
            card.getCardView().setImageDrawable(getDrawable(cardBack));
        }

        CoinsTV = findViewById(R.id.numOfCoinsTV);
        LivesTV = findViewById(R.id.numOfLivesTV);
        TimerTV = findViewById(R.id.timerTV);

        CoinsTV.setText(coins + "");
        LivesTV.setText(numOfLives + "");

        CoinImage = findViewById(R.id.mediumCoin);
        ScoreTV = findViewById(R.id.score);

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