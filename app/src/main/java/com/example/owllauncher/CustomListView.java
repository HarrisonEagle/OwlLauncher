package com.example.owllauncher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CustomListView extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater = null;
    List<ResolveInfo> pkgAppList;

    public CustomListView(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setTweetList(List<ResolveInfo> pkgAppList) {
        this.pkgAppList = pkgAppList;
    }

    @Override
    public int getCount() {
        return pkgAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return pkgAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.app_list,parent,false);

        String appname=MainActivity.appnamelist.get(position);
        if(appname.length()>4){
            appname=appname.substring(0,4)+"..";
        }

        ((TextView)convertView.findViewById(R.id.appname)).setText(appname);
        ((ImageView)convertView.findViewById(R.id.appicon)).setImageDrawable(MainActivity.iconres.get(position));
        ((LinearLayout)convertView.findViewById(R.id.openapp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityInfo activity=MainActivity.pkgAppsList.get(position).activityInfo;
                ComponentName name=new ComponentName(activity.applicationInfo.packageName,
                        activity.name);
                Intent i=new Intent(Intent.ACTION_MAIN);

                i.addCategory(Intent.CATEGORY_LAUNCHER);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                i.setComponent(name);

                view.getContext().startActivity(i);
            }
        });


        return convertView;
    }

}
