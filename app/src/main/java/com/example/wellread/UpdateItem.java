package com.example.wellread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.wellread.model.ServiceLoadException;
import com.example.wellread.model.readingItemException;
import com.example.wellread.reading.ReadingContent;
import com.example.wellread.reading.ReadingItem;
import com.example.wellread.reading.Status;

public class UpdateItem extends AppCompatActivity {

    private Status selectedStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.update_item_toolbar);
        toolbar.setTitle("Update Reading Item");
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
                if (checked) {
                    selectedStatus = Status.OBTAIN;
                }
                break;
            case R.id.radio_to_read:
                if (checked) {
                    selectedStatus = Status.TO_READ;
                }
                break;
            case R.id.radio_read:
                if (checked) {
                    selectedStatus = Status.READ;
                }
                break;

        }
    }

    public void saveReadingItem(View view) throws ServiceLoadException, readingItemException {
        Intent intent = new Intent(this, ItemListActivity.class);
        EditText editTitle = (EditText) findViewById(R.id.editTitle);
        EditText editAuthor = (EditText) findViewById(R.id.editAuthor);
        EditText editYear = (EditText) findViewById(R.id.editYear);
        EditText editRecommender = (EditText) findViewById(R.id.editRecommender);

        ReadingItem newReadingItem = new ReadingItem(
                editTitle.getText().toString(),
                editAuthor.getText().toString(),
                editRecommender.getText().toString(),
                Integer.parseInt(editYear.getText().toString()),
                selectedStatus
        );

        ReadingContent.addReadingItem(newReadingItem);
        startActivity(intent);
    }
}
