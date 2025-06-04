package com.xiyue.animelogger;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.xiyue.animelogger.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private List<String> TodayAnimeData;

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TodayAnimeData = new ArrayList<>();
        TodayAnimeData.add("http://106.14.189.46:8080/files/get_img?Options=Carousel_chart&ImgName=img_1.jpeg");
        TodayAnimeData.add("http://106.14.189.46:8080/files/get_img?Options=Carousel_chart&ImgName=img_2.jpeg");
        TodayAnimeData.add("http://106.14.189.46:8080/files/get_img?Options=Carousel_chart&ImgName=img_3.jpeg");
        TodayAnimeData.add("http://106.14.189.46:8080/files/get_img?Options=Carousel_chart&ImgName=img_4.jpeg");

        viewPager = findViewById(R.id.TodayAnime);
        // 设置适配器
        ImageAdapter imageAdapter = n
        ew ImageAdapter(TodayAnimeData);
        viewPager.setAdapter(imageAdapter);
    }

    public void onClick(View view){
        if (view.getId() == R.id.button){
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

}