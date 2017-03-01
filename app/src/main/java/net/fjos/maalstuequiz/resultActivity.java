package net.fjos.maalstuequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultActivity extends AppCompatActivity {
    private TextView textMsgView;
    private TextView textScoreView;
    private Button resetQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Det blir satt opp to tekstview i denne activityen (som endrer seg. Det er yttelige flere, som er faste.
        textMsgView = (TextView)findViewById(R.id.resultSumTextView);
        textScoreView = (TextView)findViewById(R.id.scoreTextView);
        resetQuiz = (Button)findViewById(R.id.resetQuiz);

        //resultatet(score) som hentes fra den andre aktivetien.
        Bundle result = getIntent().getExtras();
        String resultData = result.getString("resultData");

        //Resultatet blir omgjort til int for å kunne legge ved en tekst som sier hvordan brukeren gjorde det.
        int resultDataInt = Integer.parseInt(resultData);

        //resultatet settes.
        textScoreView.setText(resultData+"/10 Poeng");

        //En morsom tekstsnutt på hvordan brukeren gjorde det.
        if (resultDataInt==0){
            textMsgView.setText("Dette var virkelig ikke din greie, tror du burde kjøpe en iPhone.");
        }
        else if ((resultDataInt >= 1) && (resultDataInt <=3)){
            textMsgView.setText("Du har et stykke å gå enda.");
        }
        else if ((resultDataInt >= 4) && (resultDataInt <=7)){
            textMsgView.setText("Du var ikke så aller verst på dette, men fortsatt et stykke igjen.");
        }
        else if ((resultDataInt >= 8) && (resultDataInt <=9)){
            textMsgView.setText("Nesten... :)");
        }
        else {
            textMsgView.setText("Imponerende!");
        }

        //Quizen er mulig å ta på nytt ved å trykke på knappen nedenfor resultatet.
        resetQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }

}
