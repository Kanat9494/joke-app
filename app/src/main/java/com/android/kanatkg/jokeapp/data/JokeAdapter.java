package com.android.kanatkg.jokeapp.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kanatkg.jokeapp.R;
import com.android.kanatkg.jokeapp.model.Joke;

import java.util.ArrayList;
import java.util.List;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Joke> jokes;

    public JokeAdapter(Context context, List<Joke> jokes) {
        this.inflater = LayoutInflater.from(context);
        this.jokes = jokes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.joke_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvType.setText(jokes.get(position).getSetup());
        holder.tvSetup.setText(jokes.get(position).getType());
        holder.tvPunchline.setText(jokes.get(position).getPunchline());
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvType, tvSetup, tvPunchline;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.tv_type);
            tvSetup = itemView.findViewById(R.id.tv_setup);
            tvPunchline = itemView.findViewById(R.id.tv_punchline);
        }
    }
}
