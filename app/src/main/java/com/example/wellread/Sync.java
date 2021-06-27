package com.example.wellread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Sync extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sync_toolbar);
        toolbar.setTitle("Sync " + getTitle() + " Account");
        toolbar.setLogo(R.drawable.ic_launcher_book_foreground);
        setSupportActionBar(toolbar);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_item_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void backToList(MenuItem action_back) {
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivity(intent);
    }
}