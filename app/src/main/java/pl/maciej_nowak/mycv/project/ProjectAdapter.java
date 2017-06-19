package pl.maciej_nowak.mycv.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pl.maciej_nowak.mycv.R;

/**
 * Created by Maciej on 19.06.2017.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private Context context;
    private List<Project> list;

    public ProjectAdapter(Context context) {
        this.context = context;
        this.list = setDefaultData();
    }

    public ProjectAdapter(Context context, List<Project> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new ProjectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProjectAdapter.ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.description.setText(list.get(position).getDescription());
        Picasso.with(context).load(list.get(position).getLogo()).resize(80, 80).centerInside().into(holder.logo);
        Picasso.with(context).load(list.get(position).getImages()[0]).fit().centerCrop().into(holder.image1);
        Picasso.with(context).load(list.get(position).getImages()[1]).fit().centerCrop().into(holder.image2);
        Picasso.with(context).load(list.get(position).getImages()[2]).fit().centerCrop().into(holder.image3);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<Project> setDefaultData() {
        List<Project> list = new ArrayList<>();

        int[] placetagImages = {R.drawable.placetag_1, R.drawable.placetag_2, R.drawable.placetag_3};
        list.add(new Project("Placetag",
                "Set your spot application",
                R.drawable.logo_placetag,
                placetagImages));

        int[] songbookImages = {R.drawable.songbook_1, R.drawable.songbook_2, R.drawable.songbook_3};
        list.add(new Project("Songbook",
                "Interactive songbook application for music bands, mainly for keyboard players",
                R.drawable.logo_songbook,
                songbookImages));

        int[] kindleImages = {R.drawable.kindle_1, R.drawable.kindle_2, R.drawable.kindle_3};
        list.add(new Project("Push to Kindle",
                "Application for sending website to Kindle device",
                R.drawable.logo_push_to_kindle,
                kindleImages));

        return list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final View item;
        private final TextView title, description;
        private final ImageView logo, image1, image2, image3;

        ViewHolder(View view) {
            super(view);
            item = view;
            title = (TextView) view.findViewById(R.id.project_title);
            description = (TextView) view.findViewById(R.id.project_description);
            logo = (ImageView) view.findViewById(R.id.project_logo);
            image1 = (ImageView) view.findViewById(R.id.image_1);
            image2 = (ImageView) view.findViewById(R.id.image_2);
            image3 = (ImageView) view.findViewById(R.id.image_3);
        }
    }
}
