package is.mista.bioapp;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

        JSONArray s = new JSONArray(movies.getString("movies"));

        String[] poster = new String[s.length()];
        String[] rating = new String[s.length()];
        String[] title = new String[s.length()];

        for(int i=0; i < s.length(); i++) {
            JSONObject item = (JSONObject)s.get(i);
            String p = item.getString("poster");
            String r = item.getString("rating");
            String t = item.getString("title");
            poster[i] = p;
            rating[i] = r;
            title[i] = t;
        }
        populate(poster, rating, title);
    }

    private void populate(final String[] poster, final String[] rating, final String[] title) {

        recyclerView = (RecyclerView) activity.findViewById(R.id.recycle_list);
        adapter = new VAdapter(activity, getData(poster, rating, title));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }

    public static List<Information> getData(String[] poster, String[] rating, String[] title) {
        List<Information> data = new ArrayList<>();

        for(int i = 0; i < poster.length; i++) {
            Information current = new Information();
            current.imageId = poster[i];
            current.rating = rating[i];
            current.title = title[i];
            data.add(current);
        }
        return data;
    }
}
