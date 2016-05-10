package tn.itskills.android.webservices.utils;

import java.util.ArrayList;

import tn.itskills.android.webservices.R;
import tn.itskills.android.webservices.models.EmployeeWrapper;


/**
 * Created by adnenhamdouni on 23/03/2016.
 */
public class EmployeeContent {

    public static  String[] names = {"Pablo Escobar" , "Alphone Gabriel Capone", "Charles Bronson" , "Joaquín Guzmán Elchapo" , "Tommy"};
    public static  String[] designations = {"ingenieur" , "technicien" , "enseignent" , "technicien" , "technicien"};
    public static  String[] salaries = {"4000" , "2000" , "3500" , "2700" , "2360"};
    public static Integer[] images ={R.drawable.escobar, R.drawable.alcapone, R.drawable.bronson, R.drawable.elchapo, R.drawable.tommy};



    public static ArrayList<EmployeeWrapper> getEmployees(){
        ArrayList<EmployeeWrapper> employees = new ArrayList<>();
        for (int i = 0; i<5; i++){
            employees.add(new EmployeeWrapper(names[i], designations[i], salaries[i]));
        }

        return employees;
    }

}
