package tn.itskills.android.webservices;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tn.itskills.android.webservices.models.EmployeeWrapper;
import tn.itskills.android.webservices.services.Config;
import tn.itskills.android.webservices.services.RequestHandler;

public class ProfileEmployeeActivity extends AppCompatActivity {

    private TextView id, name, salary, designation;

    EmployeeWrapper mEmployee;
    private int getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        AddEmployeeActivity.class);
                intent.putExtra("employee",mEmployee);
                intent.putExtra("from","update");
                //startActivity(intent);
                startActivityForResult(intent, Config.PICK_ADD_UPDATE_EMPLOYEE_REQUEST);
            }
        });

        getId = Integer.parseInt(getIntent().getStringExtra("id"));
        Log.e("adnen", "MainActivity : id ="+getId);


        initView();
        getEmployee();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getEmployee();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == Config.PICK_ADD_UPDATE_EMPLOYEE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == Config.PICK_ADD_UPDATE_EMPLOYEE_RESULT) {
                Log.e("test", "Back from addUpdateActivity");

                finish();
            }
        }
    }

    private void initView() {

        id = (TextView) findViewById(R.id.tvId);
        name = (TextView) findViewById(R.id.tvName);
        designation = (TextView) findViewById(R.id.tvDesignation);
        salary = (TextView) findViewById(R.id.tvSalary);
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(
                        ProfileEmployeeActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(
                        Config.URL_GET_EMP,
                        Integer.toString(getId));
                Log.e("adnen", "s ="+s);
                return s;
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);

            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String n = c.getString(Config.TAG_NAME);
            String desg = c.getString(Config.TAG_DESG);
            String sal = c.getString(Config.TAG_SAL);

            Log.e("adnen", "name = "+n);
            Log.e("adnen", "desg = "+desg);


            mEmployee = new EmployeeWrapper(getId,n,desg,sal);

            updateView(mEmployee);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateView(EmployeeWrapper mEmployee) {
        id.setText(Integer.toString(mEmployee.getId()));
        name.setText(mEmployee.getName());
        designation.setText(mEmployee.getDesignation());
        salary.setText(mEmployee.getSalary());
    }


}
