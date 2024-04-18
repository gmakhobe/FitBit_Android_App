package com.givenm.fitlife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.AdapterView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ViewAllMealGoalsAdapter extends RecyclerView.Adapter<ViewAllMealGoalsAdapter.ViewHolder> {
    Context context;
    List<List<String>> data;

    private ItemClickLister listener;

    public ViewAllMealGoalsAdapter(Context context, List<List<String>> data, ItemClickLister listener){
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewAllMealGoalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_view_meal_goal_thumbnail, parent, false);

        ViewAllMealGoalsAdapter.ViewHolder viewHolder =  new ViewAllMealGoalsAdapter.ViewHolder(view, listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllMealGoalsAdapter.ViewHolder holder, int position) {

        holder.mealName.setText(data.get(position).get(0));
        holder.mealDescription.setText(data.get(position).get(1));
        holder.coloryIntake.setText(data.get(position).get(2));
        holder.protainIntake.setText(data.get(position).get(3));
        holder.fatIntake.setText(data.get(position).get(4));
        holder.fiberIntake.setText(data.get(position).get(5));
        holder.vitaminType.setText(data.get(position).get(6));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mealName, mealDescription, coloryIntake, protainIntake, fatIntake, fiberIntake, vitaminType;
        ItemClickLister listener;

        public ViewHolder(View view, ItemClickLister listener) {
            super(view);
            mealName = (TextView) view.findViewById(R.id.mealNameRes);
            mealDescription = (TextView) view.findViewById(R.id.mealDescriptionRes);
            coloryIntake = (TextView) view.findViewById(R.id.coloryIntakeRes);
            protainIntake = (TextView) view.findViewById(R.id.protainIntakeRes);
            fatIntake = (TextView) view.findViewById(R.id.fatIntakeRes);
            fiberIntake = (TextView) view.findViewById(R.id.fiberIntakeRes);
            vitaminType = (TextView) view.findViewById(R.id.vitaminIntakeRes);

            this.listener = listener;
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
