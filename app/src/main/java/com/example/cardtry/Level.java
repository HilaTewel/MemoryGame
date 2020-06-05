package com.example.cardtry;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import android.animation.ObjectAnimator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;


import java.util.Objects;
import java.util.Random;

public abstract class Level extends Activity implements View.OnClickListener, Game {

    protected int NUM_OF_CARDS;
    protected int levelTime;
    protected int numOfLives;
    protected MyCountDown myCountDown;
    protected SharedPreferences sp;
    protected String levelName;
    protected int cardBack;

    protected boolean firstInPair;
    protected int firstCard;//check if can be not global
    protected int combo = 0;
    protected int coins; //get coins from shared pref
    protected int pairsMatched = 0;
    protected boolean isWin;
    protected boolean isPassed;

    protected TextView CoinsTV;
    protected TextView LivesTV;
    protected TextView TimerTV;
    protected TextView ScoreTV;
    protected ImageView CoinImage;
    protected Context context;
    protected Class thisLevel;
    protected Class nextLevel;

    protected Card[] cards;
    protected int[] pictures;

    protected SoundPool soundPool;
    protected int cardSound, cheerSound;
    protected boolean isSoundOn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void setCards() {
        for (int picture : pictures) {
            Random rand = new Random();
            int index = rand.nextInt(NUM_OF_CARDS);

            while (cards[index].getImageId() != -1) {
                index = rand.nextInt(NUM_OF_CARDS);
            }
            cards[index].setImageId(picture);

            while (cards[index].getImageId() != -1) {
                index = rand.nextInt(NUM_OF_CARDS);
            }
            cards[index].setImageId(picture);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {


        final int tag = (int) v.getTag();
        final ImageView cardChosen = (ImageView) v;
        cardChosen.setClickable(false);
        animatedCardFlip(cardChosen, cards[tag].getImageId());

        soundPool.play(cardSound, 1,1,0,0,1);

        firstInPair = !firstInPair;
        doTurn(tag);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void doTurn(int index) {

        if (firstInPair) {
            firstCard = index;
        } else {
            Compare(firstCard, index);
            coinsCount();
            CoinsTV.setText(coins + "");
        }
    }

    protected void Compare(final int c1, final int c2) {

        if (cards[c1].getImageId() == cards[c2].getImageId()) {

            cards[c1].getCardView().setClickable(false);
            cards[c2].getCardView().setClickable(false);
            pairsMatched++;
            combo++;
            animatedCardDisappear(cards[c1].getCardView());
            animatedCardDisappear(cards[c2].getCardView());
            Animation scoreAnim = AnimationUtils.loadAnimation(context, R.anim.text_view_score_anim);
            Animation coinAnim = AnimationUtils.loadAnimation(context, R.anim.coin_anim);
            ScoreTV.setText("+"+10 * combo);
            ScoreTV.setVisibility(View.VISIBLE);
            scoreAnim.setInterpolator(new DecelerateInterpolator());
            ScoreTV.startAnimation(scoreAnim);

            CoinImage.startAnimation(coinAnim);
            scoreAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ScoreTV.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            isWinOrLose();
        } else {

            combo = 0;
            numOfLives--;
            LivesTV.setText(numOfLives + "");
            isWinOrLose();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    animatedCardFlip(cards[c1].getCardView(), cardBack);
                    animatedCardFlip(cards[c2].getCardView(), cardBack);
                    cards[c1].getCardView().setClickable(true);
                    cards[c2].getCardView().setClickable(true);
                }
            }, 700);
        }
    }

