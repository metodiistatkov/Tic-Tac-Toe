package com.example.metodiistatkov.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int row = 3;
    private int col = 3;

    private Button [][] positions = new Button[row][col];

    private boolean p1Move = true;

    private int numMoves;

    private int p1Score;
    private int p2Score;

    private TextView p1ViewScore;
    private TextView p2ViewScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1ViewScore = findViewById(R.id.p1_score);
        p2ViewScore = findViewById(R.id.p2_score);

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                String buttonID = "pos_" + i + j;
                int posID = getResources().getIdentifier(buttonID, "id", getPackageName());
                positions[i][j] = findViewById(posID);
                positions[i][j].setOnClickListener(this);

            }
        }

        final Button newGame = findViewById(R.id.button_newGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resetBoard();
               p1Score = 0;
                p2Score = 0;
                updatePoints();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
           return;

        }
        else {
            if(p1Move){
                ((Button) v ).setText("X");
            }
            else {
                ((Button) v ).setText("O");
            }

            numMoves++;

            if(hasWon()){
                if(p1Move){
                    p1Wins();
                }
                else {
                    p2Wins();
                }
            } else if (numMoves == 9){
                draw();
            } else { //change the turn order; i.e after a X is played comes an O and vice versa
                p1Move = !p1Move;
            }
        }

    }


    private boolean hasWon() {
        //check rows
        for (int i = 0; i < row; i++) {
            if (positions[i][0].getText().toString().equals(positions[i][1].getText().toString())
                    && positions[i][0].getText().toString().equals(positions[i][2].getText().toString())
                    && !positions[i][0].getText().toString().equals("")) {
                return true;
            }
        }

        //check columns
        for (int j = 0; j < row; j++) {
            if (positions[0][j].getText().toString().equals(positions[1][j].getText().toString())
                    && positions[0][j].getText().toString().equals(positions[2][j].getText().toString())
                    && !positions[0][j].getText().toString().equals("")) {
                return true;
            }
        }

        //check diag1
        if (positions[0][0].getText().toString().equals(positions[1][1].getText().toString())
                && positions[0][0].getText().toString().equals(positions[2][2].getText().toString())
                && !positions[0][0].getText().toString().equals("")) {
            return true;
        }

        //check diag2
        if (positions[0][2].getText().toString().equals(positions[1][1].getText().toString())
                && positions[0][2].getText().toString().equals(positions[2][0].getText().toString())
                && !positions[0][2].getText().toString().equals("")) {
            return true;
        }

        return false;
    }


    private void p1Wins() {
        p1Score++;
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void p2Wins() {
        p2Score++;
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePoints(){
       p1ViewScore.setText("Player 1: " + p1Score);
        p2ViewScore.setText("Player 2: " + p2Score);
    }

    private void resetBoard() {
        for(int i=0; i<row; i++){
            for (int j=0; j < col; j++){
                positions[i][j].setText("");
            }
        }

        numMoves = 0;
        p1Move = true;
    }
}
