package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class achievements extends AppCompatActivity {

    // creating variables

    TextView txtPen1,txtPen2,txtPen3;
    ProgressBar penStarter,penCollector,penPackrat;
    int starterCount=0;
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    List<Items> itemsList;
    ImageView imgStarter,imgCollect, imgPackrat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        // setting the elements to the variables

        txtPen1 =findViewById(R.id.txtPen1);
        txtPen2= findViewById(R.id.txtPen2);
        txtPen3= findViewById(R.id.txtPen3);

        penStarter =findViewById(R.id.progressBarStarter);
        penCollector= findViewById(R.id.progressCollect);
        penPackrat= findViewById(R.id.progressPackrat);

        imgStarter= findViewById(R.id.imgStarter);
        imgCollect= findViewById(R.id.imgCollect);
        imgPackrat= findViewById(R.id.imgTolevel);
        itemsList=globalVariables.getItemList();
        SetAchievements();



    }
    // function for the progress bar
    public void SetAchievements(){

        // get the sizeof the array
        starterCount= itemsList.size();


//setting the progress bar to the size of the array
        if(starterCount>=1){
            penStarter.setProgress(100);
            imgStarter.setImageResource(R.drawable.ic_medal);
            txtPen1.setText("");

        }
        else if(starterCount==0){

            penStarter.setProgress(starterCount);
            txtPen1.setText( starterCount+"%");
        }

        // achievements for collector

        if(starterCount<3){
            txtPen2.setText("33%");
            penCollector.setProgress(33);
            if(starterCount==2){
                txtPen2.setText("66%");
                penCollector.setProgress(66);
            }
        }
        else if(starterCount==3){
            penCollector.setProgress(100);
            imgCollect.setImageResource(R.drawable.ic_collector);
            txtPen2.setText("");
        }


        // achievements  toplevel


        if(starterCount<10){
            starterCount= starterCount*10;
            txtPen3.setText((starterCount)+"%");
            penPackrat.setProgress(starterCount);

        }
        else if(starterCount==10){
            penPackrat.setProgress(100);
            imgPackrat.setImageResource(R.drawable.ic_tolevel);
            txtPen3.setText("");
        }



    }
}