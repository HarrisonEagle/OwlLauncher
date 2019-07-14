package com.example.owllauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<ResolveInfo> pkgAppsList;
    public static List<String> appnamelist;
    public static List<Drawable> iconres;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PackageManager pm = this.getPackageManager();



        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        pkgAppsList =getApplicationContext().getPackageManager().queryIntentActivities( mainIntent, 0);
        appnamelist = new ArrayList<String>();
        iconres = new ArrayList<Drawable>();

        for(int i=0;i<pkgAppsList.size();i++){
            appnamelist.add(pkgAppsList.get(i).loadLabel(pm).toString());

        }
        for(int i=0;i<pkgAppsList.size();i++){
            iconres.add(pkgAppsList.get(i).loadIcon(pm));

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomListView listView=new CustomListView(MainActivity.this);
        listView.setTweetList(pkgAppsList);
        GridView applist=findViewById(R.id.applist);
        applist.setAdapter(listView);




    }
}
