package com.example.ttt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.content.Context;
import android.net.wifi.p2p.nsd.WifiP2pUpnpServiceInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int Arrayid[][]={{R.id.button1,R.id.button2,R.id.button3},{R.id.button4,R.id.button5,R.id.button6},{R.id.button7,R.id.button8,R.id.button9}};
    EditText player1,player2;
    Button resetButton;
    private int roundCount=0,player1points=0,player2points=0;
    private boolean isPlayer1=true;
    private Button board[][]=new Button[3][3];
    String play1="Player1",play2="Player2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1=findViewById(R.id.player1);
        player1.setEnabled(false);
        player2=findViewById(R.id.player2);
        player2.setEnabled(false);
        resetButton=findViewById(R.id.buttonReset);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j]=findViewById(Arrayid[i][j]);
            }
        }
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
                player1.setText(play1+" : 0");
                player2.setText(play2+" : 0");
            }
        });
    }



    public void onClick(View v)
    {
        if(!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        if(isPlayer1)
        {
            ((Button)v).setText("X");
        }
        else{
            ((Button)v).setText("O");
        }
        roundCount++;
        if(checkforWin())
        {
            if(isPlayer1)
            {
                player1points++;
                player1.setText(play1+" : "+player1points);
                Toast.makeText(this, play1+"Wins!!", Toast.LENGTH_SHORT).show();
                resetBoard();
            }
            else
            {
                player2points++;
                player2.setText(play2+ " : "+player2points);
                Toast.makeText(this, play2+"Wins!!", Toast.LENGTH_SHORT).show();
                resetBoard();
            }
        }
        else if(roundCount==9)
        {
            Toast.makeText(this, "Its a DRAW!!", Toast.LENGTH_SHORT).show();
            resetBoard();
        }
        else
        {
            isPlayer1=!isPlayer1;
        }

    }
    public boolean checkforWin()
    {
        String b[][]=new String[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                b[i][j]=board[i][j].getText().toString().trim();
            }
        }
        for(int i=0;i<3;i++)
        {
            if(b[i][0]==b[i][1] && b[i][1]==b[i][2] && b[i][0]!="")
                return true;
        }
        for(int j=0;j<3;j++)
        {
            if(b[0][j]==b[1][j] && b[1][j]==b[2][j] && b[1][j]!="")
                return true;
        }
        if(b[0][0].equals(b[1][1]) && b[1][1].equals(b[2][2]) && b[1][1]!="")
            return true;
        if(b[0][2].equals(b[1][1]) && b[1][1].equals(b[2][0]) && b[1][1]!="")
            return true;
        return false;
    }

    public void resetBoard()
    {
        for(int i=0;    i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j].setText("");
            }
        }
    }



}