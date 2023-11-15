package com.example.apcspportfolioproject;

import android.annotation.SuppressLint;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Objects;

public class Volume extends AppCompatActivity {


    // !Under Construction TODO redesign cover page!

    // Variables Used in this program
    Spinner VolumeUnitsSpinner;
    EditText input;
    final String[] VolumeUnits = {"Cubic Centimeter", "Cubic Millimeter","Cubic Inch", "Liter", "Milliliter", "Gallon","Fluid Ounce", "Tablespoon"}; // List Collection Type
    Button convert;
    TextView textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        // Removing the action bar and making the app fullscreen
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        // Initializing the variables used in this app
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
        final TextView[] textViews = {textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9}; // List Collection Type

        /*
            The following is the code for the spinner. The spinner is populated with the available units to convert to.
            The user can choose from any of the units in the spinner. The spinner is set to the first item in the lThat by
            default when the app is opened to avoid a null pointer exception. A toast is also displayed to the user when
            the user choose something to confirm the selection has been received.
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, VolumeUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VolumeUnitsSpinner.setAdapter(adapter);
        VolumeUnitsSpinner.setPrompt("Select a unit");
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

        /*
            The following is where the user can enter a number to be converted. This along with the selection in the spinner
            will be used to convert the number to all units. A toast is displayed when the user clicks enter on the EditText
            stating that the user should click the convert button instead.
         */
        input.setOnEditorActionListener((v, actionId, event) -> {
            Toast.makeText(Volume.this, "Please click the convert button", Toast.LENGTH_SHORT).show();
            return false;
        });

        /*
            The following is the main user action that triggers events in the app.
            The button takes in the user's number input in the EditText and the current selection in the spinner
            and converts the number to all units.
         */
        convert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (input.getText().toString().isEmpty()) {
                    Toast.makeText(Volume.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                }
                else {

                    /*
                        First the current selection in the spinner is taken and the user's input is taken and stored in a variable.
                        Then the convertToAllUnits method is called and the results are stored in an array.
                     */
                    String currentSelection = VolumeUnitsSpinner.getSelectedItem().toString();
                    double inputNumber = Double.parseDouble(input.getText().toString());

                    String[] convertedValues = convertToAllUnits(currentSelection, inputNumber, textViews); // ! 3b.i and 3c.ii List Collection Type and a call to a student created procedure

                    // Display the results in the TextViews
                    for (int i = 0; i < textViews.length; i++) { // An integrative algorithm is used to display the results
                        textViews[i].setText(convertedValues[i]); // Instructions for output
                    }
                }
            }
        });


    }

    /*
        The following methods convert milliliters to all units. The method takes in a double and returns a double.
        These methods are called in the convertToAllUnits method. All are examples of a student created procedure that
        contributes towards the intended purpose of the app. They all include one or more parameters with their data types
        indicated.
     */
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

    /*
        The following method takes in the user's input and the current selection in the spinner. Then the user's input is
        first converted into milliliters. Then the milliliters are converted into all other units using several methods all coded above.
         The array is then returned.
     */
    public String[] convertToAllUnits(String units, double number, TextView[] textViews) { // !3c.i code shows procedure with parameters
        // Variables
        String[] convertedValues = new String[8]; // List Collection Type
        double milliliters;

        // ! If-Statement to convert to common Unit of measure
        if (units.equals("Cubic Centimeter")) { milliliters = number * 10; }
        else if (units.equals("Cubic Millimeter")) { milliliters = number / 1000; }
        else if (units.equals("Cubic Inch")) { milliliters = number * 16.387064; }
        else if (units.equals("Liter")) { milliliters = number * 1000; }
        else if (units.equals("Milliliter")) { milliliters = number; }
        else if (units.equals("Gallon")) { milliliters = number * 3785.412; }
        else if (units.equals("Fluid Ounce")) { milliliters = number * 29.5735295625; }
        else if (units.equals("Tablespoon")) { milliliters = number * 14.78676478125; }
        else { milliliters = 0; }

        // Then convert to all other units
        convertedValues[0] = String.valueOf(convertMillilitersToCubicCentimeters(milliliters));
        convertedValues[1] = String.valueOf(convertMillilitersToCubicMillimeters(milliliters));
        convertedValues[2] = String.valueOf(convertMillilitersToCubicInches(milliliters));
        convertedValues[3] = String.valueOf(convertMillilitersToLiters(milliliters));
        convertedValues[4] = String.valueOf(convertMillilitersToMilliliters(milliliters));
        convertedValues[5] = String.valueOf(convertMillilitersToGallons(milliliters));
        convertedValues[6] = String.valueOf(convertMillilitersToFluidOunces(milliliters));
        convertedValues[7] = String.valueOf(convertMillilitersToTablespoons(milliliters));

        /* Add units to the end of each value by looping through the array and adding the units to the end of each
           value based on the text in the TextViews. If there is a ":" that means the textViews have been previously updated. So we grab the unit part
           of the text in the textView and ignore the rest. If that isn't the case then we just grab the text in the textView and add it to the end  */
        for (int i = 0; i < convertedValues.length; i++) {
            if (textViews[i].getText().toString().contains(":")) {

                convertedValues[i] += " :" + textViews[i].getText().toString().split(":")[1];
            }
            else{
                convertedValues[i] += " :" + textViews[i].getText().toString();
            }
        }

        // print the array to Log.d in one line
        Log.d("Converted Values", Arrays.toString(convertedValues));
        return convertedValues;
    }
}

