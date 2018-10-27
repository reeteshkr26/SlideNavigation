package com.example.hp.slidenavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    NavigationView navigationview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);
        navigationview=findViewById(R.id.nav_view);
        navigationview.setNavigationItemSelectedListener(navListener);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new FragmentMessage()).commit();
            navigationview.setCheckedItem(R.id.nav_message);
        }
    }

    private NavigationView.OnNavigationItemSelectedListener navListener=new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment=null;
            switch (menuItem.getItemId()){
                case R.id.nav_message:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,new FragmentMessage()).commit();
                    break;
                case R.id.nav_chat:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,new FragmentChat()).commit();
                    break;
                case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,new FragmentProfile()).commit();
                    break;
                case R.id.nav_share:
                    Toast.makeText(MainActivity.this,"share",Toast.LENGTH_LONG).show();
                    break;
                case R.id.nav_send:
                    Toast.makeText(MainActivity.this,"send",Toast.LENGTH_LONG).show();
                    break;
                default:
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    };
    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

}
