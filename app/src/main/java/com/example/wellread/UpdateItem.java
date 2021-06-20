package com.example.wellread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.wellread.model.ServiceLoadException;
import com.example.wellread.model.readingItemException;
import com.example.wellread.reading.ReadingContent;
import com.example.wellread.reading.ReadingItem;
import com.example.wellread.reading.Status;

public class UpdateItem extends AppCompatActivity {

    private ReadingItem item = null;
    private Status selectedStatus = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.update_item_toolbar);
        String item_id = getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID);

        try {
            item = ReadingContent.getItemMap().get(item_id);
        } catch (ServiceLoadException e) {
            e.printStackTrace();
        } catch (readingItemException e) {
            e.printStackTrace();
        }
        toolbar.setTitle("Updating " + item.title);
        toolbar.setLogo(R.drawable.ic_launcher_book_foreground);
        setSupportActionBar(toolbar);

        EditText editTitle = (EditText) findViewById(R.id.editTitle);
        editTitle.setText(item.title);
        EditText editAuthor = (EditText) findViewById(R.id.editAuthor);
        editAuthor.setText(item.author);
        EditText editRecommender = (EditText) findViewById(R.id.editRecommender);
        editRecommender.setText(item.recommender);
        EditText editYear = (EditText) findViewById(R.id.editYear);
        editYear.setText(item.year.toString());
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

    public void updateReadingItem(View view) throws ServiceLoadException, readingItemException {
        Intent intent = new Intent(this, ItemListActivity.class);
        EditText editTitle = (EditText) findViewById(R.id.editTitle);
        EditText editAuthor = (EditText) findViewById(R.id.editAuthor);
        EditText editYear = (EditText) findViewById(R.id.editYear);
        EditText editRecommender = (EditText) findViewById(R.id.editRecommender);

        ReadingContent.updateReadingItem(
                item.id,
                editTitle.getText().toString(),
                editAuthor.getText().toString(),
                editRecommender.getText().toString(),
                Integer.parseInt(editYear.getText().toString()),
                selectedStatus);

        startActivity(intent);
    }
}
