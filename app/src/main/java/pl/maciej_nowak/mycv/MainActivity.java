package pl.maciej_nowak.mycv;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import pl.maciej_nowak.mycv.about.AboutFragment;
import pl.maciej_nowak.mycv.experience.ExperienceFragment;
import pl.maciej_nowak.mycv.project.ProjectFragment;

public class MainActivity extends AppCompatActivity {

    private final long BACK_PRESSED_INTERVAL = 2000;
    private long backPressedTime;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_about:
                    replaceFragment(AboutFragment.newInstance(), AboutFragment.TAG);
                    return true;
                case R.id.navigation_experience:
                    replaceFragment(ExperienceFragment.newInstance(), ExperienceFragment.TAG);
                    return true;
                case R.id.navigation_projects:
                    replaceFragment(ProjectFragment.newInstance(), ProjectFragment.TAG);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(0);
        replaceFragment(AboutFragment.newInstance(), AboutFragment.TAG);
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + BACK_PRESSED_INTERVAL > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), R.string.press_back_again, Toast.LENGTH_SHORT).show();
        backPressedTime = System.currentTimeMillis();
    }

    private void replaceFragment(Fragment fragment, String TAG) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment, TAG)
                .commit();
    }

}
