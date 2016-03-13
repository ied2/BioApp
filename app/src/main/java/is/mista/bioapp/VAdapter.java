package is.mista.bioapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class VAdapter extends RecyclerView.Adapter<VAdapter.MyViewHolder> implements View.OnClickListener {

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList(); // Collections.emptyList() prevents null exception
    private Context context;

    public VAdapter(Context context, List<Information> data) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.data = data;
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

        Information current = data.get(position); // get current item from our list at given position

        holder.rating.setText(current.rating);

        holder.title.setText(current.title);

        Picasso.with(this.context)
                .load("http://www.hvaderibio.is/" + current.imageId)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        TextView rating;

        public MyViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.movie_poster);
            rating = (TextView) itemView.findViewById(R.id.rating);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.findViewById(R.id.dagskra).getVisibility() == View.INVISIBLE) {
//                    view.findViewById(R.id.movie_poster).setAlpha((float) 0.1);

            v.findViewById(R.id.dagskra).setVisibility(View.VISIBLE);
        }else {
//                    view.findViewById(R.id.movie_poster).setAlpha((float) 1);
            v.findViewById(R.id.dagskra).setVisibility(View.INVISIBLE);
        }
    }
}
