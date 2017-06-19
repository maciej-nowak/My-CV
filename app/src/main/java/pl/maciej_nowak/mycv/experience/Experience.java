package pl.maciej_nowak.mycv.experience;

/**
 * Created by Maciej on 19.06.2017.
 */

public class Experience {

    private String title, subtitle, date, extras;
    private int image;

    public Experience(String title, String subtitle, String date, String extras, int image) {
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
        this.extras = extras;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDate() {
        return date;
    }

    public String getExtras() {
        return extras;
    }

    public int getImage() {
        return image;
    }
}
