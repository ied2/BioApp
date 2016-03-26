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
import android.widget.FrameLayout;
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
    private Context context;
    private List<Movie> movies;
    private String[] theaters = {"","SmáraBíó", "Háskólabíó", "BorgarBíó", "Laugarásbíó", "Bíó Paradís", "Sambíóin, Álfabakki",
                                "Kringlubíó", "Sambíóin, Egilshöll", "Sambíóin, Akureyri", "Sambíóin, Selfossi", "Sambíóin, Keflavík"};

    public VAdapter(Context context, List<Movie> movies) {
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

        holder.itemView.findViewById(R.id.shows).setVisibility(View.INVISIBLE);

        Movie current = movies.get(position);

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
            if(count == current.getShowtimes().size()) break;
            String showtimes = "";
            if(count == 0) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema1.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema1_showtime.setText(showtimes);
            }

            if(count == 1) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema2.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema2_showtime.setText(showtimes);
            }

            if(count == 2) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema3.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema3_showtime.setText(showtimes);
            }

            if(count == 3) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema4.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema4_showtime.setText(showtimes);
            }

            if(count == 4) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema5.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema5_showtime.setText(showtimes);
            }

            if(count == 5) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema6.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema6_showtime.setText(showtimes);
            }

            if(count == 6) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema7.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema7_showtime.setText(showtimes);
            }

            if(count == 7) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema8.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema8_showtime.setText(showtimes);
            }

            if(count == 8) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema9.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema9_showtime.setText(showtimes);
            }

            if(count == 9) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema10.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema10_showtime.setText(showtimes);
            }

            if(count == 10) {
                String name = theaters[Integer.parseInt(current.showtimes.get(count).getName())];
                holder.cinema11.setText(name);
                for(int i = 0; i < current.showtimes.get(count).getSchedule().size(); i++) {
                    showtimes += " " + current.showtimes.get(count).getSchedule().get(i);
                }
                holder.cinema11_showtime.setText(showtimes);
            }
            count++;
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        Button info;
        FrameLayout shows;
        TextView title;
        TextView rating;
        ImageView icon;

        TextView cinema1;
        TextView cinema1_showtime;
        TextView cinema2;
        TextView cinema2_showtime;
        TextView cinema3;
        TextView cinema3_showtime;
        TextView cinema4;
        TextView cinema4_showtime;
        TextView cinema5;
        TextView cinema5_showtime;
        TextView cinema6;
        TextView cinema6_showtime;
        TextView cinema7;
        TextView cinema7_showtime;
        TextView cinema8;
        TextView cinema8_showtime;
        TextView cinema9;
        TextView cinema9_showtime;
        TextView cinema10;
        TextView cinema10_showtime;
        TextView cinema11;
        TextView cinema11_showtime;



        public MyViewHolder(final View itemView) {
            super(itemView);

            cinema1 = (TextView) itemView.findViewById(R.id.cinema1);
            cinema1_showtime = (TextView) itemView.findViewById(R.id.cinema1_showtime);
            cinema2 = (TextView) itemView.findViewById(R.id.cinema2);
            cinema2_showtime = (TextView) itemView.findViewById(R.id.cinema2_showtime);
            cinema3 = (TextView) itemView.findViewById(R.id.cinema3);
            cinema3_showtime = (TextView) itemView.findViewById(R.id.cinema3_showtime);
            cinema4 = (TextView) itemView.findViewById(R.id.cinema4);
            cinema4_showtime = (TextView) itemView.findViewById(R.id.cinema4_showtime);
            cinema5 = (TextView) itemView.findViewById(R.id.cinema5);
            cinema5_showtime = (TextView) itemView.findViewById(R.id.cinema5_showtime);
            cinema6 = (TextView) itemView.findViewById(R.id.cinema6);
            cinema6_showtime = (TextView) itemView.findViewById(R.id.cinema6_showtime);
            cinema7 = (TextView) itemView.findViewById(R.id.cinema7);
            cinema7_showtime = (TextView) itemView.findViewById(R.id.cinema7_showtime);
            cinema8 = (TextView) itemView.findViewById(R.id.cinema8);
            cinema8_showtime = (TextView) itemView.findViewById(R.id.cinema8_showtime);
            cinema9 = (TextView) itemView.findViewById(R.id.cinema9);
            cinema9_showtime = (TextView) itemView.findViewById(R.id.cinema9_showtime);
            cinema10 = (TextView) itemView.findViewById(R.id.cinema10);
            cinema10_showtime = (TextView) itemView.findViewById(R.id.cinema10_showtime);
            cinema11 = (TextView) itemView.findViewById(R.id.cinema11);
            cinema11_showtime = (TextView) itemView.findViewById(R.id.cinema11_showtime);


            shows = (FrameLayout) itemView.findViewById(R.id.shows);
            title = (TextView) itemView.findViewById(R.id.title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            icon = (ImageView) itemView.findViewById(R.id.movie_poster);

            info = (Button) itemView.findViewById(R.id.info);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(), InfoActivity.class);
                    String title = (String) ((TextView) itemView.findViewById(R.id.title)).getText();
                    for(int j = 0; j < movies.size(); j++) {
                        if(title.equals(movies.get(j).getTitle())) {
                            i.putExtra("id", String.valueOf(movies.get(j).getId()));
                            i.putExtra("duration", String.valueOf(movies.get(j).getDuration()));
                            i.putExtra("certificate", movies.get(j).getCertificate());
//                            i.putExtra("genre", movies.get(j).getGenres());
                        }
                    }
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
