package com.nav.wedasiemariam;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    /* access modifiers changed from: private */
    public Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        initialFragment();
        this.toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(this.toolbar);
        this.navigationView = (NavigationView) findViewById(R.id.navigation_view);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.drawerLayout, this.toolbar, R.string.open, R.string.close);
        this.drawerLayout.addDrawerListener(this.actionBarDrawerToggle);
        this.actionBarDrawerToggle.syncState();
        navigationListener();
    }

    /* access modifiers changed from: private */
    public void setUpFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void onBackPressed() {
        DrawerLayout drawerLayout2 = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout2.isDrawerOpen((int) GravityCompat.START)) {
            drawerLayout2.closeDrawer((int) GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void initialFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TselotZezewter()).commit();
    }

    public void navigationListener() {
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.anketse_berhan) {
                    AnketseBerhan anketseBerhan = new AnketseBerhan();
                    MainActivity.this.toolbar.setTitle((int) R.string.anketse_berhan_name);
                    MainActivity.this.setUpFragment(anketseBerhan);
                } else if (itemId == R.id.tselot_zezewter) {
                    TselotZezewter tselotZezewter = new TselotZezewter();
                    MainActivity.this.toolbar.setTitle((int) R.string.tselot_zezewter_name);
                    MainActivity.this.setUpFragment(tselotZezewter);
                } else if (itemId != R.id.yewedeswa_melakt) {
                    switch (itemId) {
                        case R.id.melkea_eyesus /*2131230874*/:
                            MelkeaEyesus melkeaEyesus = new MelkeaEyesus();
                            MainActivity.this.toolbar.setTitle((int) R.string.melkea_eyesus_name);
                            MainActivity.this.setUpFragment(melkeaEyesus);
                            break;
                        case R.id.melkea_mariam /*2131230875*/:
                            MelkeaMariam melkeaMariam = new MelkeaMariam();
                            MainActivity.this.toolbar.setTitle((int) R.string.melkea_mariam_name);
                            MainActivity.this.setUpFragment(melkeaMariam);
                            break;
                        default:
                            switch (itemId) {
                                case R.id.the_friday /*2131231001*/:
                                    TheFriday theFriday = new TheFriday();
                                    MainActivity.this.toolbar.setTitle((int) R.string.the_friday_name);
                                    MainActivity.this.setUpFragment(theFriday);
                                    break;
                                case R.id.the_monday /*2131231002*/:
                                    TheMonday theMonday = new TheMonday();
                                    MainActivity.this.toolbar.setTitle((int) R.string.the_monday_name);
                                    MainActivity.this.setUpFragment(theMonday);
                                    break;
                                case R.id.the_saturday /*2131231003*/:
                                    TheSaturday theSaturday = new TheSaturday();
                                    MainActivity.this.toolbar.setTitle((int) R.string.the_saturday_name);
                                    MainActivity.this.setUpFragment(theSaturday);
                                    break;
                                case R.id.the_sunday /*2131231004*/:
                                    TheSunday theSunday = new TheSunday();
                                    MainActivity.this.toolbar.setTitle((int) R.string.the_sunday_name);
                                    MainActivity.this.setUpFragment(theSunday);
                                    break;
                                case R.id.the_thursday /*2131231005*/:
                                    TheThursday theThursday = new TheThursday();
                                    MainActivity.this.toolbar.setTitle((int) R.string.the_thursday_name);
                                    MainActivity.this.setUpFragment(theThursday);
                                    break;
                                case R.id.the_tuesday /*2131231006*/:
                                    TheTuesday theTuesday = new TheTuesday();
                                    MainActivity.this.toolbar.setTitle((int) R.string.the_tuesday_name);
                                    MainActivity.this.setUpFragment(theTuesday);
                                    break;
                                case R.id.the_wednesday /*2131231007*/:
                                    TheWednesday theWednesday = new TheWednesday();
                                    MainActivity.this.toolbar.setTitle((int) R.string.the_wednesday_name);
                                    MainActivity.this.setUpFragment(theWednesday);
                                    break;
                            }
                    }
                } else {
                    YewedeswaMelakt yewedeswaMelakt = new YewedeswaMelakt();
                    MainActivity.this.toolbar.setTitle((int) R.string.yewedsewa_melaket_name);
                    MainActivity.this.setUpFragment(yewedeswaMelakt);
                }
                final DrawerLayout drawerLayout = (DrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        drawerLayout.closeDrawer((int) GravityCompat.START);
                    }
                }, 100);
                return true;
            }
        });
    }
}
