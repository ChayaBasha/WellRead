package com.example.wellread;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import androidx.appcompat.widget.Toolbar;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.add_item_toolbar);
        toolbar.setTitle("Add Reading Item");
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

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_obtain:
                if (checked)
                    break;
            case R.id.radio_to_read:
                if (checked)
                    break;
            case R.id.radio_read:
                if (checked)
                    break;

        }
    }
}
