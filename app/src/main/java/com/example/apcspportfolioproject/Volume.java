package com.example.apcspportfolioproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Volume extends AppCompatActivity {

    // Variables
    Spinner VolumeUnitsSpinner;
    EditText input;
    final String[] VolumeUnits = {"Cubic Centimeter", "Cubic Millimeter","Cubic Inch", "Liter", "Milliliter", "Gallon","Fluid Ounce", "Tablespoon"};
    Button convert;
    TextView textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);


        // Set the Title
        Objects.requireNonNull(getSupportActionBar()).setTitle("Chemistry Helper");

        // Variables
        VolumeUnitsSpinner = findViewById(R.id.VolumeUnitsSpinner);
        input = findViewById(R.id.input);
        convert = findViewById(R.id.convert);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView2.setText("Cubic Centimeter");
        textView3.setText("Cubic Millimeter");
        textView4.setText("Cubic Inch");
        textView5.setText("Liter");
        textView6.setText("Milliliter");
        textView7.setText("Gallon");
        textView8.setText("Fluid Ounce");
        textView9.setText("Tablespoon");
        final TextView[] textViews = {textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9};




        // Spinner
        VolumeUnitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Populate the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, VolumeUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VolumeUnitsSpinner.setAdapter(adapter);


        // Convert Button
        convert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (input.getText().toString().isEmpty()) {
                    Toast.makeText(Volume.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                }
                else {
                    String currentSelection = VolumeUnitsSpinner.getSelectedItem().toString();
                    double inputNumber = Double.parseDouble(input.getText().toString());

                    double[] convertedValues = convertToAllUnits(currentSelection,inputNumber);
                    for (int i = 0; i < textViews.length; i++) {
                        Log.d("TAG", "onClick: " + convertedValues[i]);
                        textViews[i].setText(textViews[i].getText() + ": " + convertedValues[i]);
                    }

                }

            }
        });
    }

    // Methods
    public double convertMillilitersToMilliliters(double milliliters) {
        return milliliters;
    }
    public double convertMillilitersToCubicCentimeters(double milliliters) {
        return milliliters / 10.0;
    }
    public double convertMillilitersToCubicMillimeters(double milliliters) {
        return milliliters * 1000;
    }
    public double convertMillilitersToCubicInches(double milliliters) {
        return milliliters / 16.387064;
    }
    public double convertMillilitersToLiters(double milliliters) {
        return milliliters / 1000.0;
    }
    public double convertMillilitersToGallons(double milliliters) {
        return milliliters / 3785.412;
    }
    public double convertMillilitersToFluidOunces(double milliliters) {
        return milliliters / 29.5735295625;
    }
    public double convertMillilitersToTablespoons(double milliliters) {
        return milliliters / 14.78676478125;
    }
    public double convertToMilliliters(String units, double number) {
        if (units.equals("Cubic Centimeter")) {
            return number * 10;
        }
        else if (units.equals("Cubic Millimeter")) {
            return number / 1000.0;
        }
        else if (units.equals("Cubic Inch")) {
            return number * 16.387064;
        }
        else if (units.equals("Liter")) {
            return number * 1000;
        }
        else if (units.equals("Milliliter")) {
            return number;
        }
        else if (units.equals("Gallon")) {
            return number * 3785.412;
        }
        else if (units.equals("Fluid Ounce")) {
            return number * 29.5735295625;
        }
        else if (units.equals("Tablespoon")) {
            return number * 14.78676478125;
        }
        else {
            return 0;
        }
    }
    public double[] convertToAllUnits(String units, double number) {
        double[] convertedValues = new double[9];
        double milliliters = convertToMilliliters(units, number);
        convertedValues[0] = convertMillilitersToCubicCentimeters(milliliters);
        convertedValues[1] = convertMillilitersToCubicMillimeters(milliliters);
        convertedValues[2] = convertMillilitersToCubicInches(milliliters);
        convertedValues[3] = convertMillilitersToLiters(milliliters);
        convertedValues[4] = convertMillilitersToMilliliters(milliliters);
        convertedValues[5] = convertMillilitersToGallons(milliliters);
        convertedValues[6] = convertMillilitersToFluidOunces(milliliters);
        convertedValues[7] = convertMillilitersToTablespoons(milliliters);
        return convertedValues;
    }


}