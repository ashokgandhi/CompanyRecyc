package com.example.companyrecycle;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.companyrecycle.ApiResponse.CyclerResponse;
import com.example.companyrecycle.SessionManager.SessionManager;

import java.util.ArrayList;

public class CyclerAdapter extends RecyclerView.Adapter<CyclerAdapter.ItemViewHolder> {

    Context context;
    ArrayList<CyclerResponse.User>response;



    public CyclerAdapter(Context context, ArrayList<CyclerResponse.User> response) {
        this.context = context;
        this.response = response;
    }

    @NonNull
    @Override
    public CyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.row,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CyclerAdapter.ItemViewHolder itemViewHolder, final int position) {

        itemViewHolder.company1.setText(response.get(position).getHeading());
        itemViewHolder.aboutus1.setText(response.get(position).getDescription());
        itemViewHolder.experience.setText(response.get(position).getSkills());
        itemViewHolder.subject.setText(response.get(position).getJobType());
        itemViewHolder.Currency.setText(response.get(position).getSalary());


        itemViewHolder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context ,Information_Form.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView company1;
        TextView aboutus1;
        TextView Skill;
        TextView experience;
        TextView JobType;
        TextView subject;
        TextView Salary;
        TextView Currency;
        Button button1;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            company1=itemView.findViewById(R.id.company1);
            aboutus1=itemView.findViewById(R.id.aboutus1);
            Skill=itemView.findViewById(R.id.Skill);
            experience =itemView.findViewById(R.id.experience);
            JobType=itemView.findViewById(R.id.JobType);
            subject=itemView.findViewById(R.id.subject);
            Salary=itemView.findViewById(R.id.Salary);
            Currency=itemView.findViewById(R.id.Currency);

            button1=itemView.findViewById(R.id.button1);



        }
    }


}
