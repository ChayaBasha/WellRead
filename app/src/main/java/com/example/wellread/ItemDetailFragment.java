package com.example.wellread;

import android.app.Activity;
import android.os.Bundle;

import com.example.wellread.model.ServiceLoadException;
import com.example.wellread.model.readingItemException;
import com.example.wellread.reading.ReadingContent;
import com.example.wellread.reading.ReadingItem;
import com.example.wellread.reading.Status;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private ReadingItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            try {
                mItem = ReadingContent.getItemMap().get(getArguments().getString(ARG_ITEM_ID));
            } catch (ServiceLoadException e) {
                e.printStackTrace();
            } catch (readingItemException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);


        // Show the details in the detail screen; this should be made nicer and I need to add the
        // radio button too
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


}