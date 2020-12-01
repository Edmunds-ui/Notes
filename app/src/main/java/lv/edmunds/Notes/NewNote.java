package lv.edmunds.Notes;

import android.app.AlarmManager;
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

public class NewNote extends AppCompatActivity {

    Context theContext;
    DatePicker datePicker;
    EditText editTextMessage;
    TextView setTimeTV;
    Calendar calendar;
    AlarmManager alarm;
    Button changeTimeNRBtn, datePickerBtn, messageBtn, setRemindBtn;

    Note temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_reminder);
        temp = new Note();
        theContext = getApplicationContext();

        setRemindBtn = findViewById(R.id.setNoteBtn);
        calendar = Calendar.getInstance();

        //Message

        editTextMessage = findViewById(R.id.editTextMessage);
        messageBtn = findViewById(R.id.messageBtn);
        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp.setMessage( editTextMessage.getText().toString());
                messageBtn.setVisibility(View.GONE);
                editTextMessage.setVisibility(View.GONE);

                datePicker.setVisibility(View.VISIBLE);
                datePickerBtn.setVisibility(View.VISIBLE);
            }
        });

        //DatePicker

        datePicker = findViewById(R.id.datePicker);
        datePickerBtn = findViewById(R.id.datePickerBtn);
        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               temp.setAlarmDate(datePicker.getMonth() + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear());
               calendar.set(Calendar.YEAR, datePicker.getYear());
               calendar.set(Calendar.MONTH, datePicker.getMonth());
               calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());

               datePicker.setVisibility(View.GONE);
               datePickerBtn.setVisibility(View.GONE);

                editTextMessage.setVisibility(View.VISIBLE);
                setRemindBtn.setVisibility(View.VISIBLE);
                setTimeTV.setVisibility(View.VISIBLE);
                changeTimeNRBtn.setVisibility(View.VISIBLE);

                setTimeTV.setText(temp.getAlarmDate());
            }
        });


        setTimeTV = findViewById(R.id.setDatetxt);
        changeTimeNRBtn = findViewById(R.id.changeDateNRBtn);

        changeTimeNRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextMessage.setVisibility(View.INVISIBLE);
                setRemindBtn.setVisibility(View.INVISIBLE);
                setTimeTV.setVisibility(View.INVISIBLE);
                changeTimeNRBtn.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.VISIBLE);
                datePickerBtn.setVisibility(View.VISIBLE);
            }
        });

        editTextMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                temp.setMessage(editTextMessage.getText().toString());
            }
        });


        setRemindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllNotes.getInstance().addToArray(temp);
                AllNotes.getInstance().saveToInternalStorage(theContext);
                Intent intent = new Intent(theContext, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
