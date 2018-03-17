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
    protected static final String FILTER = "filter";

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
        Button searchTasksButton = (Button) findViewById(R.id.searchtasks);
        Button profileButton = (Button) findViewById(R.id.profile);
        final Intent taskList = new Intent(getApplicationContext(),ListActivity.class);


        inProgressButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "My Tasks");
                taskList.putExtra(FILTER, "myInProgress");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        assignedTasksButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "My Tasks");
                taskList.putExtra(FILTER, "myAssigned");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        biddedTasksButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "My Tasks");
                taskList.putExtra(FILTER, "3");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        requestedBidsButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "Requested Tasks");
                taskList.putExtra(FILTER, "4");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        allRequestedButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "Requested Tasks");
                taskList.putExtra(FILTER, "5");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        searchTasksButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                taskList.putExtra(TYPE, "Search Tasks");
                taskList.putExtra(FILTER, "6");
                startActivity(taskList);
                setResult(RESULT_OK);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(),UserActivity.class);
                registerIntent.putExtra(TYPE, "My Profile");
                startActivity(registerIntent);
                setResult(RESULT_OK);
            }
        });
    }

}
