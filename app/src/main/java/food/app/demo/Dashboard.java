package food.app.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager2;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tabLayout = findViewById(R.id.tabLayoutDashBoardID);
        viewPager2 = findViewById(R.id.viewPager);


        //linking drawer items
        drawerLayout = findViewById(R.id.drawer_layoutID);
        navigationView = findViewById(R.id.navigationViewID);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.menu_open, R.string.close_munu);

        //configuring drawer
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_about:
                        Intent intent1 = new Intent(Dashboard.this, AboutActivity.class);
                        startActivity(intent1);

                        break;
                    case R.id.nav_history:
                        Intent inten = new Intent(Dashboard.this, GetStartedActivity.class);
                        startActivity(inten);
                        break;
                    case R.id.nav_Address:
                        Intent intent2 = new Intent(Dashboard.this, LoginActivity.class);
                        startActivity(intent2);

                        break;

                    case R.id.nav_chat:
                        Intent inta = new Intent(Dashboard.this, MainBusinessActivity.class);
                        startActivity(inta);

                        break;

                }

                return true;
            }
        });

        final RestaurantAdapterFragment adapter1 = new RestaurantAdapterFragment(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager2.setAdapter(adapter1);

        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}

