package com.cmput301w18t26.taskit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.Set;

/**
 * Created by kevingordon on 2018-02-26.
 */

public class TaskActivity extends AppCompatActivity {

    protected static final String TYPE = "type";

    private TextView titleText;
    private TextView dateText;
    private TextView statusText;
    private TextView descriptionText;
    private TextView ownerText;
    private TextView locationText;
    private EditText editTitleText;
    private EditText editDescText;
    private TaskItData db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        String type = intent.getStringExtra(HomeActivity.TYPE);
        db = TaskItData.getInstance();
        if (type.equals("New Task")) {
            setContentView(R.layout.edittask);
            Button createTaskButton = (Button) findViewById(R.id.createtask);
            createTaskButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    setTaskDetails();
                    Intent createTaskIntent = new Intent(getApplicationContext(),ListActivity.class);
                    createTaskIntent.putExtra(TYPE, "Requested Tasks");
                    startActivity(createTaskIntent);
                    setResult(RESULT_OK);
                }
            });
        } else {
            setContentView(R.layout.viewtask);
            final Task task = db.getTask(intent.getStringExtra("UUID"));
            getTaskDetails(task);

            Button editTaskButton = (Button) findViewById(R.id.edittask);
            editTaskButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v){
                    editTaskDetails(task);
                }
            });

            Button viewBids = (Button) findViewById(R.id.viewBids);
            final Intent bidList = new Intent(getApplicationContext(),BidListActivity.class);
            viewBids.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v){
                    Intent intent = new Intent(TaskActivity.this, BidListActivity.class);
                    String UUID = task.getUUID();
                    intent.putExtra("UUID", UUID);
                    startActivity(intent);
                    setResult(RESULT_OK);
                }
            });

            Button addBidButton = (Button) findViewById(R.id.bidTask);
            final Intent bidActivity = new Intent(getApplicationContext(), BidActivity.class);
            addBidButton.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v){
                    Intent intent = new Intent(TaskActivity.this, BidActivity.class);
                    String UUID = task.getUUID();
                    intent.putExtra("UUID", UUID);
                    startActivity(intent);
                    setResult(RESULT_OK);
                }

            });

        }
        setTitle(type);
    }

    private void setTaskDetails() {
        titleText = (TextView) findViewById(R.id.update_title);
        descriptionText = (TextView) findViewById(R.id.update_description);

        Task t = new Task();
        t.setTitle(titleText.getText().toString());
        t.setDescription(descriptionText.getText().toString());
        t.setDate(new Date());
        t.setLocation("Unknown");
        t.setStatus("Requested");
        t.setOwner(db.getCurrentUser().getOwner());
        db.addTask(t);
    }

    private void getTaskDetails(Task task) {

        titleText = (TextView) findViewById(R.id.tasktitle);
        descriptionText = (TextView) findViewById(R.id.taskdescription);
        statusText = (TextView) findViewById(R.id.taskstatus);
        locationText = (TextView) findViewById(R.id.tasklocation);
        ownerText = (TextView) findViewById(R.id.taskowner);
        dateText = (TextView) findViewById(R.id.taskdate);

        titleText.setText(task.getTitle());
        descriptionText.setText(task.getDescription());
        statusText.setText(task.getStatus());
        locationText.setText(task.getLocation());
        ownerText.setText(task.getOwner());
        dateText.setText(task.getDateString());

    }

    private void editTaskDetails (final Task task) {
        setContentView(R.layout.add_modify_task);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String status = task.getStatus();

        // Sets the dropdown menu, puts default position as the current task status
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Task.changeableStatuses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        int spinnerpos = adapter.getPosition(status);
        spinner.setAdapter(adapter);
        spinner.setSelection(spinnerpos);

        editTitleText = (EditText) findViewById(R.id.editTitle);
        editDescText = (EditText) findViewById(R.id.editDescription);


        editTitleText.setText(task.getTitle(),TextView.BufferType.EDITABLE);
        editDescText.setText(task.getDescription(),TextView.BufferType.EDITABLE);


        Button confirmEdits = (Button) findViewById(R.id.confirmedit);
        confirmEdits.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                modifyDetails (task,editTitleText,editDescText,spinner);
            }
        });

    }

    private void modifyDetails (Task task, EditText title,EditText desc, Spinner spinner){
        String newstatus = spinner.getSelectedItem().toString();
        String editedTitle = title.getText().toString();
        String editedDesc = desc.getText().toString();

        task.setTitle(editedTitle);
        task.setDescription(editedDesc);
        task.setStatus(newstatus);

        db.updateTask(task);

        setContentView(R.layout.viewtask);
        getTaskDetails(task);
    }
}
