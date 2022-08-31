package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class analytics extends AppCompatActivity {
    List<Items> itemsList;
    List<Categories>categoriesList;

    BarChart barChart;
    PieChart pieChart;
    Spinner edtCategoryID;
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    private  static  final DecimalFormat df=new DecimalFormat("0.0");


    List<ShortDataClass>data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        barChart = findViewById(R.id.bar_chart);
        pieChart= findViewById(R.id.pie_chart);
        edtCategoryID=(Spinner) findViewById(R.id.spinner);
        String selectedCategory;
        int getGoal;
        double getpen;

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,globalVariables.getDropDownList());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtCategoryID.setAdapter(spinnerAdapter);
        categoriesList=globalVariables.getCategoryList();
        getpen= (25/0.45);


        selectedCategory=edtCategoryID.getSelectedItem().toString();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<PieEntry> pieEntries= new ArrayList<>();

        itemsList=globalVariables.getItemList();

        ShortTheData();
        for (ShortDataClass i: data){


            PieEntry pieEntry =new PieEntry(i.ItemCount,i.Category);
            pieEntries.add(pieEntry);
        }


      /*  PieEntry pieEntry =new PieEntry(getGoal,"TOTAL","nice");
        pieEntries.add(pieEntry);
        pieEntry =new PieEntry(ShortData(),"New "+ df.format( getpen)+"%",50);
        pieEntries.add(pieEntry);*/

      /*  BarDataSet barDataSet= new BarDataSet(barEntries,"Stuff");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(false);
        barChart.setData( new BarData(barDataSet));
         barChart.animateY(5000);
         barChart.getDescription().setText("stuff chart");
        barChart.getDescription().setText("stuff chart");
*/

        PieDataSet pieDataSet= new PieDataSet(pieEntries,"Category");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(50);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(20);
        pieChart.setData(new PieData(pieDataSet));
        pieChart.animateXY(5000,5000);
        pieChart.getDescription().setEnabled(false);


    }

    public void ShortTheData(){
int count=1;
        for(Categories i:categoriesList) {

            for (Items x : itemsList) {
                if (x.getCategoryID().contentEquals(i.getCategory())) {
                    count++;
                }


            }
            ShortDataClass shortData= new ShortDataClass(i.getCategory(), i.getGoal(), count);
            data.add(shortData);
        }


    }

}