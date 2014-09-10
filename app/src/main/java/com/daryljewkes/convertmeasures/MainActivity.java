package com.daryljewkes.convertmeasures;

import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Spinner unitTySpinner;
    private EditText amountTextView;

    TextView teaspoonTextView, tablespoonTextView, cupTextView, ounceTextView,
            pintTextView, quartTextView, gallonTextView, poundTextView,
            milliliterTextView, litreTextView, milligramTextView, kilogramTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemsToUnitTypeSpinner();
        addListenerToUnitTypeSpinner();
        amountTextView = (EditText) findViewById(R.id.amount_text_view);
        initialiseTextViews();

    }

    private void addItemsToUnitTypeSpinner() {
        unitTySpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        ArrayAdapter<CharSequence> unitTypeSpinnerAdaptor = ArrayAdapter.createFromResource(this, R.array.conversion_types, android.R.layout.simple_spinner_item);
        unitTypeSpinnerAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitTySpinner.setAdapter(unitTypeSpinnerAdaptor);
    }

    private void addListenerToUnitTypeSpinner() {
        unitTySpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        unitTySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
                // TODO checkIfConvertingFromTsp(itemSelectedInSpinner);
            }

        });
    }

    public void checkIfConvertingFromTsp(String currentUnit) {
        if (currentUnit.equals("teaspoon")) {
            updateUnitTypeUsingTsp(Quantity.Unit.tsp);
        } else {
            if(currentUnit.equals("tablespoon")){

                updateUnitTypesUsingOther(Quantity.Unit.tbs);

            } else if(currentUnit.equals("cup")){

                updateUnitTypesUsingOther(Quantity.Unit.cup);

            } else if(currentUnit.equals("ounce")){

                updateUnitTypesUsingOther(Quantity.Unit.oz);

            } else if(currentUnit.equals("pint")){

                updateUnitTypesUsingOther(Quantity.Unit.pint);

            } else if(currentUnit.equals("quart")){

                updateUnitTypesUsingOther(Quantity.Unit.quart);

            } else if(currentUnit.equals("gallon")){

                updateUnitTypesUsingOther(Quantity.Unit.gallon);

            } else if(currentUnit.equals("pound")){

                updateUnitTypesUsingOther(Quantity.Unit.pound);

            } else if(currentUnit.equals("milliliter")){

                updateUnitTypesUsingOther(Quantity.Unit.ml);

            } else if(currentUnit.equals("liter")){

                updateUnitTypesUsingOther(Quantity.Unit.litre);

            } else if(currentUnit.equals("milligram")){

                updateUnitTypesUsingOther(Quantity.Unit.mg);

            } else {

                updateUnitTypesUsingOther(Quantity.Unit.kg);
            }
        }
    }

    private void updateUnitTypesUsingOther(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        String valueInTeaspoons = currentQuantitySelected.to(Quantity.Unit.tsp).toString();

        teaspoonTextView.setText(valueInTeaspoons);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.litre, litreTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.kg, kilogramTextView);

        if (currentUnit.name().equals(currentQuantitySelected.unit.name())){
            String currentUnitTextViewText = doubleToConvert + " " + currentQuantitySelected.unit.name();
            String currentTextViewName = currentQuantitySelected.unit.name() + "_text_view";

            int currentId = getResources().getIdentifier(currentTextViewName, "id", MainActivity.this.getPackageName());

            TextView currentTextView = (TextView) findViewById(currentId);
            currentTextView.setText(currentUnitTextViewText);
        }
    }

    private void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit currentUnit, Quantity.Unit preferredUnit, TextView targetTextView) {
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);
        String tempTextViewText = currentQuantitySelected.to(Quantity.Unit.tsp).to(preferredUnit).toString();
        targetTextView.setText(tempTextViewText);
    }

    public void updateUnitTypeUsingTsp(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String teaspoonValueAndUnit = doubleToConvert + " tsp";
        teaspoonTextView.setText(teaspoonValueAndUnit);

        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.litre, litreTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.kg, kilogramTextView);
    }

    private void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit unitConvertingTo, TextView theTextView) {
        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);
    }

    private void initialiseTextViews() {
        teaspoonTextView = (TextView) findViewById(R.id.tsp_text_view);
        tablespoonTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ounceTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        poundTextView = (TextView) findViewById(R.id.pound_text_view);
        milliliterTextView = (TextView) findViewById(R.id.ml_text_view);
        litreTextView = (TextView) findViewById(R.id.litre_text_view);
        milligramTextView = (TextView) findViewById(R.id.mg_text_view);
        kilogramTextView = (TextView) findViewById(R.id.kg_text_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
