package com.givenm.fitlife;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class InsertFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private String[] VitaminsListArray = { "NA", "Vitamin A", "Vitamin B Complex", "Vitamin C", "Vitamin D", "Vitamin E", "Vitamin K"};
    String vitaminType = "";
    EditText mealName;
    EditText mealDescription;
    EditText coloryIntake;
    EditText protainIntake;
    EditText fatIntake;
    EditText fiberIntake;

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){

        View view = inflator.inflate(R.layout.content_insert, container, false);

        Spinner vitaminListSpinner = view.findViewById(R.id.vitaminListSpinner);
        vitaminListSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, VitaminsListArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        vitaminListSpinner.setAdapter(arrayAdapter);

        final DataManager dataManager = new DataManager(getActivity());

        // Get Data of all of the elements
        mealName = view.findViewById(R.id.mealName);
        mealDescription = view.findViewById(R.id.mealDescription);
        coloryIntake = view.findViewById(R.id.coloryIntake);
        protainIntake = view.findViewById(R.id.protainIntake);
        fatIntake = view.findViewById(R.id.fatIntake);
        fiberIntake = view.findViewById(R.id.fiberIntake);

        Button insertButton = view.findViewById(R.id.insertButton);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mealName.getText().length() == 0 || mealDescription.getText().length() == 0) {
                    showAlertMessage("Warning", "Meal name and Meal Description must not be empty.");
                    return ;
                }

                float coloryIntakeValue =  mineralValueAssignment(String.valueOf(coloryIntake.getText()));
                float protainIntakeValue =  mineralValueAssignment(String.valueOf(protainIntake.getText()));
                float fatIntakeValue =  mineralValueAssignment(String.valueOf(fatIntake.getText()));
                float fiberIntakeValue =  mineralValueAssignment(String.valueOf(fiberIntake.getText()));

                dataManager.insert(mealName.getText().toString(), mealDescription.getText().toString(), coloryIntakeValue, protainIntakeValue, fatIntakeValue, fiberIntakeValue, vitaminType);

                showAlertMessage("Success", "Data captured successfully.");
                mealName.setText("");
                mealDescription.setText("");
                coloryIntake.setText("");
                protainIntake.setText("");
                fatIntake.setText("");
                fiberIntake.setText("");


            }
        });

        return view;
    }

    public float mineralValueAssignment(String value) {
        float convertedValue = 0;

        if (value.length() != 0) {
            convertedValue = Float.parseFloat(value);
        }

        return convertedValue;
    }

    public void showAlertMessage(String title, String message) {
        AlertDialog.Builder alertDialogBuilder  = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        vitaminType = VitaminsListArray[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
