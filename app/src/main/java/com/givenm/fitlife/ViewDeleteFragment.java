package com.givenm.fitlife;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewDeleteFragment extends Fragment implements ItemClickLister {

    TextView vewListOfGoalsIsEmpty;
    List<List<String>> nutritionInformation = new ArrayList<>();
    DataManager dataManager;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
        view = inflator.inflate(R.layout.content_view, container, false);
        dataManager = new DataManager(getActivity());

        viewNutritionalItemsInTheDBTable();

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {

        AlertDialog.Builder alertDialogBuilder  = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Action Required!");
        alertDialogBuilder.setMessage("Delete this nutritional goal item with the following Meal name:" + nutritionInformation.get(position).get(0));

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteNutritionalItemsInTheDBTable(nutritionInformation.get(position).get(0), nutritionInformation.get(position).get(1));
                viewNutritionalItemsInTheDBTable();
                dialogInterface.dismiss();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();

        Toast.makeText(getActivity(), "Item " + position + " clicked!", Toast.LENGTH_SHORT).show();

        Log.i("View|Delete", "Item clicked.");
    }

    public void deleteNutritionalItemsInTheDBTable(String name, String description) {
        dataManager.delete(name, description);
    }

    public void viewNutritionalItemsInTheDBTable(){

        Cursor results = dataManager.view();
        nutritionInformation = new ArrayList<>();

        while (results.moveToNext()) {

            List<String> tempDataHolder = new ArrayList<>();
            tempDataHolder.add(results.getString(1));
            tempDataHolder.add(results.getString(2));
            tempDataHolder.add(results.getString(3));
            tempDataHolder.add(results.getString(4));
            tempDataHolder.add(results.getString(5));
            tempDataHolder.add(results.getString(6));
            tempDataHolder.add(results.getString(7));

            nutritionInformation.add(tempDataHolder);

        }

        vewListOfGoalsIsEmpty = view.findViewById(R.id.viewListOfGoalsEmpty);

        if (results.getCount() == 0) {
            vewListOfGoalsIsEmpty.setText("You have not captured any nutrional goals yet.");
        }else {
            vewListOfGoalsIsEmpty.setPadding(0,0,0,0);
        }

        RecyclerView recyclerView = view.findViewById(R.id.listOfNutritionGoals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ViewAllMealGoalsAdapter adapter = new ViewAllMealGoalsAdapter(getActivity(), nutritionInformation, this);
        recyclerView.setAdapter(adapter);
    }
}
