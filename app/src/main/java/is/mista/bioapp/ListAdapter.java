package is.mista.bioapp;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static is.mista.bioapp.R.id.movie_poster;

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private String[] listOfMovies;
    private LayoutInflater inflater;

    public ListAdapter(Context context, String[] values) {
        this.mContext = context;
        this.listOfMovies = values;
        this.inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listOfMovies.length;
    }

    @Override
    public Object getItem(int position) {
        return listOfMovies[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // Find views for image and text
        ImageView imageView = (ImageView) convertView.findViewById(movie_poster);

        // Text
//        textView.setText(listOfMovies[position]);

        Log.d("IED", listOfMovies[position].trim());
        // Image
        Picasso.with(this.mContext)
                .load(listOfMovies[position].trim())
                .into(imageView);

        return convertView;
    }
}