package com.cmput301w18t26.taskit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kevingordon on 2018-02-26.
 */

public class HomeActivity extends AppCompatActivity {

    protected static final String TYPE = "type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setTitle("Home");

        Button inProgressButton = (Button) findViewById(R.id.inprogresstasks);
        Button assignedTasksButton = (Button) findViewById(R.id.assignedtasks);
        Button biddedTasksButton = (Button) findViewById(R.id.biddedtasks);
        Button requestedBidsButton = (Button) findViewById(R.id.requestedbidstasks);
        Button allRequestedButton = (Button) findViewById(R.id.allrequestedtasks);
        Button profileButton = (Button) findViewById(R.id.profile);
        final Intent taskList = new Intent(getApplicationContext(),ListActivity.class);


        inProgressButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "inProgress");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        assignedTasksButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "assignedTask");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        biddedTasksButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "biddedTasks");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        requestedBidsButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "requestedBids");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        allRequestedButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "allRequest");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(),UserActivity.class);
                registerIntent.putExtra(TYPE, "view_self");
                startActivity(registerIntent);
                setResult(RESULT_OK);
            }
        });
    }

}