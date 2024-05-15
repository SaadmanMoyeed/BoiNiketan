package com.example.pagetrade;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.pagetrade.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ShareBuyerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView ev,mail, mail2, mail3;
    LottieAnimationView lottie;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_buyer);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        lottie= findViewById(R.id.lottie);

        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        assert firebaseUser != null;
        String email= firebaseUser.getEmail();
        ev= findViewById(R.id.subTitleTv);
        mail= findViewById(R.id.email);
        mail2= findViewById(R.id.email1);
        mail3= findViewById(R.id.email2);
        ev.setText(email);
        mail.setMovementMethod(LinkMovementMethod.getInstance());
        mail2.setMovementMethod(LinkMovementMethod.getInstance());
        mail3.setMovementMethod(LinkMovementMethod.getInstance());

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id ==R.id.nav_home){
            Intent cartIntent = new Intent(ShareBuyerActivity.this, MainBuyerActivity.class);
            startActivity(cartIntent);
        } else if(id==R.id.nav_vieworder){
            Intent viewOrderIntent = new Intent(ShareBuyerActivity.this, VieworderBuyerActivity.class);
            startActivity(viewOrderIntent);
        } else if(id==R.id.nav_profile){
            Intent viewOrderIntent = new Intent(ShareBuyerActivity.this, BuyerProfileActivity.class);
            startActivity(viewOrderIntent);
        } else if(id==R.id.nav_logout){
            FirebaseAuth.getInstance().signOut();
            Intent viewOrderIntent = new Intent(ShareBuyerActivity.this, LoginActivity.class);
            startActivity(viewOrderIntent);
        }else if(id==R.id.nav_rate){
            Intent viewOrderIntent = new Intent(ShareBuyerActivity.this, RateusBuyerActivity.class);
            startActivity(viewOrderIntent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

