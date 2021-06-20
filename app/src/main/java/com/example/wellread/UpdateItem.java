package com.example.wellread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
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
    private Status selectedStatus;

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
        toolbar.setTitle("Updating" + item.title);
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);


        // Show the details in the detail screen
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_text_detail)).setText("Author: " + mItem.author
                    + "\n" +
                    "\nRecommender: " + mItem.recommender +
                    "\n" +
                    "\nYear: " + mItem.year +
                    "\n");

            ((RadioGroup) rootView.findViewById(R.id.radio_button)).getCheckedRadioButtonId();
        }

        return rootView;
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
