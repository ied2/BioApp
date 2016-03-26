package is.mista.bioapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    public static JSONArray JSON_showtimes = null;
    public static JSONArray JSON_theaterList = null;
    public static JSONArray JSON_genres = null;
    private Utils utils = new Utils(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        downloadFromDatabase();
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    public void downloadFromDatabase() {

        new AsyncTask<Void, Void, String[]>() {
            protected String[] doInBackground(Void... params) {
                try {
                    return downloadHTML();
                } catch (Exception e) {
                    Log.d("IED", e.toString());
                }
                return null;
            }

            @Override
            protected void onPostExecute(String[] result) {
                try {
                    JSON_showtimes = new JSONArray(result[0]);
                    JSON_theaterList = new JSONArray(result[1]);
                    JSON_genres = new JSONArray(result[2]);
                    utils.init_movies();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    private String[] downloadHTML() throws Exception {

        String[] array = new String[3];
        URL url = new URL("http://kvikmyndir.is/api/showtimes/?key=9ELHcuylU8b8B7Y663bIvi52p6Ra1I68");

        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        String output1 = "";

        while ((line = br.readLine()) != null) {
            output1 += line;
        }
        array[0] = output1;
        Log.d("IED", output1);

        URL url2 = new URL("http://kvikmyndir.is/api/theater_list/?id=10&key=9ELHcuylU8b8B7Y663bIvi52p6Ra1I68");

        InputStream is2 = url2.openStream();
        InputStreamReader isr2 = new InputStreamReader(is2);
        BufferedReader br2 = new BufferedReader(isr2);
        String line2 = null;
        String output2 = "";

        while ((line2 = br2.readLine()) != null) {
            output2 += line2;
        }
        array[1] = output2;
        Log.d("IED", output2);

        URL url3 = new URL("http://kvikmyndir.is/api/genres/?key=9ELHcuylU8b8B7Y663bIvi52p6Ra1I68");

        InputStream is3 = url3.openStream();
        InputStreamReader isr3 = new InputStreamReader(is3);
        BufferedReader br3 = new BufferedReader(isr3);
        String line3 = null;
        String output3 = "";

        while ((line3 = br3.readLine()) != null) {
            output3 += line3;
        }
        array[2] = output3;
        Log.d("IED", output3);

        return array;
    }


//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}