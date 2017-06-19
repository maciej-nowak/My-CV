package pl.maciej_nowak.mycv.experience;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pl.maciej_nowak.mycv.R;

/**
 * Created by Maciej on 19.06.2017.
 */

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ViewHolder> {

    private Context context;
    private List<Experience> list;

    public ExperienceAdapter(Context context, List<Experience> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_item, parent, false);
        return new ExperienceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.subtitle.setText(list.get(position).getSubtitle());
        holder.date.setText(list.get(position).getDate());
        holder.extras.setText(list.get(position).getExtras());
        Picasso.with(context).load(list.get(position).getImage()).resize(80, 80).centerInside().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final View item;
        private final TextView title, subtitle, date, extras;
        private final ImageView image;

        ViewHolder(View view) {
            super(view);
            item = view;
            title = (TextView) view.findViewById(R.id.experience_title);
            subtitle = (TextView) view.findViewById(R.id.experience_description);
            date = (TextView) view.findViewById(R.id.experience_date);
            extras = (TextView) view.findViewById(R.id.experience_extras);
            image = (ImageView) view.findViewById(R.id.experience_logo);
        }
    }
}
