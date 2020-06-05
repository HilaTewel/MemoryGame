package com.example.cardtry;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import static maes.tech.intentanim.CustomIntent.customType;

import androidx.annotation.RequiresApi;


public class AnimalsLevelHard extends Level {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hard_board);
        customType(AnimalsLevelHard.this,"fadein-to-fadeout");
        levelTime = 90000;
        numOfLives = 40;
        levelName = "AnimalsHard";
        cardBack = R.drawable.animals_card_back;
        sp = getSharedPreferences("details", MODE_PRIVATE);
        coins = sp.getInt("coins", 1000);
        isPassed = sp.getBoolean(levelName, false);
        context = AnimalsLevelHard.this;
        thisLevel = AnimalsLevelHard.class;
        nextLevel = SeaLevelEasy.class;
        NUM_OF_CARDS = 30;
        cards = new Card[NUM_OF_CARDS];
        pictures = new int[NUM_OF_CARDS / 2];

        LinearLayout levelLayout = findViewById(R.id.hardLayout);
        levelLayout.setBackgroundResource(R.drawable.jungle_3);

        ImageView card1 = findViewById(R.id.hardCard1);
        ImageView card2 = findViewById(R.id.hardCard2);
        ImageView card3 = findViewById(R.id.hardCard3);
        ImageView card4 = findViewById(R.id.hardCard4);
        ImageView card5 = findViewById(R.id.hardCard5);
        ImageView card6 = findViewById(R.id.hardCard6);
        ImageView card7 = findViewById(R.id.hardCard7);
        ImageView card8 = findViewById(R.id.hardCard8);
        ImageView card9 = findViewById(R.id.hardCard9);
        ImageView card10 = findViewById(R.id.hardCard10);
        ImageView card11 = findViewById(R.id.hardCard11);
        ImageView card12 = findViewById(R.id.hardCard12);
        ImageView card13 = findViewById(R.id.hardCard13);
        ImageView card14 = findViewById(R.id.hardCard14);
        ImageView card15 = findViewById(R.id.hardCard15);
        ImageView card16 = findViewById(R.id.hardCard16);
        ImageView card17 = findViewById(R.id.hardCard17);
        ImageView card18 = findViewById(R.id.hardCard18);
        ImageView card19 = findViewById(R.id.hardCard19);
        ImageView card20 = findViewById(R.id.hardCard20);
        ImageView card21 = findViewById(R.id.hardCard21);
        ImageView card22 = findViewById(R.id.hardCard22);
        ImageView card23 = findViewById(R.id.hardCard23);
        ImageView card24 = findViewById(R.id.hardCard24);
        ImageView card25 = findViewById(R.id.hardCard25);
        ImageView card26 = findViewById(R.id.hardCard26);
        ImageView card27 = findViewById(R.id.hardCard27);
        ImageView card28 = findViewById(R.id.hardCard28);
        ImageView card29 = findViewById(R.id.hardCard29);
        ImageView card30 = findViewById(R.id.hardCard30);


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
        cards[16].setCardView(card17);
        cards[17].setCardView(card18);
        cards[18].setCardView(card19);
        cards[19].setCardView(card20);
        cards[20].setCardView(card21);
        cards[21].setCardView(card22);
        cards[22].setCardView(card23);
        cards[23].setCardView(card24);
        cards[24].setCardView(card25);
        cards[25].setCardView(card26);
        cards[26].setCardView(card27);
        cards[27].setCardView(card28);
        cards[28].setCardView(card29);
        cards[29].setCardView(card30);


        //pictures array
        pictures[0] =R.drawable.chick;
        pictures[1]=R.drawable.koala;
        pictures[2]=R.drawable.zebra;
        pictures[3]= R.drawable.bear;
        pictures[4]= R.drawable.cow;
        pictures[5]= R.drawable.leopard;
        pictures[6]= R.drawable.monkey;
        pictures[7]= R.drawable.girrafe;
        pictures[8]= R.drawable.rhino;
        pictures[9]= R.drawable.fox2;
        pictures[10]= R.drawable.sheep;
        pictures[11]= R.drawable.owl;
        pictures[12]= R.drawable.hedgehog;
        pictures[13]= R.drawable.tiger;
        pictures[14]= R.drawable.panda;

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
        card17.setTag(16);
        card18.setTag(17);
        card19.setTag(18);
        card20.setTag(19);
        card21.setTag(20);
        card22.setTag(21);
        card23.setTag(22);
        card24.setTag(23);
        card25.setTag(24);
        card26.setTag(25);
        card27.setTag(26);
        card28.setTag(27);
        card29.setTag(28);
        card30.setTag(29);

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
        card17.setOnClickListener(this);
        card18.setOnClickListener(this);
        card19.setOnClickListener(this);
        card20.setOnClickListener(this);
        card21.setOnClickListener(this);
        card22.setOnClickListener(this);
        card23.setOnClickListener(this);
        card24.setOnClickListener(this);
        card25.setOnClickListener(this);
        card26.setOnClickListener(this);
        card27.setOnClickListener(this);
        card28.setOnClickListener(this);
        card29.setOnClickListener(this);
        card30.setOnClickListener(this);

        for (Card card: cards){
            card.getCardView().setImageDrawable(getDrawable(cardBack));
        }

        CoinsTV = findViewById(R.id.hardNumOfCoinsTV);
        LivesTV = findViewById(R.id.hardNumOfLivesTV);
        TimerTV = findViewById(R.id.hardTimerTV);

        ScoreTV = findViewById(R.id.score);
        CoinImage = findViewById(R.id.hardCoin);

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
