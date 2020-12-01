package lv.edmunds.Notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditNote extends AppCompatActivity {

    int position;
    Context theContext;
    EditText noteText;
    TextView noteActivateTime;
    DatePicker changeDatePicker;
    Calendar calendar;
    Button changeDateBtn, saveChanges, confirmDateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reminder);
        Intent i = getIntent();
        theContext = getApplicationContext();
        position = (int)i.getSerializableExtra("position");
        calendar = Calendar.getInstance();

        noteText = findViewById(R.id.noteText);
        noteActivateTime = findViewById(R.id.noteDatetxt);
        changeDateBtn = findViewById(R.id.changeDateBtn);
        saveChanges = findViewById(R.id.saveChanges);
        changeDatePicker = findViewById(R.id.changeDatePicker);
        confirmDateBtn = findViewById(R.id.confirmDateBtn);

        noteText.setText(AllNotes.getInstance().getArray().get(position).getMessage());
        noteActivateTime.setText(AllNotes.getInstance().getArray().get(position).getAlarmDate());

        //Saglabajam izmaiņas Note tekstā
        noteText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                AllNotes.getInstance().getArray().get(position).setMessage(noteText.getText().toString());
            }
        });


        changeDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteText.setVisibility(View.INVISIBLE);
                noteActivateTime.setVisibility(View.INVISIBLE);
                changeDateBtn.setVisibility(View.INVISIBLE);
                saveChanges.setVisibility(View.INVISIBLE);

                changeDatePicker.setVisibility(View.VISIBLE);
                confirmDateBtn.setVisibility(View.VISIBLE);
            }
        });

        confirmDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllNotes.getInstance().getArray().get(position).setAlarmDate(changeDatePicker.getMonth() + "/" + changeDatePicker.getDayOfMonth() + "/" + changeDatePicker.getYear());
                calendar.set(Calendar.YEAR, changeDatePicker.getYear());
                calendar.set(Calendar.MONTH, changeDatePicker.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, changeDatePicker.getDayOfMonth());
                changeDatePicker.setVisibility(View.GONE);
                confirmDateBtn.setVisibility(View.GONE);

                noteActivateTime.setText(AllNotes.getInstance().getArray().get(position).getAlarmDate());
                noteText.setVisibility(View.VISIBLE);
                noteActivateTime.setVisibility(View.VISIBLE);
                changeDateBtn.setVisibility(View.VISIBLE);
                saveChanges.setVisibility(View.VISIBLE);

            }
        });


        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllNotes.getInstance().saveToInternalStorage(theContext);
                Intent intent = new Intent(theContext, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
