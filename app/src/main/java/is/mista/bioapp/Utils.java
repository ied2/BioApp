package is.mista.bioapp;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utils {

    private Activity activity;
    private RecyclerView recyclerView;
    private VAdapter adapter;

    public Utils(Activity _activity){

        this.activity = _activity;
    }

    public void init_movies() throws Exception {

        JSONArray jsonShowtimes = MainActivity.JSON_showtimes;

        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < jsonShowtimes.length(); ++i) {
            Movie movie = new Movie();
            JSONObject rec = jsonShowtimes.getJSONObject(i);
            int id = rec.getInt("id");
            String title = rec.getString("title");
            String certificate = rec.getString("certificateIS");
            int durationMinutes = rec.getInt("durationMinutes");
            String poster = rec.getString("poster");
            movie.setId(id);
            movie.setTitle(title);
            movie.setCertificate(certificate);
            movie.setDuration(durationMinutes);
            movie.setPoster(poster);


            // Genres
            JSONArray jgenres = rec.getJSONArray("genres");
            List<String> genres = new ArrayList<>();
            for (int j = 0; j < jgenres.length(); ++j) {
                genres.add(jgenres.getString(j)); // iterate the JSONArray and extract the keys
            }

            // Actors
            JSONArray actors_abridged = rec.getJSONArray("actors_abridged");
            for (int j = 0; j < actors_abridged.length(); ++j) {
                JSONObject actors = actors_abridged.getJSONObject(j);
                String name = actors.getString("name");
            }

            // Directors
            JSONArray directors_abridged = rec.getJSONArray("actors_abridged");
            for (int j = 0; j < directors_abridged.length(); ++j) {
                JSONObject directors = directors_abridged.getJSONObject(j);
                String name = directors.getString("name");
            }

            // IMDB ratings
            String ratings = rec.getString("ratings");
            JSONObject jratings = new JSONObject(ratings);
            String imdb = jratings.getString("imdb");
            movie.setRating(imdb);


            // Showtimes
            List<Cinema> cinemas = new ArrayList<>();
            JSONArray jshowtimes = rec.getJSONArray("showtimes");
            for (int j = 0; j < jshowtimes.length(); ++j) {
                Cinema cinema = new Cinema();
                JSONObject showtimes = jshowtimes.getJSONObject(j);
                String c = showtimes.getString("cinema");
                cinema.setName(c);

                JSONArray jschedule = showtimes.getJSONArray("schedule");
                List<String> schedule = new ArrayList<>();
                for (int k = 0; k < jschedule.length(); ++k) {
                    schedule.add(jschedule.getString(k));
                }
                cinema.setSchedule(schedule);
                cinemas.add(cinema);
            }
            movie.setShowtimes(cinemas);
            movies.add(movie);
        }

        populate(movies/*title, rating, imdbUrl, restriction, poster, theater, human, number*/);
    }

    private void populate(List<Movie> Movies/*final String[] title, final String[] rating, final String[] imdbUrl, final String[] restriction,
                          final String[] poster, final String[] theater, final String[] human, final String[] number*/) {

        recyclerView = (RecyclerView) activity.findViewById(R.id.recycle_list);
        adapter = new VAdapter(activity, Movies/*getData(title, rating, imdbUrl, restriction, poster, theater, human, number)*/);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }

//    public static List<Movie> getData(String[] title, String[] rating, String[] imdbUrl, String[] restriction,
//                                            String[] poster, String[] theater, String[] human, String[] number) {
//        List<Movie> data = new ArrayList<>();
//
//        for(int i = 0; i < poster.length; i++) {
//            Movie current = new Movie();
//            current.title = title[i];
//            current.rating = rating[i];
//            current.imdbUrl = imdbUrl[i];
//            current.restriction = restriction[i];
//            current.poster = poster[i];
//            current.theater = theater[i];
//            current.human = human[i];
//            current.number = number[i];
//            data.add(current);
//        }
//        return data;
//    }
}

//    public Movie[] dummyTest() {
//        Movie[] movies = new Movie[3];
//        Movie movie = new Movie();
//        Movie movie2 = new Movie();
//        Movie movie3 = new Movie();
//
//        // StarWars
//        movie.setTitle("StarWars");
//        movie.setPoster("http://kvikmyndir.is/images/poster/star_wars-1445273192.jpg");
//        movie.setRating("7.5");
//
//        Cinema cinema = new Cinema();
//        cinema.setName("Smárabíó");
//        String[] s = {"22:00 (3)", "20:00 (3)"};
//        cinema.setSchedule(s);
//
//        Cinema lau = new Cinema();
//        lau.setName("Laugarásbíó");
//        String[] slau = {"18:00 (2)", "21:00 (1)"};
//        lau.setSchedule(slau);
//
//        Cinema[] c = {cinema, lau};
//
//        movie.setShowtimes(c);
//
//        // Fifty Shades of Black
//        movie2.setTitle("Fifty Shades of Black");
//        movie2.setPoster("http://kvikmyndir.is/images/poster/bla-1458037750.jpg");
//        movie2.setRating("7.0");
//
//        Cinema cinema2 = new Cinema();
//        cinema2.setName("Smárabíó");
//        String[] s2 = {"22:00 (3)", "20:00 (3)"};
//        cinema2.setSchedule(s2);
//        Cinema[] c2 = {cinema2};
//
//        movie2.setShowtimes(c2);
//
//        // Kung Fu Panda 3
//        movie3.setTitle("Kung Fu Panda 3");
//        movie3.setPoster("http://kvikmyndir.is/images/poster/9642_500.jpg");
//        movie3.setRating("8.0");
//
//        Cinema cinema3 = new Cinema();
//        cinema3.setName("Smárabíó");
//        String[] s3 = {"22:30 (3)", "20:30 (3)"};
//        cinema3.setSchedule(s3);
//        Cinema[] c3 = {cinema3};
//
//        movie3.setShowtimes(c3);
//
//        movies[0] = movie;
//        movies[1] = movie2;
//        movies[2] = movie3;
//
//        return movies;
//    }
