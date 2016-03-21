package is.mista.bioapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VAdapter extends RecyclerView.Adapter<VAdapter.MyViewHolder> implements View.OnClickListener {

    private LayoutInflater inflater;
    //List<Movie> data = Collections.emptyList(); // Collections.emptyList() prevents null exception
    private Context context;
    private Movie[] movies;

    public VAdapter(Context context, Movie[] movies) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.movies = movies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.listview_item, parent, false);
        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        Movie current = data.get(position); // get current item from our list at given position

        Movie current = movies[position];

        String title = current.getTitle();
        String rating = current.getRating();
        String poster = current.getPoster();

        // Title
        holder.title.setText(title);

        // Rating
        holder.rating.setText(rating);

        // Poster
        Picasso.with(this.context)
                .load(poster)
                .into(holder.icon);

        int count = 0;

        // ShowTimes
        while(true) {

            if(count == current.getShowtimes().length) break;
            if(count == 0) {
                holder.cinema1.setText(current.showtimes[count].getName());
                String showtimes = "";
                for(int i = 0; i < current.showtimes.length; i++) {
                    showtimes += " " + current.showtimes[count].getSchedule()[i];
                }
                holder.cinema1_showtime.setText(showtimes);
            }

            if(count == 1) {
                holder.cinema2.setText(current.showtimes[count].getName());
                String showtimes = "";
                for(int i = 0; i < current.showtimes.length; i++) {
                    showtimes += " " + current.showtimes[count].getSchedule()[i];
                }
                holder.cinema2_showtime.setText(showtimes);
            }

            if(count == 2) {
                holder.cinema3.setText(current.showtimes[count].getName());
                String showtimes = "";
                for(int i = 0; i < current.showtimes.length; i++) {
                    showtimes += " " + current.showtimes[count].getSchedule()[i];
                }
                holder.cinema3_showtime.setText(showtimes);
            }
            count++;
        }
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        Button info;
        LinearLayout shows;
        TextView title;
        TextView rating;
        ImageView icon;

        TextView cinema1;
        TextView cinema1_showtime;
        TextView cinema2;
        TextView cinema2_showtime;
        TextView cinema3;
        TextView cinema3_showtime;

        public MyViewHolder(final View itemView) {
            super(itemView);

            cinema1 = (TextView) itemView.findViewById(R.id.cinema1);
            cinema1_showtime = (TextView) itemView.findViewById(R.id.cinema1_showtime);
            cinema2 = (TextView) itemView.findViewById(R.id.cinema2);
            cinema2_showtime = (TextView) itemView.findViewById(R.id.cinema2_showtime);
            cinema3 = (TextView) itemView.findViewById(R.id.cinema3);
            cinema3_showtime = (TextView) itemView.findViewById(R.id.cinema3_showtime);

            shows = (LinearLayout) itemView.findViewById(R.id.shows);
            title = (TextView) itemView.findViewById(R.id.title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            icon = (ImageView) itemView.findViewById(R.id.movie_poster);

            info = (Button) itemView.findViewById(R.id.info);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(), InfoActivity.class);
                    itemView.getContext().startActivity(i);
                }
            });

        }
    }

    @Override
    public void onClick(View v) {
        if(v.findViewById(R.id.shows).getVisibility() == View.INVISIBLE) {
//                    view.findViewById(R.id.movie_poster).setAlpha((float) 0.1);

            v.findViewById(R.id.shows).setVisibility(View.VISIBLE);
        }else {
//                    view.findViewById(R.id.movie_poster).setAlpha((float) 1);
            v.findViewById(R.id.shows).setVisibility(View.INVISIBLE);
        }
    }
}
