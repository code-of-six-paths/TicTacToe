package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 - Yellow, 1 - red

    int[]  gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int [][] winningPositions = {{0, 1, 2}, {3, 4 ,5}, {6, 7, 8}, {0, 3, 6},
                                 {1, 4, 7}, {2, 5, 8}, {0, 4,8}, {2, 4 ,6}};
    int active_player = 0;
    boolean game_active = true;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && game_active) {


            gameState[tappedCounter] = active_player;
            counter.setTranslationY(-1500);

            if (active_player == 0) {
                counter.setImageResource(R.drawable.yellow);
                active_player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                active_player = 0;
            }


            counter.animate().translationYBy(1500).rotation(20).setDuration(500);


            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //Somebody wins in this case
                    game_active = false;
                    String winner = "";

                    if (active_player == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

//                    Toast.makeText(this, winner + " has won! Woohoo", Toast.LENGTH_SHORT).show();
                    Button playAgainButton = (Button) findViewById(R.id.buttonPlayAgain);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won! Woohoo");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.buttonPlayAgain);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);


//        winnerTextView.setText(winner + " has won! Woohoo");
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }


        for(int i = 0 ; i<gameState.length; i++){
            gameState[i] = 2;
        }
//        gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};


        active_player = 0;
        game_active = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}