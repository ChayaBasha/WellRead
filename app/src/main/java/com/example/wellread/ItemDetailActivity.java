package com.example.wellread;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import com.example.wellread.model.ServiceLoadException;
import com.example.wellread.model.readingItemException;
import com.example.wellread.reading.ReadingContent;
import com.example.wellread.reading.ReadingItem;
import com.example.wellread.reading.Status;

public class ItemDetailActivity extends AppCompatActivity {

    private ReadingItem item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        String item_id = getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID);

        try {
            item = ReadingContent.getItemMap().get(item_id);
        } catch (ServiceLoadException e) {
            e.printStackTrace();
        } catch (readingItemException e) {
            e.printStackTrace();
        }
        toolbar.setTitle(item.title);
        toolbar.setLogo(R.drawable.ic_launcher_book_foreground);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void goBack(MenuItem action_back) {
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivity(intent);
    }

    public void deleteReadingItem(MenuItem delte_Item) throws ServiceLoadException, readingItemException {
        Intent intent = new Intent(this, ItemListActivity.class);
        ReadingItem readingItem = item;
        ReadingContent.deleteReadingItem(readingItem);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        String item_id = getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID);
        ReadingItem item = null;
        try {
            item = ReadingContent.getItemMap().get(item_id);
        } catch (ServiceLoadException e) {
            e.printStackTrace();
        } catch (readingItemException e) {
            e.printStackTrace();
        }

        if (item.status == Status.READ) {
            RadioButton radioRead = (RadioButton) findViewById(R.id.radio_read);
            radioRead.setChecked(true);
        } else if (item.status == Status.TO_READ) {
            RadioButton radioToRead = (RadioButton) findViewById(R.id.radio_to_read);
            radioToRead.setChecked(true);
        } else {
            RadioButton radioObtain = (RadioButton) findViewById(R.id.radio_obtain);
            radioObtain.setChecked(true);
        }
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