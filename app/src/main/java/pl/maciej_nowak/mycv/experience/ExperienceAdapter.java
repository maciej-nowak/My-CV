package pl.maciej_nowak.mycv.experience;

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

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ViewHolder> {

    private Context context;
    private List<Experience> list;

    public ExperienceAdapter(Context context) {
        this.context = context;
        this.list = setDefaultData();
    }

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

    private List<Experience> setDefaultData() {
        List<Experience> list = new ArrayList<>();

        list.add(new Experience("Yanosik.pl",
                "Android Developer",
                "2017.03 - present",
                "Poznań",
                R.drawable.logo_yanosik));

        list.add(new Experience("Sealcode.org",
                "Member",
                "2016.12 - present",
                "Poznań",
                R.drawable.logo_sealcode));

        list.add(new Experience("Adam Mickiewicz University",
                "Computer Science",
                "2016.02 - 2017.06",
                "Master thesis: Measuring and evaluation of productivity team development",
                R.drawable.logo_uam));

        list.add(new Experience("Poleng",
                "Software Tester",
                "2014.04 - 2014.05",
                "Poznań",
                R.drawable.logo_poleng));

        list.add(new Experience("Adam Mickiewicz University",
                "Computer Science, specialization: Algorithms and software engineering",
                "2012.10 - 2016.02",
                "Engineering thesis: Creating and designing mobile application for Android platform",
                R.drawable.logo_uam));

        list.add(new Experience("Novikov Restaurant",
                "Runner",
                "2012.05 - 2012.08",
                "London",
                R.drawable.logo_novikov));

        list.add(new Experience("Opti-Bit",
                "Computer Service Technician",
                "2011.03 - 2011.03",
                "Chodzież",
                R.drawable.logo_optibit));

        list.add(new Experience("Józef Wybicki Vocational Technical High School",
                "Computer Science",
                "2008.09 - 2012.05",
                "",
                R.drawable.logo_zslg));

        return list;
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
