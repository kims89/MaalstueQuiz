package net.fjos.maalstuequiz;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private TextView tekstFeltSporsmal;
    private RadioButton svar1;
    private Drawable bildefil;
    private Drawable [] bildeQuizListe = null;
    private ImageView bilde;
    private RadioButton svar2;
    private RadioButton svar3;
    private RadioButton svar4;
    private RadioGroup svarGruppe;
    private Button angiKnapp;
    //Alle spørsmålene legges i en liste, det legges ti spørsmål i listen.
    private String sporsmal[] = {
            "Hvordan operativsystem er Android basert på?","Google oppkaller ofte androidversjonene sine etter søtsaker, hvordan er på bildet?","Når kjøpte Google selskapet Android Inc?",
            "Hva var den første smarttelefonen med Android-operativsystem?","Hva var Androids egentlige bruksområdet? Operativsystem for:","I 2011 kjøpte Google Motorola (mobilprodusent), deretter solgte selskapet til?",
            "Android-brukere gjør ofte noe for å få full tilgang til operativsystemet, hva kalles dette?","Hva brukes ADB (Android Debug Bridge) til?","Mange Apple-fanatikere tror Apple var først ute med smart-klokken, men egentlig var det?",
            "Android betyr robot som ser ut som en mann, hva heter roboter som ser ut som kvinner?"
    };
    //Det legges ti riktige svar i en liste.
    private String riktigSvar[] = {
            "Linux","KitKat","2005","T-Mobile G1","Fotokamera","Lenovo","Rooting","muliggjør tilkobling til datamaskin for utviklere","Sony (Android Wear)","Gynoid"
    };



    //ti mulige svar i hver av av valgalternativlistene.
    private String valgAlternativer1[] = {
            "Windows",
            "Jelly Bean",
            "2006",
            "T-Mobile G1",
            "Klokke",
            "Lenovo",
            "Pruting",
            "Sjekke konsekvent etter bug i operativsystemet",
            "Apple var det jo selfølgelig",
            "Girldroid"
    };
    private String valgAlternativer2[] = {
            "Linux",
            "KitKat",
            "2003",
            "Ludo P9s",
            "Server",
            "Sony",
            "Snooping",
            "En bru mellom debugmodus og normalmodus",
            "Sony (Android Wear)",
            "Femdroid"

    };

    private String valgAlternativer3[] = {
            "Symbian",
            "Ice Cream Sandwich",
            "2005",
            "HTC Dream",
            "Fotokamera",
            "Apple",
            "Snoozing",
            "En ubrukt funksjon",
            "Nokia (Symbian Watchsystem)",
            "Jellingdroid"

    };

    private String valgAlternativer4[] = {
            "OS X",
            "Kvikk Lunsj",
            "2001",
            "Google Sooner",
            "PC",
            "Huawei",
            "Rooting",
            "muliggjør tilkobling til datamaskin for utviklere",
            "Motorola (Android Wear)",
            "Gynoid"

    };

    //det settes opp et flag (nummer i en variable) som skal ha kontroll på hvor i listene den befinner seg i.
    //Står flag til 1, vil nr 2 i alle listene bli valgt.
    private int flag=0;
    //totalsummen til nå.
    private int Score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Alle bildene blir også lagt inn i lister. Det blir vist et nytt bilde for hvert spørsmål.
        bildeQuizListe = new Drawable[] {
                getResources().getDrawable(R.drawable.android),
                getResources().getDrawable(R.drawable.kitkatandroid),
                getResources().getDrawable(R.drawable.googlebuysandroid),
                getResources().getDrawable(R.drawable.oonergog),
                getResources().getDrawable(R.drawable.brainos),
                getResources().getDrawable(R.drawable.motorola),
                getResources().getDrawable(R.drawable.kuxlarge),
                getResources().getDrawable(R.drawable.adbandroid),
                getResources().getDrawable(R.drawable.watch),
                getResources().getDrawable(R.drawable.femadroid)

        };

        tekstFeltSporsmal=(TextView)findViewById(R.id.qTextView);
        svar1=(RadioButton)findViewById(R.id.svarRadioButton1);
        svar2=(RadioButton)findViewById(R.id.svarRadioButton2);
        svar3=(RadioButton)findViewById(R.id.svarRadioButton3);
        svar4=(RadioButton)findViewById(R.id.svarRadioButton4);
        svarGruppe=(RadioGroup)findViewById(R.id.svarRadioGroup);
        bilde=(ImageView)findViewById(R.id.quizImageView);


        //Her settes tekstfeltene til spørsmålene, bildene og mulige svarene til der hvor flagget er. Slik den står nå
        //vil flag=0 være valgt.
        tekstFeltSporsmal.setText(sporsmal[flag]);
        svar1.setText(valgAlternativer1[flag]);
        svar2.setText(valgAlternativer2[flag]);
        svar3.setText(valgAlternativer3[flag]);
        svar4.setText(valgAlternativer4[flag]);
        bilde.setImageDrawable(bildeQuizListe[flag]);

        angiKnapp=(Button)findViewById(R.id.button);


        angiKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Blir gjort en exception på hvor ikke valgt svar blir tatt. Ved feil skrives ut meldingen: "Velg et alternativ"
                try{

                    RadioButton Svar=(RadioButton)findViewById(svarGruppe.getCheckedRadioButtonId());
                    String strSvar = Svar.getText().toString();

                    //Hvis svaret fra valgt radioknapp tilsvarer fasiten, vil det legges inn +1 i scoren.
                    //score vil bli vist på slutten av quizen. Vurderer å utvide funksjonaliteten til at
                    //vise alle feile og riktige svarene på slutten av quizen, derfor ligger det også en else inne.
                    if(strSvar.equalsIgnoreCase(riktigSvar[flag])){
                        Score++;
                        Log.d("Q"," Riktig");
                    }
                    else {
                        Log.d("Q"," Feil");
                    }
                    //Flagget øker med +1
                    flag++;

                    //hvis flagget er mindre enn antallet i sporsmal-listen vil neste spørsmål bli vist.
                    if(flag<sporsmal.length){
                        tekstFeltSporsmal.setText(sporsmal[flag]);
                        svar1.setText(valgAlternativer1[flag]);
                        svar2.setText(valgAlternativer2[flag]);
                        svar3.setText(valgAlternativer3[flag]);
                        svar4.setText(valgAlternativer4[flag]);
                        bilde.setImageDrawable(bildeQuizListe[flag]);
                        svarGruppe.clearCheck();
                    }
                    //Hvis flaget er mer enn sporsmal-listen, vil brukeren bli sendt videre til resultat-activity.
                    else {
                        //her sendes score til resultActivity. Brukeren vil også bli sendt videre til den neste activityen.
                        String resultat = Integer.toString(Score);
                        Log.d("Resultat",resultat);
                        Intent intent = new Intent(getBaseContext(), resultActivity.class);
                        intent.putExtra("resultData",resultat);
                        startActivity(intent);

                    }

                }

                //her er exception som tar for seg hvis ikke brukeren velger et alternativ.
                catch(Exception e) {
                    Toast.makeText(MainActivity.this, "Velg et alternativ", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }



        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
