package is.mista.bioapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class InfoActivity extends YouTubeBaseActivity implements View.OnClickListener, YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private MyPlayerStateChangeListener playerStateChangeListener;
    private MyPlaybackEventListener playbackEventListener;
    private YouTubePlayer player;
    private int id;
    private String youtubeLink;
    private JSONObject movie;
    private TextView plot, duration, certificate, genre;
    private EditText write_review;
    private KeyListener edtTxtMessageKeyListener = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Reviews
//        write_review = (EditText) findViewById(R.id.write_review);
//        write_review.setOnClickListener(this);
//
//        edtTxtMessageKeyListener = write_review.getKeyListener();
//        write_review.setCursorVisible(false);
//        write_review.setKeyListener(null);

        plot = (TextView) findViewById(R.id.plot);
        duration = (TextView) findViewById(R.id.duration);
        certificate = (TextView) findViewById(R.id.certificate);
        genre = (TextView) findViewById(R.id.genre);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = Integer.parseInt(extras.getString("id"));
            duration.setText(extras.getString("duration") + " min");
            certificate.setText(extras.getString("certificate"));
//            genre.setText(extras.getString("genre"));
        }

        downloadMovie();

        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();

        // Back arrow enabled
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void downloadMovie() {
        class GetURLs extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InfoActivity.this,"Loading...","Please Wait...",true,true);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loading.dismiss();
                try {
                    movie = new JSONObject(result);

                    // Trailer
                    String title = movie.getString("trailers");
                    // Plot
                    String p = movie.getString("plot");
                    plot.setText(p);
                    // Youtube Link
                    JSONArray description = new JSONArray(title);
                    JSONObject rec = description.getJSONObject(0);
                    String url = rec.getString("url");
                    youtubeLink = url.split("/")[3];
//                    Log.d("IED", title);
//                    Log.d("IED", url);

                    youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
                    youTubeView.initialize(Config.YOUTUBE_API_KEY, InfoActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... string) {
                try {
                    URL url = new URL("http://kvikmyndir.is/api/movie/?id="+id+"&key=9ELHcuylU8b8B7Y663bIvi52p6Ra1I68");

                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String line = null;
                    String output = "";

                    while ((line = br.readLine()) != null) {
                        output += line;
                    }
//                    Log.d("IED", output);
                    return output;

                }catch(Exception e){
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        this.player = player;
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        if (!wasRestored) {
            Log.d("IED", youtubeLink);
            player.cueVideo(youtubeLink); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        @Override
        public void onPlaying() {
            // Called when playback starts, either due to user action or call to play().
            showMessage("Playing");
        }

        @Override
        public void onPaused() {
            // Called when playback is paused, either due to user action or call to pause().
            showMessage("Paused");
        }

        @Override
        public void onStopped() {
            // Called when playback stops for a reason other than being paused.
            showMessage("Stopped");
        }

        @Override
        public void onBuffering(boolean b) {
            // Called when buffering starts or ends.
        }

        @Override
        public void onSeekTo(int i) {
            // Called when a jump in playback position occurs, either
            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
        }
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
            // Called when the player is loading a video
            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
        }

        @Override
        public void onLoaded(String s) {
            // Called when a video is done loading.
            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
        }

        @Override
        public void onAdStarted() {
            // Called when playback of an advertisement starts.
        }

        @Override
        public void onVideoStarted() {
            // Called when playback of the video starts.
        }

        @Override
        public void onVideoEnded() {
            // Called when the video reaches its end.
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            // Called when an error occurs.
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.write_review:
//                write_review.setCursorVisible(true);
//                write_review.setKeyListener(edtTxtMessageKeyListener);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
