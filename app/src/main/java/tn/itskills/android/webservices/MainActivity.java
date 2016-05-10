package tn.itskills.android.webservices;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import tn.itskills.android.webservices.adapters.EmployeeContentAdapter;
import tn.itskills.android.webservices.listeners.RecyclerItemClickListener;
import tn.itskills.android.webservices.models.EmployeeWrapper;
import tn.itskills.android.webservices.services.Config;
import tn.itskills.android.webservices.services.RequestHandler;
import tn.itskills.android.webservices.utils.EmployeeContent;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EmployeeContentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<EmployeeWrapper> mEmployees;


    private String JSON_STRING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEmployeeActivity.class);
                intent.putExtra("from","add");
                startActivity(intent);
            }
        });

        initEmployees();
        initView();
        initRecyclerView();
        getJSON();
    }

    private void initEmployees() {
        //mEmployees = EmployeeContent.getEmployees();
    }

    private void initView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void initRecyclerView() {


        mLayoutManager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Log.e("adnen", "onItemClick position ="+position);

                        EmployeeWrapper employee = mEmployees.get(position);

                        Intent intent = new Intent(getApplicationContext(), ProfileEmployeeActivity.class);
                        intent.putExtra("id",Integer.toString(employee.getId()));
                        Log.e("adnen", "MainActivity : id ="+employee.getId());

                        startActivity(intent);

                    }
                })
        );

        // Set padding for Tiles (not needed for Cards/Lists!)
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        mRecyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        mRecyclerView.setLayoutManager(mLayoutManager);



        mRecyclerView.setHasFixedSize(true);
    }

    private ArrayList<EmployeeWrapper> showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<EmployeeWrapper> employees = new ArrayList<EmployeeWrapper>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_NAME);
                String designation = jo.getString(Config.TAG_DESG);
                String salary = jo.getString(Config.TAG_SAL);

                Log.e("MDEV", "id = "+ id);
                Log.e("MDEV", "Name = "+ name);
                Log.e("MDEV", "designation = "+ designation);

                EmployeeWrapper employee = new EmployeeWrapper(Integer.parseInt(id), name,
                        designation, salary);

                employees.add(employee);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return employees;

    }

    @Override
    protected void onResume() {
        super.onResume();
        getJSON();
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                mEmployees = showEmployee();
                mAdapter = new EmployeeContentAdapter(MainActivity.this, mEmployees);

                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setHasFixedSize(true);

            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                Log.e("TEST", "S = "+s);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
