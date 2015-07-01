package com.example.krishnakhandagale.androidapplicationlist;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListOfApplicationsActivity extends AppCompatActivity {
    String TAG="TAG";
    ListView listView;
    EditText searchtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_applications);
        listView = (ListView) findViewById(R.id.app_list);
        searchtext= (EditText) findViewById(R.id.searchBar);
        List<String> applist= new ArrayList<>();
        PackageManager packageManager= getPackageManager();
        List<ApplicationInfo> listOfApps= packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo appInfo : listOfApps) {
            Log.d(TAG, "Installed package :" + appInfo.packageName);
            Log.d(TAG,"App name"+appInfo.loadLabel(packageManager));
            applist.add(appInfo.loadLabel(packageManager).toString());
            Log.d(TAG, "Source dir : " + appInfo.sourceDir);
            Log.d(TAG, "Launch Activity :" + packageManager.getLaunchIntentForPackage(appInfo.packageName));
        }

        final ArrayAdapter<String> adapter =new ArrayAdapter<String>(ListOfApplicationsActivity.this,android.R.layout.simple_list_item_1,applist);
        listView.setAdapter(adapter);

        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }


}
