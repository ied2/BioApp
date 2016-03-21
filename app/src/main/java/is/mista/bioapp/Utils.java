package is.mista.bioapp;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private Activity activity;
    private RecyclerView recyclerView;
    private VAdapter adapter;

    public Utils(Activity _activity){

        this.activity = _activity;
    }

    public void init_movies() throws Exception {

        JSONObject movies = MainActivity.JSON_movies;

        JSONArray s1 = new JSONArray(movies.getString("movies"));
        int len = s1.length();

        JSONObject[] jMovies = new JSONObject[len];

        for(int i=0; i < s1.length(); i++) {
            JSONObject item = (JSONObject)s1.get(i);
            jMovies[i] = item;
//            Log.d("IED", item.toString());
        }

//        int len = s1.length();
//
//        String[] title = new String[len];
//        String[] rating = new String[len];
//        String[] imdbUrl = new String[len];
//        String[] restriction = new String[len];
//        String[] poster = new String[len];
//        String[] theater = new String[len];
//        String[] human = new String[len];
//        String[] number = new String[len];
//
//        for(int i=0; i < s1.length(); i++) {
//            JSONObject item1 = (JSONObject)s1.get(i);
//            String ti = item1.getString("title");
//            String ra = item1.getString("rating");
//            String im = item1.getString("imdbUrl");
//            String re = item1.getString("restriction");
//            String po = item1.getString("poster");
//            title[i] = ti;
//            rating[i] = ra;
//            imdbUrl[i] = im;
//            restriction[i] = re;
//            poster[i] = po;
//
//            JSONArray s2 = new JSONArray(item1.getString("shows"));
//            for(int j = 0; j < s2.length(); j++) {
//                JSONObject item2 = (JSONObject)s2.get(j);
//                String th = item2.getString("theater");
//                theater[i] += th;
//                Log.d("IED", j + " theater: " + th);
//
//                JSONArray s3 = new JSONArray(item2.getString("times"));
//                for(int k = 0; k < s3.length(); k++) {
//                    JSONObject item3 = (JSONObject)s3.get(k);
//                    String hu = item3.getString("human");
//                    String no = item3.getString("number");
//                    human[i] += hu;
//                    number[i] += no;
//                }
//            }
//        }
        populate(dummyTest()/*title, rating, imdbUrl, restriction, poster, theater, human, number*/);
    }

    public Movie[] dummyTest() {
        Movie[] movies = new Movie[3];
        Movie movie = new Movie();
        Movie movie2 = new Movie();
        Movie movie3 = new Movie();

        // StarWars
        movie.setTitle("StarWars");
        movie.setPoster("http://kvikmyndir.is/images/poster/star_wars-1445273192.jpg");
        movie.setRating("7.5");

        Cinema cinema = new Cinema();
        cinema.setName("Smárabíó");
        String[] s = {"22:00 (3)", "20:00 (3)"};
        cinema.setSchedule(s);

        Cinema lau = new Cinema();
        lau.setName("Laugarásbíó");
        String[] slau = {"18:00 (2)", "21:00 (1)"};
        lau.setSchedule(slau);

        Cinema[] c = {cinema, lau};

        movie.setShowtimes(c);

        // Fifty Shades of Black
        movie2.setTitle("Fifty Shades of Black");
        movie2.setPoster("http://kvikmyndir.is/images/poster/bla-1458037750.jpg");
        movie2.setRating("7.0");

        Cinema cinema2 = new Cinema();
        cinema2.setName("Smárabíó");
        String[] s2 = {"22:00 (3)", "20:00 (3)"};
        cinema2.setSchedule(s2);
        Cinema[] c2 = {cinema2};

        movie2.setShowtimes(c2);

        // Kung Fu Panda 3
        movie3.setTitle("Kung Fu Panda 3");
        movie3.setPoster("http://kvikmyndir.is/images/poster/9642_500.jpg");
        movie3.setRating("8.0");

        Cinema cinema3 = new Cinema();
        cinema3.setName("Smárabíó");
        String[] s3 = {"22:30 (3)", "20:30 (3)"};
        cinema3.setSchedule(s3);
        Cinema[] c3 = {cinema3};

        movie3.setShowtimes(c3);

        movies[0] = movie;
        movies[1] = movie2;
        movies[2] = movie3;

        return movies;
    }

    private void populate(Movie[] Movies/*final String[] title, final String[] rating, final String[] imdbUrl, final String[] restriction,
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
