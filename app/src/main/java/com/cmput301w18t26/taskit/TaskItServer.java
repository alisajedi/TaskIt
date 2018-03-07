package com.cmput301w18t26.taskit;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;

import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.PutMapping;

/**
 * Created by kevingordon on 2018-03-05.
 */

public class TaskItServer {

    public static JestDroidClient client;
    private static String INDEX_TaskItMain = "cmput301w18t26_main";
    private static String TYPE_USER = "user";
    private static String TYPE_TASK = "task";
    private static String TYPE_BID = "bid";

    public static class setupServerJob extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            verifySettings();
            try {
                // Create the indexx
                JestResult result = client.execute(new CreateIndex.Builder(INDEX_TaskItMain).build());
                if (result.isSucceeded()) {

                } else {
                    Log.d("TaskItServer", "Create Index Not Success "+result.getErrorMessage());
                }
            }
            catch (Exception e) {
                Log.d("TaskItServer", "Failed setup Server");
                e.printStackTrace();
            }

            return null;
        }
    }

    public static class teardownServerJob extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            verifySettings();
            try {
                // Create the indexx
                JestResult result = client.execute(new DeleteIndex.Builder(INDEX_TaskItMain).build());
                if (result.isSucceeded()) {

                } else {
                    Log.d("TaskItServer", "Delete Index Not Success "+result.getErrorMessage());
                }
            }
            catch (Exception e) {
                Log.d("TaskItServer", "Failed teardown Server");
                e.printStackTrace();
            }

            return null;
        }
    }

    public static class addUserJob extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            for (User user : users) {
                Index index = new Index.Builder(user).index(INDEX_TaskItMain).type(TYPE_USER).build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.d("TaskItServer", String.valueOf(result.getId())+result.getJsonString());
//                        tweet.setId(result.getId());
                    } else {
                        Log.d("TaskItServer", "add user not succeed "+result.getErrorMessage());
                    }

                }
                catch (Exception e) {
                    Log.d("TaskItServer", "Exception during add user");
                    e.printStackTrace();
                }

            }
            return null;
        }
    }

    public static class addTaskJob extends AsyncTask<Task, Void, Void> {

        @Override
        protected Void doInBackground(Task... tasks) {
            verifySettings();

            for (Task task : tasks) {
                Index index = new Index.Builder(task).index(INDEX_TaskItMain).type(TYPE_TASK).build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.d("TaskItServer", String.valueOf(result.getId())+result.getJsonString());
//                        tweet.setId(result.getId());
                    } else {
                        Log.d("TaskItServer", "add task not succeed "+result.getErrorMessage());
                    }

                }
                catch (Exception e) {
                    Log.d("TaskItServer", "Exception during add task");
                    e.printStackTrace();
                }

            }
            return null;
        }
    }

    public static class addBidJob extends AsyncTask<Bid, Void, Void> {

        @Override
        protected Void doInBackground(Bid... bids) {
            verifySettings();

            for (Bid bid : bids) {
                Index index = new Index.Builder(bid).index(INDEX_TaskItMain).type(TYPE_BID).build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.d("TaskItServer", String.valueOf(result.getId())+result.getJsonString());
//                        tweet.setId(result.getId());
                    } else {
                        Log.d("TaskItServer", "add task not succeed "+result.getErrorMessage());
                    }

                }
                catch (Exception e) {
                    Log.d("TaskItServer", "Exception during add task");
                    e.printStackTrace();
                }

            }
            return null;
        }
    }

    public static class getUserJob extends AsyncTask<String, Void, UserList> {
        @Override
        protected UserList doInBackground(String... search_parameters) {
            verifySettings();

            UserList users = new UserList();

            // TODO Build the query
            Log.d("TaskItServer", search_parameters[0]);
            Search search = new Search.Builder(search_parameters[0]).addIndex(INDEX_TaskItMain).addType(TYPE_USER).build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<User> foundUser = result.getSourceAsObjectList(User.class);
                    users.addAll(foundUser);
                } else {
                    Log.d("TaskItServer", "Something bad in get User");
                }
            }
            catch (Exception e) {
                Log.d("TaskItServer", "Something went wrong when we tried to communicate with the elasticsearch server!");
                e.printStackTrace();
            }

            return users;
        }
    }

    public static class getTaskJob extends AsyncTask<String, Void, TaskList> {
        @Override
        protected TaskList doInBackground(String... search_parameters) {
            verifySettings();

            TaskList tasks = new TaskList();

            Search search = new Search.Builder(search_parameters[0]).addIndex(INDEX_TaskItMain).addType(TYPE_TASK).build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Task> foundTask = result.getSourceAsObjectList(Task.class);
                    tasks.addAll(foundTask);
                } else {
                    Log.d("TaskItServer", "Something bad in get Task");
                }
            }
            catch (Exception e) {
                Log.d("TaskItServer", "Something went wrong when we tried to communicate with the elasticsearch server!");
                e.printStackTrace();
            }

            return tasks;
        }
    }

    public static class getBidJob extends AsyncTask<String, Void, BidList> {
        @Override
        protected BidList doInBackground(String... search_parameters) {
            verifySettings();

            BidList bids = new BidList();

            Search search = new Search.Builder(search_parameters[0]).addIndex(INDEX_TaskItMain).addType(TYPE_BID).build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Bid> foundBid = result.getSourceAsObjectList(Bid.class);
                    bids.addAll(foundBid);
                } else {
                    Log.d("TaskItServer", "Something bad in get Task");
                }
            }
            catch (Exception e) {
                Log.d("TaskItServer", "Something went wrong when we tried to communicate with the elasticsearch server!");
                e.printStackTrace();
            }

            return bids;
        }
    }

    public static class deleteUserJob extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            for (User user : users) {
                Delete index = new Delete.Builder(user.getUUID())
                                    .index(INDEX_TaskItMain)
                                    .type(TYPE_USER)
                                    .build();
                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.d("TaskItServer", String.valueOf(result.getId())+result.getJsonString());
//                        tweet.setId(result.getId());
                    } else {
                        Log.d("TaskItServer", "delete user not succeed "+result.getErrorMessage());
                    }

                }
                catch (Exception e) {
                    Log.d("TaskItServer", "Exception during delete user");
                    e.printStackTrace();
                }

            }
            return null;
        }
    }

    public static class deleteTaskJob extends AsyncTask<Task, Void, Void> {

        @Override
        protected Void doInBackground(Task... tasks) {
            verifySettings();

            for (Task task : tasks) {
                Delete index = new Delete.Builder(task.getUUID())
                        .index(INDEX_TaskItMain)
                        .type(TYPE_TASK)
                        .build();
                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.d("TaskItServer", String.valueOf(result.getId())+result.getJsonString());
//                        tweet.setId(result.getId());
                    } else {
                        Log.d("TaskItServer", "delete task not succeed "+result.getErrorMessage());
                    }

                }
                catch (Exception e) {
                    Log.d("TaskItServer", "Exception during delete task");
                    e.printStackTrace();
                }

            }
            return null;
        }
    }

    public static class deleteBidJob extends AsyncTask<Bid, Void, Void> {

        @Override
        protected Void doInBackground(Bid... bids) {
            verifySettings();

            for (Bid bid : bids) {
                Delete index = new Delete.Builder(bid.getUUID())
                        .index(INDEX_TaskItMain)
                        .type(TYPE_BID)
                        .build();
                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.d("TaskItServer", String.valueOf(result.getId())+result.getJsonString());
//                        tweet.setId(result.getId());
                    } else {
                        Log.d("TaskItServer", "delete bid not succeed "+result.getErrorMessage());
                    }

                }
                catch (Exception e) {
                    Log.d("TaskItServer", "Exception during delete bid");
                    e.printStackTrace();
                }

            }
            return null;
        }
    }

    // TODO implement delete jobs + methods

    public void loadAllFromServer(UserList u, TaskList t, BidList b) {
        TaskItServer.getUserJob getUser = new TaskItServer.getUserJob();
        TaskItServer.getTaskJob getTask = new TaskItServer.getTaskJob();
        TaskItServer.getBidJob getBid = new TaskItServer.getBidJob();

        getUser.execute("");
        getTask.execute("");
        getBid.execute("");

        try {
            u = getUser.get();
            t = getTask.get();
            b = getBid.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }



    public void addUser(User user) {
        TaskItServer.addUserJob addUser = new TaskItServer.addUserJob();
        addUser.execute(user);
    }

    public void addTask(Task task) {
        TaskItServer.addTaskJob addTask = new TaskItServer.addTaskJob();
        addTask.execute(task);
    }

    public void addBid(Bid bid) {
        TaskItServer.addBidJob addBid = new TaskItServer.addBidJob();
        addBid.execute(bid);
    }


    public void delUser(User user) {
        TaskItServer.deleteUserJob delUser = new TaskItServer.deleteUserJob();
        delUser.execute(user);
    }

    public void delTask(Task task) {
        TaskItServer.deleteTaskJob delTask = new TaskItServer.deleteTaskJob();
        delTask.execute(task);
    }

    public void delBid(Bid bid) {
        TaskItServer.deleteBidJob delBid = new TaskItServer.deleteBidJob();
        delBid.execute(bid);
    }

    public UserList getUsers() {
        UserList l = new UserList();
        TaskItServer.getUserJob getUser = new TaskItServer.getUserJob();
        getUser.execute("");
        try {
            l = getUser.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public TaskList getTasks() {
        TaskList l = new TaskList();
        TaskItServer.getTaskJob getTask = new TaskItServer.getTaskJob();
        getTask.execute("");
        try {
            l = getTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public BidList getBids() {
        BidList l = new BidList();
        TaskItServer.getBidJob getBid = new TaskItServer.getBidJob();
        getBid.execute("");
        try {
            l = getBid.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }


}