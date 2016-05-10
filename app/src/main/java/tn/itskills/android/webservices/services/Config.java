package tn.itskills.android.webservices.services;

/**
 * Created by adnenhamdouni on 10/05/2016.
 */
public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://10.0.3.2/itskills/addEmp.php";
    public static final String URL_GET_ALL = "http://10.0.3.2/itskills/getAllEmp.php";
    public static final String URL_GET_EMP = "http://10.0.3.2/itskills/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://10.0.3.2/itskills/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://10.0.3.2/itskills/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_DESG = "designation";
    public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESG = "designation";
    public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";

    public static final int PICK_ADD_UPDATE_EMPLOYEE_REQUEST = 1;
    public static final int PICK_ADD_UPDATE_EMPLOYEE_RESULT = 2;
}
