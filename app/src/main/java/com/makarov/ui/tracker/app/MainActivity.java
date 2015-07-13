package com.makarov.ui.tracker.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.makarov.ui.tracker.R;
import com.makarov.ui.tracker.library.annotations.AttachState;
import com.makarov.ui.tracker.library.annotations.LoggerView;
import com.makarov.ui.tracker.library.annotations.ViewEvent;
import com.makarov.ui.tracker.library.injector.ViewEventsInjector;
import com.makarov.ui.tracker.library.injector.ViewEventsTracker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher{

    public static final String TAG = MainActivity.class.getSimpleName();

    @LoggerView("fist button")
    public Button button;
    public Button button2;

    @LoggerView("editText - test field")
    public EditText editText;

    public Artist artist = new Artist("123", "qwe");
    public Track track = new Track("ABS");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        editText.addTextChangedListener(this);

        ViewEventsInjector.init();
        ViewEventsInjector.inject(this);

        /*

         */
    }

    @Override
    @AttachState({"artist","track"})
    @ViewEvent(ViewEventsTracker.CLICK)
    public void onClick(View v) {
        Log.d(TAG, "method onClick - " + v.getId());
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    @AttachState({"artist"})
    @ViewEvent(ViewEventsTracker.AFTER_TEXT_CHANGED)
    public void afterTextChanged(Editable s) {
        Log.d(TAG, "afterTextChanged");
    }
}
