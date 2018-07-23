package com.example.attendance.View;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.attendance.Fragment.HomeFragment;
import com.example.attendance.Fragment.ProfileFragment;
import com.example.attendance.Fragment.QRFragment;
import com.example.attendance.Fragment.ScheduleFragment;
import com.example.attendance.R;

public class Home extends AppCompatActivity {

    private BottomNavigationView MainNav;
    private FrameLayout MainFrame;
    private HomeFragment homeFragment;
    private QRFragment qrFragment;
    private ScheduleFragment scheduleFragment;
    private ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MainFrame = (FrameLayout)findViewById(R.id.main_frame);
        MainNav = (BottomNavigationView)findViewById(R.id.main_nav);
        homeFragment = new HomeFragment();
        scheduleFragment = new ScheduleFragment();
        profileFragment = new ProfileFragment();
        qrFragment = new QRFragment();

                setFragment(homeFragment);

        MainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        setTitle("Home");
                        return true;

                    case R.id.nav_profile:
                        setFragment(profileFragment);
                        setTitle("Profile");
                        return true;

                    case R.id.nav_qr:
                        setFragment(qrFragment);
                        setTitle("Scan QR code");
                        return true;

                    case R.id.nav_schedule:
                        setFragment(scheduleFragment);
                        setTitle("Schedule");
                        return true;

                        default:
                            return false;
                }
            }
        });
    }

    private void setFragment(android.support.v4.app.Fragment fragment){

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}