    protected void isWinOrLose() {
        if (pairsMatched == NUM_OF_CARDS / 2) {
            //winner Dialog
            isWin = true;
            isPassed = true;
            myCountDown.startPause();
            final Dialog winnerDialog = new Dialog(context);
            winnerDialog.setContentView(R.layout.winner_dialog);
            winnerDialog.setCancelable(false);
            Button playAgain = winnerDialog.findViewById(R.id.playAgain);
            Button home = winnerDialog.findViewById(R.id.winGoHome);
            Button next = winnerDialog.findViewById(R.id.nextLevel);
            Objects.requireNonNull(winnerDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            if(!((Activity) context).isFinishing())
            {
                winnerDialog.show();
                //show dialog
            }


            playAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, thisLevel);
                    winnerDialog.dismiss();
                    startActivity(intent);
                    finish();

                }
            });

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    winnerDialog.dismiss();
                    startActivity(intent);
                    finish();
                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WorldActivity.class);
                    winnerDialog.dismiss();
                    startActivity(intent);
                    finish();
                }
            });


        } else if (numOfLives == 0) {
            //loser dialog out of lives
            myCountDown.startPause();

            final Dialog loserDialog = new Dialog(context);
            loserDialog.setContentView(R.layout.lose_lives_dialog);
            loserDialog.setCancelable(false);

            Button extraLives = loserDialog.findViewById(R.id.buyMoreLives);
            Button tryAgain = loserDialog.findViewById(R.id.outOfLivesTryAgain);
            Button home = loserDialog.findViewById(R.id.outOfLivesHome);
            Objects.requireNonNull(loserDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            if(!((Activity) context).isFinishing())
            {
                loserDialog.show();
                //show dialog
            }


            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    loserDialog.dismiss();
                    startActivity(intent);
                    finish();
                }
            });

            tryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, thisLevel);
                    loserDialog.dismiss();
                    startActivity(intent);
                    finish();
                }
            });
            extraLives.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (coins < 500) {
                        Toast.makeText(context, R.string.not_enough_coins, Toast.LENGTH_SHORT).show();
                    } else {
                        coins = coins - 500;
                        CoinsTV.setText(coins + "");
                        numOfLives = numOfLives + 3;
                        LivesTV.setText(numOfLives + "");
                        myCountDown.startPause();
                        loserDialog.dismiss();
                    }
                }
            });
        }
    }

    protected void animatedCardFlip(final ImageView v, final int imageID) {
        ObjectAnimator invisibleAnim = ObjectAnimator.ofFloat(v, "scaleY", 1f, 0f).setDuration(100);
        invisibleAnim.setInterpolator(new DecelerateInterpolator());
        invisibleAnim.start();
        final ObjectAnimator visibleAnim = ObjectAnimator.ofFloat(v, "scaleY", 0f, 1f).setDuration(100);
        visibleAnim.setInterpolator(new DecelerateInterpolator());
        invisibleAnim.addListener(new AnimatorListenerAdapter() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationEnd(Animator animation) {

                super.onAnimationEnd(animation);
                v.setImageDrawable(getDrawable(imageID));
                visibleAnim.start();
            }
        });
    }

    protected void animatedCardDisappear(final ImageView v){
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(v,"alpha", 1f,0f);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(150);
        fadeOut.setStartDelay(300);
        fadeOut.start();
    }

    protected void coinsCount() {
        coins = coins + (combo * 10);
    }


    @Override
    public void showOutOfTimeDialog() {
        if (!isWin) {
            //dialog out of time
            //final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            //final View dialogView = getLayoutInflater().inflate(R.layout.out_of_time_dialog, null);
            final Dialog loserDialog = new Dialog(context);
            loserDialog.setContentView(R.layout.out_of_time_dialog);
            loserDialog.setCancelable(false);

            Button tryAgain = loserDialog.findViewById(R.id.outOfTimeTryAgain);
            Button home = loserDialog.findViewById(R.id.outOfTimeHome);
            Button extraTime = loserDialog.findViewById(R.id.buyMoreTime);
            //builder.setView(dialogView);
            //builder.setCancelable(false);
            //final AlertDialog alertDialog = builder.show();
            Objects.requireNonNull(loserDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            if(!((Activity) context).isFinishing())
            {
                loserDialog.show();
                //show dialog
            }

            tryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, thisLevel);
                    loserDialog.dismiss();
                    startActivity(intent);
                    finish();
                }
            });

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    loserDialog.dismiss();
                    startActivity(intent);
                    finish();
                }
            });

            extraTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (coins < 500) {
                        Toast.makeText(context, R.string.not_enough_coins, Toast.LENGTH_SHORT).show();
                    } else {
                        coins = coins - 500;
                        CoinsTV.setText(coins + "");
                        myCountDown.setTimeLeftInMillis(30000);
                        myCountDown.startPause();
                        loserDialog.dismiss();
                    }
                }
            });

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("coins", coins);
        if (isPassed){
            editor.putBoolean(levelName, true);
        }
        editor.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
