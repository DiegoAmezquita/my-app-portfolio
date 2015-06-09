package com.diegoamezquita.portfolio;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.diegoamezquita.portfolio.adapters.HomeAdapter;
import com.diegoamezquita.portfolio.models.App;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements HomeAdapter.HomeItemListener {

    private ArrayList<App> arrayApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.action_bar_title);

        loadData();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        HomeAdapter homeAdapter = new HomeAdapter(arrayApps);
        homeAdapter.setHomeItemListener(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(homeAdapter);
    }

    private void loadData() {
        arrayApps = new ArrayList<>();

        String[] names = getResources().getStringArray(R.array.names);
        TypedArray icons = getResources().obtainTypedArray(R.array.icons);

        if (names.length != icons.length()) {
            icons.recycle();
            return;
        }

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int icon = icons.getResourceId(i, -1);
            arrayApps.add(new App(name, icon));
        }

        icons.recycle();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            showAlertAbout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlertAbout() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.action_about)
                .setMessage(R.string.message_about)
                .setNeutralButton(R.string.close,null)
                .show();
    }

    @Override
    public void onHomeItemClicked(App appClicked) {
        String message = getResources().getString(R.string.message_home_item_clicked, appClicked.getName());
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
