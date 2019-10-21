package com.mad.da1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Subject> subjectList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,day;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            day = (TextView) view.findViewById(R.id.day);
        }
    }


    public RecyclerAdapter(List<Subject> subjects) {
        this.subjectList = subjects;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        holder.name.setText(subject.getName());
        holder.day.setText(subject.getDay());
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }


    public void removeItem(int position) {
        subjectList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Subject item, int position) {
        subjectList.add(position, item);
        notifyItemInserted(position);
    }

    public List<Subject> getData() {
        return subjectList;
    }
}