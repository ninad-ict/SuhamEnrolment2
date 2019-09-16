package com.suhamservice.ictsoftware.suhamenrolment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.joda.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.DeliveryLocation;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_ANTE;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_CHILD;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_GIRL;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_DELL;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.anteApplicant;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.antePersonal;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.antePreDelivery;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.applicationName;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childApplicant;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childBirth;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childBirth1;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childBirth2;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childBirthS;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childGrowth;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childGrowth1;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childGrowth2;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childGrowthS;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.delivery;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.deliveryApplicant;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.pregPersonal;

public  class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "SUHAM_ENROLDATA";
    private static final int DB_VERSION = 4;
    //-----------TABLES--------------
    private static final String TABLE_NAME_REGION = "REGIONTABLE";

    private static final String TABLE_NAME_LOCATION= "LOCATIONTABLE";
    private static final String TABLE_NAME_PERSONAL = "PERSONALTABLE";
    private static final String TABLE_NAME_HEALTH = "HEALTHTABLE";
    private static final String TABLE_NAME_EDUCATION = "EDUCATIONTABLE";

    private static final String TABLE_NAME_PREG_PERSONAL = "PREGPERSONAL";
    private static final String TABLE_NAME_PREGNANCY = "PREGNANCY";
    private static final String TABLE_NAME_DELIVERY = "DELIVERY";

    private static final String TABLE_NAME_PARENTS = "PARENTS";
    private static final String TABLE_NAME_CHILDBIRTH = "CHILDBIRTH";
    private static final String TABLE_NAME_CHILDGROWTH = "CHILDGROWTH";

    //---------TABLES FOR STORING MAPPED LOCATIONS WHERE DHAN WORKS-----------
    private static final String TABLE_NAME_STATE= "STATE";
    private static final String TABLE_NAME_DISTRICT = "DISTRICT";
    private static final String TABLE_NAME_FED = "FED";
    private static final String TABLE_NAME_PANCH = "PANCHAYAT";
    private static final String TABLE_NAME_VILLAGE = "VILLAGE";
    //---------TABLES FOR STORING MAPPED LOCATIONS WHERE DHAN WORKS-----------

    private static final String TABLE_NAME_ARCHIVE = "ARCHIVETABLE";

    //-----------------------TIME-STAMP--------------------------
    private static final String TABLE_NAME_TIMESTAMP = "TIMESTAMP";
    //-----------------------TIME-STAMP--------------------------

    //-----------------------ADD OR MODIFY CONTENT--------------------------
    private static final String TABLE_NAME_ADDMOD = "ADDMOD";
    //-----------------------ADD OR MODIFY CONTENT--------------------------

    //-----------------------TABLE TO MAP MOM-CHILD--------------------------
    private static final String TABLE_NAME_MOMCHILD = "MOMCHILD";
    //-----------------------TABLE TO MAP MOM-CHILD--------------------------

    //-----------------------TABLE FOR TRANSITION----------------------------
    private static final String TABLE_NAME_TRANSITION = "TRANSITION";
    //-----------------------TABLE FOR TRANSITION----------------------------

    //-----------------------TABLE FOR EMP_REG----------------------------
    private static final String TABLE_NAME_EMPREG = "EMPREG";
    //-----------------------TABLE FOR EMP_REG----------------------------

    //-----------------------TABLE FOR GIRL_GROUP----------------------------
    private static final String TABLE_NAME_GIRLGROUP = "GIRLGROUP";
    //-----------------------TABLE FOR GIRL_GROUP----------------------------

    //-----------------------TABLE FOR CONTACT----------------------------
    private static final String TABLE_NAME_CONTACT = "CONTACT";
    //-----------------------TABLE FOR CONTACT----------------------------

    //-----------------------TABLE FOR CONTACT----------------------------
    private static final String TABLE_NAME_TEST = "TESTME";
    //-----------------------TABLE FOR CONTACT----------------------------


    //-----------------------TABLE FOR CONTACT----------------------------
    private static final String TABLE_EMP_LOCATION = "EMPLOCATION";
    //-----------------------TABLE FOR CONTACT----------------------------

    //-----------------------TABLE FOR USER STORAGE----------------------------
    private static final String TABLE_USER = "USER";
    //-----------------------TABLE FOR USER STORAGE----------------------------

    //-----------------------TABLE FOR EMP TRACKING---------------------------
    private static final String TABLE_EMP_TRACK = "EMPTRACK";
    //-----------------------TABLE FOR EMP TRACKING---------------------------

    //------------COLUMNS FOR EMP TRACKING---------------
    private static final String COL_EMP_TRACK="EMPID";
    //------------COLUMNS FOR EMP TRACKING---------------

    //---------COLUMNS FOR TRANSITION-----------
    private static final String COL_TEST_NAME = "NAME";
    private static final String COL_TEST_ID = "ID";
    //---------COLUMNS FOR TRANSITION-----------

    //---------COLUMNS FOR EMP REG-----------
   /*private static final String COL_EMP_ID = "EMPID";
    private static final String COL_EMP_NAME = "NAME";
    private static final String COL_EMP_PHONE = "PHONE";
    private static final String COL_EMP_ADDRESS= "ADDRESS";
    private static final String COL_EMP_TIME = "TIME";
    private static final String COL_EMP_STATUS = "STATUS";*/
    private static final String COL_EMP_ID = "ID";
    private static final String COL_EMP_NAME = "NAME";
    private static final String COL_EMP_MOBILE = "MOBILE";
    private static final String COL_EMP_ADDRESS= "ADDRESS";
    private static final String COL_EMP_PIN = "PIN";
    //---------COLUMNS FOR EMP REG-----------

    //---------COLUMNS FOR TRANSITION-----------
    private static final String COL_OLD = "OLD";
    private static final String COL_NEW = "NEW";
    //---------COLUMNS FOR TRANSITION-----------


    private static final String COL_ID = "ID";


    private static final String COL_A_NO = "SR";
    private static final String COL_A_NAME = "NAME";
    private static final String COL_A_ID = "ID";
    private static final String COL_A_STATUS = "STATUS";

    private static final String COL_L_REGION = "REGION";

    private static final String COL_L_STATE = "STATE";
    private static final String COL_L_DISTRICT = "DISTRICT";
    private static final String COL_L_FED = "FEDERATION";
    private static final String COL_L_FEDCODE = "FED_CODE";
    private static final String COL_L_PANCH = "PANCHAYAT";
    private static final String COL_L_VILLAGE = "VILLAGE";

    //private static final String COL_L_ID = "ID";
    private static final String COL_L_SENT = "SENT";

    private static final String COL_P_NAME="GNAME";
    private static final String COL_P_MOTHER="MOTHER";
    private static final String COL_P_M_MEMBER="M_MEMBER";
    private static final String COL_P_F_MEMBER="F_MEMBER";
    private static final String COL_P_FATHER="FATHER";
    private static final String COL_P_DOB="DOB";
    private static final String COL_P_ISMARR="IS_MARRIED";
    private static final String COL_P_MARRYEAR="MARRIAGE_YEAR";

    private static final String COL_E_EDUCATION="EDUCATION";
    private static final String COL_E_STUDY="STUDY";
    private static final String COL_E_WORK="WORK";

    private static final String COL_H_PUBERTY="YEAR_PUBERTY";
    private static final String COL_H_HB="HB";
    private static final String COL_H_HEIGHT="HEIGHT";
    private static final String COL_H_WEIGHT="WEIGHT";


    private static final String COL_PP_NAME="NAME";
    private static final String COL_PP_HUSBAND="HUSBAND";
    private static final String COL_PP_H_MEMBER="H_MEMBER";
    private static final String COL_PP_M_MEMBER="M_MEMBER";
    private static final String COL_PP_DOB="DOB";
    private static final String COL_PP_EDUCATION="EDUCATION";

    private static final String COL_PD_LMP="LMP";
    private static final String COL_PD_EDD="EDD";
    private static final String COL_PD_GRAVIDA="GRAVIDA";
    private static final String COL_PD_PHC="WHEN_REGISTERED";
    private static final String COL_PD_MONTH="MONTH_PREGNANT";

    private static final String COL_DD_PLACE="PLACE";
    private static final String COL_DD_STATUS="STATUS";
    //private static final String COL_DD_SEX="SEX";-------SKIP----
    private static final String COL_DD_COLO="COLOSTRUM";
    private static final String COL_DD_TWINS="TWINS";
    private static final String COL_DD_OUTCOME="OUTCOME";
    private static final String COL_DD_ABOR_MONTH="ABORTION_MONTH";
    private static final String COL_DD_DOD="DOD";
    private static final String COL_DD_GRAVIDA="GRAVIDA";

    private static final String COL_PAR_MOM="MOM";
    private static final String COL_PAR_M_MEMBER="M_MEMBER";
    private static final String COL_PAR_DOB="MOM_DOB";
    private static final String COL_PAR_D_MEMBER="D_MEMBER";
    private static final String COL_PAR_DAD="DAD";

    private static final String COL_BIR_NAME="NAME";
    private static final String COL_BIR_SEX="SEX";
    private static final String COL_BIR_WEIGHT="WEIGHT";
    private static final String COL_BIR_DOB="DOB";
    private static final String COL_BIR_COLO="COLOSTRUM";

    private static final String COL_GROW_ORDER="ORDERBIRTH";
    private static final String COL_GROW_HEIGHT="HEIGHT";
    private static final String COL_GROW_WEIGHT="WEIGHT";
    //private static final String COL_GROW_AGE="AGE";

    private static final String COL_R_NAME = "NAME";

    //---------COLUMNS FOR STORING LOCATIONS WHERE DHAN WORKS-----------
    private static final String COL_STATE_N = "NAME";
    private static final String COL_STATE_SC = "S_CODE";

    private static final String COL_DIST_N = "NAME";
    private static final String COL_DIST_DC = "D_CODE";
    private static final String COL_DIST_SC = "S_CODE";

    private static final String COL_FED_N = "NAME";
    private static final String COL_FED_FC = "F_CODE";
    private static final String COL_FED_DC = "D_CODE";
    private static final String COL_FED_SC = "S_CODE";

    private static final String COL_PANCH_N = "NAME";
    private static final String COL_PANCH_PID = "PID";
    private static final String COL_PANCH_FCODE = "FCODE";

    private static final String COL_VILL_N = "NAME";
    private static final String COL_VILL_VID = "VID";
    private static final String COL_VILL_PID = "PID";

    //---------COLUMNS FOR STORING LOCATIONS WHERE DHAN WORKS-----------

    //---------COLUMNS FOR STORING TIMESTAMP-----------
    private static final String COL_TIME = "TIME";
    //---------COLUMNS FOR STORING TIMESTAMP-----------

    //---------COLUMNS FOR STORING ADD-MOD VALUE-----------
    private static final String COL_STATUS = "STATUS";
    //---------COLUMNS FOR STORING ADD-MOD VALUE-----------

    //---------COLUMNS FOR STORING MOM-CHILD MAPPING-----------
    private static final String COL_CID = "CID";
    //---------COLUMNS FOR STORING MOM-CHILD MAPPING-----------

    //---------COLUMNS FOR STORING GIRL GROUP ----------
    private static final String COL_GROUP_NAME = "GROUPNAME";
    private static final String COL_GROUP_TYPE = "GROUPTYPE";
    //---------COLUMNS FOR STORING GIRL GROUP ----------

    //---------COLUMNS FOR STORING CONTACT NUMBERS ----------
    private static final String COL_CONTACT = "CONTACT";
    //---------COLUMNS FOR STORING CONTACT NUMBERS ----------

    // ---------COLUMNS FOR STORING CONTACT NUMBERS ----------
    private static final String COL_L_USER = "USER";
    //---------COLUMNS FOR STORING CONTACT NUMBERS ----------

    private static DataBaseHelper sInstance;

   /* public DataBaseHelper(Context context, String database, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE, factory, DB_VERSION);
    }*/

    public static synchronized DataBaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //------CREATE TABLE GIRLGROUP------
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_GIRLGROUP + " ("+ COL_ID + " TEXT,"+ COL_GROUP_NAME +" TEXT,"+COL_GROUP_TYPE + " TEXT);";
        db.execSQL(CREATE_TABLE);
        //Log.d("ON-CREATE","Create TABLE_NAME_GIRLGROUP QUERY->"+CREATE_TABLE);
        //------CREATE TABLE GIRLGROUP------

        //------CREATE TABLE CONTACT------
        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_CONTACT + " ("+ COL_ID + " TEXT,"+ COL_CONTACT +" TEXT);";
        db.execSQL(CREATE_TABLE);
        //Log.d("ON-CREATE","Create TABLE_NAME_CONTACT QUERY->"+CREATE_TABLE);
        //------CREATE TABLE CONTACT------

        //------CREATE TABLE EMPREG------
        //----------VERSION1-----------------
         //CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_EMPREG + " (" + COL_EMP_ID +" TEXT,"+COL_EMP_NAME + " TEXT,"+ COL_EMP_PHONE + " TEXT,"+COL_EMP_ADDRESS+" TEXT,"+COL_EMP_TIME+" TEXT,"+COL_EMP_STATUS+" TEXT);";
       // db.execSQL(CREATE_TABLE);
        //----------VERSION1-----------------

        //----------VERSION-2-----------------
        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_EMPREG + " (" + COL_EMP_ID + " TEXT," + COL_EMP_NAME + " TEXT,"+ COL_EMP_PIN + " TEXT,"+ COL_EMP_MOBILE + " TEXT,"+ COL_EMP_ADDRESS + " TEXT);";
        db.execSQL(CREATE_TABLE);
        //----------VERSION-2-----------------

        //----------VERSION-3-----------------
        CREATE_TABLE = "CREATE TABLE " + TABLE_USER + " (" + COL_L_USER + " TEXT);";
        db.execSQL(CREATE_TABLE);
        //----------VERSION-3-----------------

        //Log.d("ON-CREATE","Create TABLE_NAME_EMPREG QUERY->"+CREATE_TABLE);
        //------CREATE TABLE EMPREG------

        //------CREATE TABLE TIMESTAMP------
        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_TIMESTAMP + " (" + COL_ID + " TEXT,"+ COL_TIME + " TEXT);";
        db.execSQL(CREATE_TABLE);
      //  Log.d("ON-CREATE","Create TABLE_NAME_TIMESTAMP QUERY->"+CREATE_TABLE);
        //------CREATE TABLE TIMESTAMP------

        //------CREATE TABLE ADDMOD------
        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_ADDMOD + " (" + COL_ID + " TEXT,"+ COL_STATUS + " TEXT);";
        db.execSQL(CREATE_TABLE);
       // Log.d("ON-CREATE","Create TABLE_NAME_ADDMOD QUERY->"+CREATE_TABLE);
        //------CREATE TABLE ADDMOD------

        //------CREATE TABLE MOMCHILD------
        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_MOMCHILD + " (" + COL_ID + " TEXT,"+ COL_CID + " TEXT);";
        db.execSQL(CREATE_TABLE);
       // Log.d("ON-CREATE","Create TABLE_NAME_MOMCHILD QUERY->"+CREATE_TABLE);
        //------CREATE TABLE MOMCHILD------

        //------CREATE TABLE LOCATION----

         CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_LOCATION + " (" + COL_L_FEDCODE + " TEXT,"+ COL_L_STATE + " TEXT,"+ COL_L_DISTRICT + " TEXT," + COL_L_FED + " TEXT," + COL_L_PANCH + " TEXT," + COL_L_VILLAGE + " TEXT," + COL_ID + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
       // Log.d("ON-CREATE","Create TABLE_LOCATION QUERY->"+CREATE_TABLE);

        //------CREATE TABLE LOCATION----


        //---------CREATE TABLE FOR EMP LOCATION----------
        CREATE_TABLE = "CREATE TABLE " + TABLE_EMP_LOCATION + " (" + COL_L_FEDCODE + " TEXT,"+ COL_L_STATE + " TEXT,"+ COL_L_DISTRICT + " TEXT," + COL_L_FED + " TEXT," + COL_L_PANCH + " TEXT," + COL_L_VILLAGE + " TEXT," + COL_ID + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
        //---------CREATE TABLE FOR EMP LOCATION----------



        //------CREATE TABLE PERSONAL-------------

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_PERSONAL+ " (" + COL_P_NAME + " TEXT," + COL_P_MOTHER + " TEXT," +COL_P_M_MEMBER+" TEXT," + COL_P_FATHER + " TEXT,"+COL_P_F_MEMBER+" TEXT," + COL_P_DOB + " TEXT,"+ COL_P_MARRYEAR + " TEXT," +COL_ID+" TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
       // Log.d("ON-CREATE","Create TABLE_PERSONAL QUERY->"+CREATE_TABLE);

        //------CREATE TABLE PERSONAL--------------

        //------CREATE TABLE HEALTH----

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_HEALTH + " (" + COL_H_PUBERTY + " TEXT," + COL_H_HB + " TEXT," + COL_H_HEIGHT + " TEXT," + COL_H_WEIGHT+ " TEXT," + COL_ID + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
       // Log.d("ON-CREATE","Create TABLE_HEALTH QUERY->"+CREATE_TABLE);

        //------CREATE TABLE HEALTH----


        //------CREATE TABLE EDUCATION----


        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_EDUCATION + " (" + COL_E_EDUCATION + " TEXT," + COL_E_WORK+ " TEXT,"+COL_E_STUDY+" TEXT,"+ COL_ID + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
      //  Log.d("ON-CREATE","Create TABLE_EDUCATION QUERY->"+CREATE_TABLE);

        //------CREATE TABLE EDUCATION----

        //------CREATE TABLE ARCHIVE----

        CREATE_TABLE="CREATE TABLE " + TABLE_NAME_ARCHIVE + " (" + COL_A_NO + " TEXT," + COL_A_NAME + " TEXT," + COL_A_ID + " TEXT);";
       // Log.d("ON-CREATE","Create TABLE_ARCHIVE QUERY->"+CREATE_TABLE);
        db.execSQL(CREATE_TABLE);

        //------CREATE TABLE ARCHIVE----

        //------CREATE TABLE PREG_PERSONAL----

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_PREG_PERSONAL+ " (" + COL_PP_NAME + " TEXT," +COL_PP_M_MEMBER + " TEXT," + COL_PP_HUSBAND + " TEXT," +COL_PP_H_MEMBER + " TEXT," + COL_PP_DOB + " TEXT," + COL_PP_EDUCATION + " TEXT," +COL_ID+" TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_PREG_PERSONAL QUERY->"+CREATE_TABLE);

        //------CREATE TABLE PREG_PERSONAL----

        //------CREATE TABLE PREGNANCY----


        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_PREGNANCY+ " (" + COL_PD_LMP + " TEXT," + COL_PD_EDD + " TEXT," + COL_PD_GRAVIDA + " TEXT," + COL_PD_PHC + " TEXT," + COL_PD_MONTH + " TEXT," +COL_ID+" TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_PREGNANCY QUERY->"+CREATE_TABLE);

        //------CREATE TABLE PREGNANCY----

        //------CREATE TABLE DELIVERY----

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_DELIVERY+ " (" + COL_DD_PLACE + " TEXT," + COL_DD_STATUS
                + " TEXT," + COL_DD_TWINS + " TEXT," + COL_DD_COLO + " TEXT," +
                COL_DD_OUTCOME + " TEXT," +COL_DD_ABOR_MONTH + " TEXT," +COL_DD_DOD + " TEXT," +
                COL_DD_GRAVIDA + " TEXT,"+COL_ID+" TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_DELIVERY QUERY->"+CREATE_TABLE);

        //------CREATE TABLE DELIVERY----

        //------CREATE TABLE PARENTS----



        CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME_PARENTS+ " (" + COL_PAR_MOM + " TEXT," + COL_PAR_DOB +
                        " TEXT,"+ COL_PAR_M_MEMBER + " TEXT," + COL_PAR_DAD + " TEXT," + COL_PAR_D_MEMBER + " TEXT,"+COL_ID+" TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_PARENTS QUERY->"+CREATE_TABLE);

        //------CREATE TABLE PARENTS----

        //------CREATE TABLE CHILDCARE----


        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_CHILDBIRTH+ " (" + COL_BIR_NAME + " TEXT,"
                + COL_BIR_SEX + " TEXT," + COL_BIR_WEIGHT + " TEXT," + COL_BIR_DOB + " TEXT,"
                + COL_BIR_COLO + " TEXT," +COL_ID+" TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_CHILDBIRTH QUERY->"+CREATE_TABLE);

        //------CREATE TABLE CHILDCARE----

        //------CREATE TABLE CHILDGROWTH----


        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_CHILDGROWTH+ " (" + COL_GROW_ORDER + " TEXT," + COL_GROW_HEIGHT + " TEXT," + COL_GROW_WEIGHT + " TEXT,"+COL_ID+" TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_CHILDGROWTH QUERY->"+CREATE_TABLE);

        //------CREATE TABLE CHILDGROWTH----

        //------CREATE TABLE STATE----

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_STATE+ " (" + COL_STATE_N + " TEXT," + COL_STATE_SC + " INT NOT NULL PRIMARY KEY);";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_STATE QUERY->"+CREATE_TABLE);

        //------CREATE TABLE STATE----

        //------CREATE TABLE DISTRICT----

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_DISTRICT+ " (" + COL_DIST_N + " TEXT," + COL_DIST_DC + " INT NOT NULL PRIMARY KEY,"+COL_DIST_SC+" INT,foreign key("+COL_DIST_SC+") references "+TABLE_NAME_STATE+" ("+COL_STATE_SC+"));";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_DISTRICT QUERY->"+CREATE_TABLE);

        //------CREATE TABLE DISTRICT----

        //------CREATE TABLE FED----

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_FED+ " (" + COL_FED_N + " TEXT," + COL_FED_FC + " INT NOT NULL PRIMARY KEY,"+COL_DIST_DC+" INT,"+COL_FED_SC+" INT,foreign key("+COL_FED_DC+") references "+TABLE_NAME_DISTRICT+" ("+COL_DIST_DC+"),foreign key("+COL_FED_SC+") references "+TABLE_NAME_STATE+"("+COL_STATE_SC+"));";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_DISTRICT QUERY->"+CREATE_TABLE);

        //------CREATE TABLE FED----

        //------CREATE TABLE PANCH----

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_PANCH+ " (" + COL_PANCH_N + " TEXT," + COL_PANCH_PID + " INT NOT NULL PRIMARY KEY,"+COL_PANCH_FCODE+",foreign key("+COL_PANCH_FCODE+") references "+TABLE_NAME_FED+" ("+COL_FED_FC+"));";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_DISTRICT QUERY->"+CREATE_TABLE);

        //------CREATE TABLE PANCH----

        //------CREATE TABLE VILLAGE----

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_VILLAGE+ " (" + COL_VILL_N + " TEXT," + COL_VILL_VID + " INT NOT NULL PRIMARY KEY,"+COL_VILL_PID+",foreign key("+COL_VILL_PID+") references "+TABLE_NAME_PANCH+" ("+COL_PANCH_PID+"));";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_DISTRICT QUERY->"+CREATE_TABLE);

        //------CREATE TABLE VILLAGE----

        //------CREATE TABLE TRANSITION----

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_TRANSITION+ " (" + COL_OLD + " TEXT," + COL_NEW + " TEXT);";
        db.execSQL(CREATE_TABLE);
        // log.d("ON-CREATE","Create TABLE_NAME_TRANSITION QUERY->"+CREATE_TABLE);

        //------CREATE TABLE TRANSITION----
        //db.close();

        //------CREATE TABLE FOR EMP TRACK-------------
        CREATE_TABLE = "CREATE TABLE " + TABLE_EMP_TRACK+ " (" + COL_EMP_TRACK + " TEXT," + COL_ID + " TEXT);";
        db.execSQL(CREATE_TABLE);
        //------CREATE TABLE FOR EMP TRACK-------------
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion<2) {
            //------CREATE NEW  TABLE EMPREG------
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_EMPREG);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_PREG_PERSONAL);

            String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_EMPREG + " (" + COL_EMP_ID + " TEXT," + COL_EMP_NAME + " TEXT,"+ COL_EMP_PIN + " TEXT,"+ COL_EMP_MOBILE + " TEXT,"+ COL_EMP_ADDRESS + " TEXT);";
            db.execSQL(CREATE_TABLE);
            //------CREATE NEW  TABLE EMPREG------

            //------CREATE TABLE PREG_PERSONAL----

            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_PREG_PERSONAL+ " (" + COL_PP_NAME + " TEXT," +COL_PP_M_MEMBER + " TEXT," + COL_PP_HUSBAND + " TEXT," +COL_PP_H_MEMBER + " TEXT," + COL_PP_DOB + " TEXT," + COL_PP_EDUCATION + " TEXT," +COL_ID+" TEXT NOT NULL);";
            db.execSQL(CREATE_TABLE);
            // log.d("ON-CREATE","Create TABLE_NAME_PREG_PERSONAL QUERY->"+CREATE_TABLE);

            //------CREATE TABLE PREG_PERSONAL---

            //---------CREATE TABLE FOR EMP LOCATION----------
            CREATE_TABLE = "CREATE TABLE " + TABLE_EMP_LOCATION + " (" + COL_L_FEDCODE + " TEXT,"+ COL_L_STATE + " TEXT,"+ COL_L_DISTRICT + " TEXT," + COL_L_FED + " TEXT," + COL_L_PANCH + " TEXT," + COL_L_VILLAGE + " TEXT," + COL_ID + " TEXT NOT NULL);";
            db.execSQL(CREATE_TABLE);
            //---------CREATE TABLE FOR EMP LOCATION----------

            //------CREATE TABLE PANCH----

            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_PANCH+ " (" + COL_PANCH_N + " TEXT," + COL_PANCH_PID + " INT NOT NULL PRIMARY KEY,"+COL_PANCH_FCODE+",foreign key("+COL_PANCH_FCODE+") references "+TABLE_NAME_FED+" ("+COL_FED_FC+"));";
            db.execSQL(CREATE_TABLE);
            // log.d("ON-CREATE","Create TABLE_NAME_DISTRICT QUERY->"+CREATE_TABLE);

            //------CREATE TABLE PANCH----

            //------CREATE TABLE VILLAGE----

            CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_VILLAGE+ " (" + COL_VILL_N + " TEXT," + COL_VILL_VID + " INT NOT NULL PRIMARY KEY,"+COL_VILL_PID+",foreign key("+COL_VILL_PID+") references "+TABLE_NAME_PANCH+" ("+COL_PANCH_PID+"));";
            db.execSQL(CREATE_TABLE);
            // log.d("ON-CREATE","Create TABLE_NAME_DISTRICT QUERY->"+CREATE_TABLE);

            //------CREATE TABLE VILLAGE----
        }

        if(oldVersion<3)
        {
            String CREATE_TABLE = "CREATE TABLE " + TABLE_USER + " (" + COL_L_USER + " TEXT);";
            db.execSQL(CREATE_TABLE);
        }

        if(oldVersion<4)
        {
            //------CREATE TABLE FOR EMP TRACK-------------
            String CREATE_TABLE = "CREATE TABLE " + TABLE_EMP_TRACK+ " (" + COL_EMP_TRACK + " TEXT," + COL_ID + " TEXT);";
            db.execSQL(CREATE_TABLE);
            //------CREATE TABLE FOR EMP TRACK-------------
        }

    }


    public void setUser(String user)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        db.delete(TABLE_USER,null,null);
        contentValues.put(COL_L_USER,user);
        db.insert(TABLE_USER,null,contentValues);
    }

    public String getUser() {
        String mess = "In getUser";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_L_USER + " FROM " + TABLE_USER + ";";
        Cursor cursor = null;
        Log.d(mess, "QUERY->" + query);

        cursor = db.rawQuery(query, null);

        if (cursor.getCount() == 0) {
            // log.d(mess,"No rows selected");
            return "-1";
        } else {
            cursor.moveToNext();

            return cursor.getString(0);
        }
    }


    public Boolean checkEmpStatus(String Login,String Password)
    {String mess="In checkEmpStatus";
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME_EMPREG+" WHERE "+COL_EMP_MOBILE+"="+Login+" AND "+COL_EMP_PIN+"="+Password+";";
        Cursor cursor=null;
        Log.d(mess,"QUERY->"+query);

        cursor=db.rawQuery(query,null);

        if(cursor.getCount()<1)
        {
            return false;
        }
        return true;
    }

    public void storeEmpDetails(String id,String name,String pin,String mobile,String address)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_EMP_ID,id);
        contentValues.put(COL_EMP_NAME,name);
        contentValues.put(COL_EMP_PIN,pin);
        contentValues.put(COL_EMP_MOBILE,mobile);
        contentValues.put(COL_EMP_ADDRESS,address);

        db.insert(TABLE_NAME_EMPREG,null,contentValues);
    }



   public String[] getEmpRegData()
    {
        String mess="IN-getEmpRegData";
         Log.d(mess,"FirstLine");

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        //select s.name,d.name,f.name from fed f,state s,district d where f_code=8002 and f.d_code=d.d_code and f.s_code=s.s_code;
        String query="SELECT * FROM "+TABLE_NAME_EMPREG+" WHERE "+COL_EMP_MOBILE+"= ? AND "+COL_EMP_PIN+"= ?";
        Log.d(mess,query);
            cursor = db.rawQuery(query, new String[]{EmployeeLogin.LOGNAME,EmployeeLogin.LOGPASS});
            ////db.close();
            int count = cursor.getCount();
        Log.d(mess,"count->"+count);
            if (count == 0) {
                Log.d(mess,"No rows selected");
                return new String[]{"-1"};
            } else {
                cursor.moveToNext();

                Log.d(mess,"cursor[0]->"+cursor.getString(0));
                // log.d(mess,"cursor[1]->"+cursor.getString(1));
                // log.d(mess,"cursor[2]->"+cursor.getString(2));
                // log.d(mess,"cursor[3]->"+cursor.getString(3));
                // log.d(mess,"cursor[4]->"+cursor.getString(4));
                // log.d(mess,"cursor[5]->"+cursor.getString(5));

                String[] EMPDATA = new String[5];

                EMPDATA[0] = cursor.getString(0);
                EMPDATA[1] = cursor.getString(1);
                EMPDATA[2] = cursor.getString(2);
                EMPDATA[3] = cursor.getString(3);
                EMPDATA[4] = cursor.getString(4);
                return EMPDATA;
            }


    }


    public String[] getEmpLocation(String id)
    {
        Cursor cursor=null;
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+TABLE_EMP_LOCATION+" WHERE "+COL_ID+"= ?";
        cursor = db.rawQuery(query, new String[]{id});

        if (cursor.getCount() == 0) {
            // log.d(mess,"No rows selected");
            return new String[]{"-1"};
        } else {
            cursor.moveToNext();

            String[] EMPDATA = new String[6];

            EMPDATA[0] = cursor.getString(0);
            EMPDATA[1] = cursor.getString(1);
            EMPDATA[2] = cursor.getString(2);
            EMPDATA[3] = cursor.getString(3);
            EMPDATA[4] = cursor.getString(4);
            EMPDATA[5] = cursor.getString(5);
            return EMPDATA;
        }

    }

    public void  setEmpLocation(LocationFragment locationFragment)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_L_VILLAGE,locationFragment.VILLAGE);
        contentValues.put(COL_L_STATE,locationFragment.STATE);
        contentValues.put(COL_L_DISTRICT,locationFragment.DISTRICT);
        contentValues.put(COL_L_FED,locationFragment.FED);
        contentValues.put(COL_L_FEDCODE, locationFragment.FEDCODE);
        contentValues.put(COL_L_PANCH, locationFragment.PANCH);
        contentValues.put(COL_ID,getEmpRegData()[0]);

        //        db.update(TABLE_NAME_ARCHIVE,contentValues,COL_ID+"=?",new String[]{id});
        if(getEmpLocation(getEmpRegData()[0])[0].equals("-1"))
        db.insert(TABLE_EMP_LOCATION,null,contentValues);
        else
            db.update(TABLE_EMP_LOCATION,contentValues,COL_ID+"=?",new String[]{getEmpRegData()[0]});
    }

    public void uploadState(String regions)
    {
        String mess="Inside upload STATE";
        Log.d(mess,"regions"+regions);
        // log.d(mess,regions);
       String[] Line= regions.split("-");
        // log.d(mess,"Line.Length->"+Line.length);
       SQLiteDatabase db=this.getWritableDatabase();
       for(int i=0;i<Line.length;i++)
       {
           String[] word=Line[i].split(";");
           // log.d(mess,"word.Length->"+word.length);

           Cursor cursor=null;
           String countQuery="Select * from "+ TABLE_NAME_STATE+" where s_code=?";
           try
           {
               cursor=db.rawQuery(countQuery,new String[]{word[1]});
               // log.d(mess,"Cursor->"+cursor.getCount());
           }catch (Exception e)
           {
               e.printStackTrace();
           }
           if(cursor.getCount()>0)
           continue;

           // log.d(mess,"Cursor->"+"No Code exists...lets add");

           ContentValues contentValues = new ContentValues();
           contentValues.put(COL_STATE_N,word[0]);
           contentValues.put(COL_STATE_SC,word[1]);
           db.insert(TABLE_NAME_STATE,null,contentValues);
       }
       //db.close();
     /* // SQLiteDatabase db=this.getWritableDatabase();
       for (int i=0;i<Line.length;i++)
       {
           ContentValues contentValues = new ContentValues();
           contentValues.put(COL_R_NAME,Line[i]);
           db.insert(TABLE_NAME_REGION,null,contentValues);
       }*/
    }

    public void uploadPanch(String result)
    {
        String mess="Inside-UPLOAD-Panch";
        Log.d(mess,"result->"+result);
        JSONArray jsonArray=null;
        try{
            Log.d(mess,"TRY-1");
            jsonArray=new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Log.d(mess, "JSON-IS->" + jsonObject.getString("NAME"));
                SQLiteDatabase db=this.getWritableDatabase();
                String countQuery="Select * from "+ TABLE_NAME_PANCH+" where "+COL_PANCH_PID+"=?";
                Cursor cursor=null;
                try
                {
                    cursor=db.rawQuery(countQuery,new String[]{jsonObject.getString("PID")});
                    Log.d(mess,"jsonObject.getString(PID)->"+jsonObject.getString("PID"));
                    if(cursor.getCount()>0)
                        continue;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(COL_PANCH_N,jsonObject.getString("NAME"));
                    contentValues.put(COL_PANCH_PID,jsonObject.getString("PID"));
                    contentValues.put(COL_PANCH_FCODE,jsonObject.getString("FCODE"));
                    db.insert(TABLE_NAME_PANCH,null,contentValues);
                    // log.d(mess,"Cursor->"+cursor.getCount());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }catch (JSONException j)
        {
            j.printStackTrace();
        }

        }


    public void uploadVillage(String result)
    {
        String mess="Inside-UPLOAD-Panch";
        Log.d(mess,"result->"+result);
        JSONArray jsonArray=null;
        try{
            Log.d(mess,"TRY-1");
            jsonArray=new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Log.d(mess, "JSON-IS->" + jsonObject.getString("NAME"));
                SQLiteDatabase db=this.getWritableDatabase();
                String countQuery="Select * from "+ TABLE_NAME_VILLAGE+" where "+COL_VILL_VID+"=?";
                Cursor cursor=null;
                try
                {
                    cursor=db.rawQuery(countQuery,new String[]{jsonObject.getString("VID")});
                    Log.d(mess,"jsonObject.getString(VID)->"+jsonObject.getString("VID"));
                    if(cursor.getCount()>0)
                        continue;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(COL_VILL_N,jsonObject.getString("NAME"));
                    contentValues.put(COL_VILL_VID,jsonObject.getString("VID"));
                    contentValues.put(COL_VILL_PID,jsonObject.getString("PID"));
                    db.insert(TABLE_NAME_VILLAGE,null,contentValues);
                    // log.d(mess,"Cursor->"+cursor.getCount());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }catch (JSONException j)
        {
            j.printStackTrace();
        }

    }

        public String[] getPanch(String FEDCODE) {
            SQLiteDatabase db = this.getWritableDatabase();
            String Query = "Select "+COL_PANCH_N+" from " + TABLE_NAME_PANCH + " where " + COL_PANCH_FCODE + "=?";
            Cursor cursor = null;
            try {
                cursor = db.rawQuery(Query, new String[]{FEDCODE});

                if (cursor.getCount() == 0) {
                    // log.d(mess,"No rows selected");
                    return new String[]{"No List!"};
                } else {
                    String[] result=new String[cursor.getCount()+1];
                    result[0]="Select Panchayat";
                    for (int i=1;i<=cursor.getCount();i++)
                    {
                        cursor.moveToNext();
                        // log.d(mess,"Cursor is pointing at->"+String.valueOf(cursor.getString(0)));
                        result[i]=String.valueOf(cursor.getString(0));
                        // updateSentStatus(StoreID[i],"Y");
                    }

                    return result;

                }
                } catch (Exception e) {
                e.getStackTrace();
            }
            return new String[]{"-1"};
        }

    public String[] getVillage(String PANCH) {
       String mess="IN-GET-VILLAGE";
       Log.d(mess,"PANCH->"+PANCH);
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select "+COL_VILL_N+" from " + TABLE_NAME_VILLAGE
                + " where " + COL_VILL_PID + " IN (SELECT "+COL_PANCH_PID+" FROM "+TABLE_NAME_PANCH+" WHERE "+COL_PANCH_N+"=?);";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(Query, new String[]{PANCH});

            if (cursor.getCount() == 0) {
                // log.d(mess,"No rows selected");
                return new String[]{"No List!"};
            } else {
                String[] result=new String[cursor.getCount()+1];
                result[0]="Select Village";
                for (int i=1;i<=cursor.getCount();i++)
                {
                    cursor.moveToNext();
                    // log.d(mess,"Cursor is pointing at->"+String.valueOf(cursor.getString(0)));
                    result[i]=String.valueOf(cursor.getString(0));
                    // updateSentStatus(StoreID[i],"Y");
                }

                return result;

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new String[]{"-1"};
    }

    public void uploadDistrict(String lines)
    {
        String mess="Inside upload DIST";
        // log.d(mess,lines);
        String[] Line= lines.split("-");
        // log.d(mess,"Line.Length->"+Line.length);
        SQLiteDatabase db=this.getWritableDatabase();
        for(int i=0;i<Line.length;i++)
        {
            String[] word=Line[i].split(";");
            // log.d(mess,"word.Length->"+word.length);

            Cursor cursor=null;
            String countQuery="Select * from "+ TABLE_NAME_DISTRICT+" where d_code=?";
            try
            {
                cursor=db.rawQuery(countQuery,new String[]{word[1]});
                // log.d(mess,"Cursor->"+cursor.getCount());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            if(cursor.getCount()>0)
                continue;

            // log.d(mess,"Cursor->"+"No Code exists...lets add");

            ContentValues contentValues = new ContentValues();

            contentValues.put(COL_DIST_N,word[0]);
            contentValues.put(COL_DIST_DC,word[1]);
            contentValues.put(COL_DIST_SC,word[2]);

            db.insert(TABLE_NAME_DISTRICT,null,contentValues);
        }
        //db.close();

    }

    public void uploadFed(String lines)
    {
        String mess="Inside upload DIST";
        // log.d(mess,lines);
        String[] Line= lines.split("-");
        // log.d(mess,"Line.Length->"+Line.length);
        SQLiteDatabase db=this.getWritableDatabase();
        for(int i=0;i<Line.length;i++)
        {
            String[] word=Line[i].split(";");
            // log.d(mess,"word.Length->"+word.length);

            Cursor cursor=null;
            String countQuery="Select * from "+ TABLE_NAME_FED+" where f_code=?";
            try
            {
                cursor=db.rawQuery(countQuery,new String[]{word[1]});
                // log.d(mess,"Cursor->"+cursor.getCount());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            if(cursor.getCount()>0)
                continue;

            // log.d(mess,"Cursor->"+"No Code exists...lets add");


            ContentValues contentValues = new ContentValues();

            contentValues.put(COL_FED_N,word[0]);
            contentValues.put(COL_FED_FC,word[1]);
            contentValues.put(COL_FED_DC,word[2]);
            contentValues.put(COL_FED_SC,word[3]);

            db.insert(TABLE_NAME_FED,null,contentValues);
        }
        //db.close();

    }

    //-------Function to Get STATE/DIST/FED for FED CODE--------------

    public String[] getLocation(String s)
    {
        String mess="IN getLocation";
        // log.d(mess,"s->"+s);
        if(s.equals(""))
            return new String[]{"-1"};


        int code=Integer.parseInt(s);
        // log.d(mess,"code->"+code);
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        //select s.name,d.name,f.name from fed f,state s,district d where f_code=8002 and f.d_code=d.d_code and f.s_code=s.s_code;
        String query="SELECT s."+COL_STATE_N+",d."+COL_DIST_N+",f."+COL_FED_N+" from "+TABLE_NAME_FED+" f,"+TABLE_NAME_STATE+" s,"+TABLE_NAME_DISTRICT+" d where "+COL_FED_FC+"="+code+" and f."+COL_FED_DC+"=d."+COL_DIST_DC+" and f."+COL_FED_SC+"=s."+COL_STATE_SC+";";
        // log.d(mess,query);
       try {
           cursor = db.rawQuery(query, null);

           ////db.close();
           int count = cursor.getCount();
           // log.d(mess,"count->"+count);
           if (count == 0) {
               // log.d(mess,"No rows selected");
               return new String[]{"-1"};
           } else {
               cursor.moveToNext();
               // log.d(mess,"cursor[0]->"+cursor.getString(0));
               // log.d(mess,"cursor[1]->"+cursor.getString(1));
               // log.d(mess,"cursor[2]->"+cursor.getString(2));

               String[] location = new String[3];

               location[0] = cursor.getString(0);
               location[1] = cursor.getString(1);
               location[2] = cursor.getString(2);
               return location;
           }
       }
       finally {
           if (cursor != null)
               cursor.close();
                ////db.close();
       }

    }

    //-------Function to Get STATE/DIST/FED for FED CODE--------------

    public void setArchiveData(String name,String Id,String status)
    {
        String mess="SET-ARCHIVE";
        String query="SELECT * FROM "+TABLE_NAME_ARCHIVE+";";
        // log.d(mess,"QUERY->"+query);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        int count=1;
        try {
            cursor = db.rawQuery(query, null);
            count=cursor.getCount()+1;
            // log.d(mess,"COUNT in TRY->"+count);
        }catch (SQLException e)
        {// log.d(mess,"INSIDE catch");
            if(e.getMessage().contains("no such table"))
            {
                // log.d(mess,"INSIDE IF - CATCH");
                count=1;
            }
        }

        // log.d(mess,"COUNT->"+count);
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_A_NO,String.valueOf(count));
        contentValues.put(COL_A_NAME,name);
        contentValues.put(COL_A_ID,Id);
        //contentValues.put(COL_A_STATUS,status);
        db.insert(TABLE_NAME_ARCHIVE,null,contentValues);
        cursor.close();
        //db.close();
    }

    public void updateArchive(String id)
    {
        String mess="UPDATE-ARC";
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_A_STATUS,"Y");
        db.update(TABLE_NAME_ARCHIVE,contentValues,COL_ID+"=?",new String[]{id});
        //db.close();
    }

    public String[][] getArchive()
    {   String mess="GET-ARCH";
        // log.d(mess,"Entered Function");
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * from "+TABLE_NAME_ARCHIVE;
        // log.d(mess,query);
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();
        String data[][]=new String[count][3];
        for (int i=0;i<count;i++)
        {cursor.moveToNext();
            for (int j=0;j<3;j++)
            {
                data[i][j]=cursor.getString(j);
                // log.d(mess,cursor.getString(j));
            }
        }
        cursor.close();
        //db.close();
        return data;
    }

    //------------FUNCTION TO GET STATE LIST-----------

    public String[] getState(String s) {

        String mess="GetState";
        String query="SELECT "+COL_STATE_N+" FROM "+TABLE_NAME_STATE+" where "+COL_STATE_N+" like ?;";
        // log.d(mess,"QUERY IS->"+query);

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        try {


            cursor = db.rawQuery(query, new String[]{"%" + s + "%"});
            ////db.close();
            int count = cursor.getCount();
            if (count != 0) {
                String StoreID[] = new String[count];

                for (int i = 0; i < StoreID.length; i++) {
                    cursor.moveToNext();
                    // log.d(mess,"Cursor is pointing at->"+String.valueOf(cursor.getString(0)));
                    StoreID[i] = String.valueOf(cursor.getString(0));
                }
                return StoreID;
            }
            //else
            // log.d(mess,"count->"+count);
        }
        finally {
            if (cursor != null)
                cursor.close();
            //db.close();
        }
        return new String[]{"-1"};

    }

    //------------FUNCTION TO GET STATE LIST-----------


    //------------FUNCTION TO GET DISTRICT LIST-----------

    public String[] getDistrict(String district,String state) {

        String mess="GetDistrict";
        //select * from district where name like '%c%' and s_code in (select s_code from state where name="TamilNadu");
        String query="SELECT "+COL_DIST_N+" FROM "+TABLE_NAME_DISTRICT+" where "+COL_DIST_N+" like ? and "+COL_DIST_SC+" in (Select "+COL_STATE_SC+" from "+TABLE_NAME_STATE+" where "+COL_STATE_N+" =?);";
        // log.d(mess,"QUERY IS->"+query);

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        try {
            cursor = db.rawQuery(query, new String[]{"%" + district + "%", state});

            // //db.close();
            int count = cursor.getCount();
            if (count != 0) {
                String StoreID[] = new String[count];

                for (int i = 0; i < StoreID.length; i++) {
                    cursor.moveToNext();
                    // log.d(mess,"Cursor is pointing at->"+String.valueOf(cursor.getString(0)));
                    StoreID[i] = String.valueOf(cursor.getString(0));
                }
                return StoreID;
            }
            // else
            //  // log.d(mess,"count->"+count);
        }finally {
            if (cursor != null)
                cursor.close();
            //db.close();
        }
        return new String[]{"-1"};

    }

    //------------FUNCTION TO GET DISTRICT LIST-----------


    //------------FUNCTION TO GET FED LIST-----------

    public String[] getFed(String fed,String state,String district) {

        String mess="GetFed";
        //select * from district where name like '%c%' and s_code in (select s_code from state where name="TamilNadu");
        String query="SELECT "+COL_FED_N+" FROM "+TABLE_NAME_FED+
                " where "+COL_FED_N+" like ? "+
                // OR "+ COL_FED_N +" like ? " +
                " and "+COL_FED_DC+" in (Select "+COL_DIST_DC+" from "+TABLE_NAME_DISTRICT+" where "+COL_DIST_N+" =?)"+
                "and "+COL_FED_SC+" in (Select "+COL_STATE_SC+" from "+TABLE_NAME_STATE+" where "+COL_STATE_N+" =?);";
        // log.d(mess,"QUERY IS->"+query);
        // log.d(mess,"fed IS->"+fed);

        SQLiteDatabase db=this.getReadableDatabase();
       // Cursor cursor=db.rawQuery(query,new String[]{"%"+fed+"%","%%",district,state});
        Cursor cursor=null;
        try {
        cursor=db.rawQuery(query,new String[]{"%"+fed+"%",district,state});


            int count = cursor.getCount();
            if (count != 0) {
                String StoreID[] = new String[count + 1];

                for (int i = 0; i < StoreID.length - 1; i++) {
                    cursor.moveToNext();
                    // log.d(mess,"Cursor is pointing at->"+String.valueOf(cursor.getString(0)));
                    StoreID[i] = String.valueOf(cursor.getString(0));
                }
                StoreID[StoreID.length - 1] = "SUHAM";
                return StoreID;
            }
        }
        finally {
            if (cursor != null)
                cursor.close();
                 //db.close();
        }
       // else
           // // log.d(mess,"count->"+count);

        return new String[]{"-1"};

    }

    //------------FUNCTION TO GET FED LIST-----------


    //------------FUNCTION TO SET FED CODE------------

    public int setCode(String state,String district,String fed)
    {String mess="IN setCode";
       if(!state.equals(null)&&!district.equals(null)&&!fed.equals(null))
       {
           SQLiteDatabase db = this.getReadableDatabase();
           String query="SELECT "+COL_FED_FC+" FROM "+TABLE_NAME_FED+" where "+COL_FED_N+" =? " +
                   "and "+COL_FED_DC+" in (Select "+COL_DIST_DC+" from "+TABLE_NAME_DISTRICT+" where "+COL_DIST_N+" =?)"+
                   "and "+COL_FED_SC+" in (Select "+COL_STATE_SC+" from "+TABLE_NAME_STATE+" where "+COL_STATE_N+" =?);";
           Cursor cursor=db.rawQuery(query,new String[]{fed,district,state});
           ////db.close();
           int count=cursor.getCount();
           if(count!=0)
           {
               cursor.moveToNext();
               return cursor.getInt(0);
           }
           return 0;

       }
       return 0;
    }

    //------------FUNCTION TO SET FED CODE------------


    public void addData(LocationFragment locationFragment) {

        String mess = "ADD-DATA";


        Integer random = new Random().nextInt(9999) + 1;
        //String id=locationFragment.region.substring(0,1)+"-"+
        //      locationFragment.village.substring(0,1)+"-"+random.toString();

        String letter = "";
        if (ENROL_GIRL)
            letter = "A";
        if (ENROL_ANTE)
            letter = "P";
        if (ENROL_DELL)
            letter = "M";
        if (ENROL_CHILD)
            letter = "C";


        String id = locationFragment.FEDCODE + letter + random.toString();

        if (ENROL_GIRL)
        {

            if ((applicationName.ID_ENTERED)&& (applicationName.ID_WHO.equals("GIRL"))) {
                try {
                    if(applicationName.jsonObject.get("FED_CODE").toString().equals(locationFragment.FEDCODE))
                    {
                        id = applicationName.jsonObject.get("ID").toString();

                        //---------------UPDATING ADDMOD TABLE ------------
                        ContentValues contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, id);
                        contentValues.put(COL_STATUS, "MOD");
                        SQLiteDatabase db = this.getWritableDatabase();
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->MOD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------
                    }
                    else
                    {
                        String OldId=applicationName.jsonObject.get("ID").toString();
                        //---------------UPDATING TRANSITION TABLE ------------
                        ContentValues contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_OLD,OldId);
                        contentValues.put(COL_NEW,id);
                        SQLiteDatabase db = this.getWritableDatabase();
                        db = this.getWritableDatabase();
                        // log.d(mess, "FC_CHANGE->TRANSITION Values Are ->>if:" + COL_OLD + " -> " + OldId + " AND " + COL_NEW + " ->id");
                        db.insert(TABLE_NAME_TRANSITION, null, contentValues);
                        //db.close();
                        //---------------UPDATING TRANSITION TABLE ------------

                        //---------------UPDATING ADDMOD TABLE ------------
                        contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, id);
                        contentValues.put(COL_STATUS, "ADD");
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------
                    }

                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }
            else {
                //---------------UPDATING ADDMOD TABLE ------------
                ContentValues contentValues = new ContentValues();
                contentValues = new ContentValues();
                contentValues.put(COL_ID, id);
                contentValues.put(COL_STATUS, "ADD");
                SQLiteDatabase db = this.getWritableDatabase();
                db = this.getWritableDatabase();
                // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                //db.close();
                //---------------UPDATING ADDMOD TABLE ------------
            }
    }
        if(ENROL_ANTE)
        {
            if(anteApplicant.ID_ENTERED&&anteApplicant.ID_WHO.equals("PREG"))
            {// log.d("IN ANTE","IF:YEs id is entered");
                try {
                    if(anteApplicant.jsonObject.get("FED_CODE").toString().equals(locationFragment.FEDCODE)) {
                        // log.d("IN ANTE","fed_code=al.fedcode");
                        id = anteApplicant.jsonObject.get("ID").toString();

                        //---------------UPDATING ADDMOD TABLE ------------

                        ContentValues contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, id);
                        contentValues.put(COL_STATUS, "MOD");
                        SQLiteDatabase db = this.getWritableDatabase();
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->MOD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------
                    }
                    else
                    {
                        // log.d("IN ANTE","fed_code!=al.fedcode");
                        String OldId=anteApplicant.jsonObject.get("ID").toString();
                        //---------------UPDATING TRANSITION TABLE ------------
                        ContentValues contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_OLD,OldId);
                        contentValues.put(COL_NEW,id);
                        SQLiteDatabase db = this.getWritableDatabase();
                        db = this.getWritableDatabase();
                        // log.d(mess, "FC_CHANGE->TRANSITION Values Are ->>if:" + COL_OLD + " -> " + OldId + " AND " + COL_NEW + " ->id");
                        db.insert(TABLE_NAME_TRANSITION, null, contentValues);
                        //db.close();
                        //---------------UPDATING TRANSITION TABLE ------------

                        //---------------UPDATING ADDMOD TABLE ------------
                        contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, id);
                        contentValues.put(COL_STATUS, "ADD");
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------
                    }
                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }
            }
            else
            {// log.d("IN ANTE","ELSE:NO id,name is entered");
                //---------------UPDATING ADDMOD TABLE ------------
                ContentValues contentValues = new ContentValues();
                contentValues = new ContentValues();
                contentValues.put(COL_ID, id);
                contentValues.put(COL_STATUS, "ADD");
                SQLiteDatabase db = this.getWritableDatabase();
                db = this.getWritableDatabase();
                // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                //db.close();
                //---------------UPDATING ADDMOD TABLE ------------

            }

            if(anteApplicant.ID_ENTERED&&anteApplicant.ID_WHO.equals("GIRL"))
            {
                try {
                    String OldId=anteApplicant.jsonObject.get("ID").toString();

                    //---------------UPDATING ADDMOD TABLE ------------
                    ContentValues contentValues = new ContentValues();
                    contentValues = new ContentValues();
                    contentValues.put(COL_OLD,OldId);
                    contentValues.put(COL_NEW,id);
                    SQLiteDatabase db = this.getWritableDatabase();
                    db = this.getWritableDatabase();
                    // log.d(mess, "TRANSITION Values Are ->>if:" + COL_OLD + " -> " + OldId + " AND " + COL_NEW + " ->id");
                    db.insert(TABLE_NAME_TRANSITION, null, contentValues);
                    //db.close();
                    //---------------UPDATING ADDMOD TABLE ------------
                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }
            }
        }
        if(ENROL_DELL)
        {
            if(deliveryApplicant.ID_ENTERED&&deliveryApplicant.ID_WHO.equals("MOTH"))
            {
                try {
                    if(deliveryApplicant.jsonObject.get("FED_CODE").toString().equals(locationFragment.FEDCODE)) {
                        id = deliveryApplicant.jsonObject.get("ID").toString();

                        //---------------UPDATING ADDMOD TABLE ------------
                        ContentValues contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, id);
                        contentValues.put(COL_STATUS, "MOD");
                        SQLiteDatabase db = this.getWritableDatabase();
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>if:" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->MOD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------
                    }
                    else
                    {
                        String OldId=deliveryApplicant.jsonObject.get("ID").toString();
                        //---------------UPDATING TRANSITION TABLE ------------
                        ContentValues contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_OLD,OldId);
                        contentValues.put(COL_NEW,id);
                        SQLiteDatabase db = this.getWritableDatabase();
                        db = this.getWritableDatabase();
                        // log.d(mess, "FC_CHANGE->TRANSITION Values Are ->>if:" + COL_OLD + " -> " + OldId + " AND " + COL_NEW + " ->id");
                        db.insert(TABLE_NAME_TRANSITION, null, contentValues);
                        //db.close();
                        //---------------UPDATING TRANSITION TABLE ------------

                        //---------------UPDATING ADDMOD TABLE ------------
                         contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, id);
                        contentValues.put(COL_STATUS, "ADD");
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>else:" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------

                    }
                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }
            }
            else {

                //---------------UPDATING ADDMOD TABLE ------------
                ContentValues contentValues = new ContentValues();
                contentValues = new ContentValues();
                contentValues.put(COL_ID, id);
                contentValues.put(COL_STATUS, "ADD");
                SQLiteDatabase db = this.getWritableDatabase();
                db = this.getWritableDatabase();
                // log.d(mess, "ADDMOD Values Are ->>else:" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                //db.close();
                //---------------UPDATING ADDMOD TABLE ------------

            }


            if(deliveryApplicant.ID_ENTERED&&(deliveryApplicant.ID_WHO.equals("GIRL")||deliveryApplicant.ID_WHO.equals("PREG")))
            {
                try {
                    if(deliveryApplicant.jsonObject.get("FED_CODE").toString().equals(DeliveryLocation.FEDCODE)) {
                        String OldId = deliveryApplicant.jsonObject.get("ID").toString();

                        //---------------UPDATING ADDMOD TABLE ------------
                        ContentValues contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_OLD, OldId);
                        contentValues.put(COL_NEW, id);
                        SQLiteDatabase db = this.getWritableDatabase();
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>if:" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->MOD");
                        db.insert(TABLE_NAME_TRANSITION, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------
                    }
                    else
                    {
                        String OldId=deliveryApplicant.jsonObject.get("ID").toString();

                        //---------------UPDATING TRANSITION TABLE ------------
                        ContentValues contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_OLD,OldId);
                        contentValues.put(COL_NEW,id);
                        SQLiteDatabase db = this.getWritableDatabase();
                        db = this.getWritableDatabase();
                        // log.d(mess, "FC_CHANGE->TRANSITION Values Are ->>if:" + COL_OLD + " -> " + OldId + " AND " + COL_NEW + " ->id");
                        db.insert(TABLE_NAME_TRANSITION, null, contentValues);
                        //db.close();
                        //---------------UPDATING TRANSITION TABLE ------------

                        //---------------UPDATING ADDMOD TABLE ------------
                        contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID,id);
                        contentValues.put(COL_STATUS,"MOD");
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->MOD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------


                    }
                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }
            }
        }

        if(ENROL_CHILD)
        {
            if(childApplicant.ID_ENTERED&&childApplicant.ID_WHO.equals("CHILD"))
            {
                mess="IN-ENROL_CHILD";
                try {
                    // log.d(mess,"jsonFECODE-> "+childApplicant.jsonObject.get("FED_CODE").toString()
                    //+" location.fedcode-> "+locationFragment.FEDCODE);
                    if(childApplicant.jsonObject.get("FED_CODE").toString().equals(locationFragment.FEDCODE)) {
                        //// log.d(mess,"Entered for MOD trans");
                    id=childApplicant.jsonObject.get("ID").toString();

                    //---------------UPDATING ADDMOD TABLE ------------
                    ContentValues contentValues = new ContentValues();
                    contentValues = new ContentValues();
                    contentValues.put(COL_ID,id);
                    contentValues.put(COL_STATUS,"MOD");
                    SQLiteDatabase db = this.getWritableDatabase();
                    db = this.getWritableDatabase();
                   // // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->MOD");
                    db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                    //db.close();
                    //---------------UPDATING ADDMOD TABLE ------------

                    }
                    else
                    {
                        String OldId=childApplicant.jsonObject.get("ID").toString();
                        //---------------UPDATING TRANSITION TABLE ------------
                        ContentValues contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_OLD,OldId);
                        contentValues.put(COL_NEW,id);
                        SQLiteDatabase db = this.getWritableDatabase();
                        db = this.getWritableDatabase();
                        // log.d(mess, "FC_CHANGE->TRANSITION Values Are ->>if:" + COL_OLD + " -> " + OldId + " AND " + COL_NEW + " ->id");
                        db.insert(TABLE_NAME_TRANSITION, null, contentValues);
                        //db.close();
                        //---------------UPDATING TRANSITION TABLE ------------

                        //---------------UPDATING ADDMOD TABLE ------------
                         contentValues = new ContentValues();
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, id);
                        contentValues.put(COL_STATUS, "ADD");
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------

                    }
                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }
            }
            else
            {
                //---------------UPDATING ADDMOD TABLE ------------
                ContentValues contentValues = new ContentValues();
                contentValues = new ContentValues();
                contentValues.put(COL_ID, id);
                contentValues.put(COL_STATUS, "ADD");
                SQLiteDatabase db = this.getWritableDatabase();
                db = this.getWritableDatabase();
                // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                //db.close();
                //---------------UPDATING ADDMOD TABLE ------------
            }
        }


        MainActivity.ID=id;
        // log.d(mess,"ID is->"+id);

        //----------TRACKING EMP WHO HAS FILLED THE FORM------------------

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EMP_TRACK,getEmpRegData()[0]);
        contentValues.put(COL_ID,id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_EMP_TRACK, null, contentValues);

        //----------TRACKING EMP WHO HAS FILLED THE FORM------------------

       //---------------INSERTING CONTENTS OF LOCATION FRAGMENT INTO LOCAL DB-------------

        contentValues = new ContentValues();
        contentValues.put(COL_L_VILLAGE,locationFragment.VILLAGE);
        contentValues.put(COL_L_STATE,locationFragment.STATE);
        contentValues.put(COL_L_DISTRICT,locationFragment.DISTRICT);
        contentValues.put(COL_L_FED,locationFragment.FED);
        contentValues.put(COL_L_FEDCODE, locationFragment.FEDCODE);
        contentValues.put(COL_L_PANCH, locationFragment.PANCH);
        contentValues.put(COL_ID,id);

        db = this.getWritableDatabase();
        // log.d(mess,"Columns Are ->>"+COL_L_FED+" AND "+COL_L_PANCH+" AND "+COL_L_VILLAGE+" AND "+COL_L_REGION+" AND "+COL_ID);
        //Log.d(mess,"Columns Are ->>"+COL_L_FED+" AND "+COL_L_PANCH+" AND "+COL_L_VILLAGE+" AND "+COL_L_REGION+" AND "+COL_L_ID+" AND "+COL_L_SENT);
        db.insert(TABLE_NAME_LOCATION, null, contentValues);
        //db.close();

        //---------------INSERTING CONTENTS OF LOCATION FRAGMENT INTO LOCAL DB------------

        //---------------UPDATING TIMESTAMP FOR LOCAL TIME------------
        contentValues = new ContentValues();
        contentValues.put(COL_ID,id);
        contentValues.put(COL_TIME,String.valueOf(new LocalDateTime()));

        db = this.getWritableDatabase();
        // log.d(mess, "TimeStamp Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_TIME + " -> " + String.valueOf(new LocalDateTime()));
        db.insert(TABLE_NAME_TIMESTAMP, null, contentValues);
        //db.close();
        //---------------UPDATING TIMESTAMP FOR LOCAL TIME------------


        //-------------UPDATING CONTACT NUMBER------------------------
        contentValues = new ContentValues();
        contentValues.put(COL_ID,id);
        contentValues.put(COL_CONTACT,locationFragment.Contact);
        db = this.getWritableDatabase();
        // log.d(mess, "CONTACT TABLE Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_CONTACT + " -> " + locationFragment.Contact);
        db.insert(TABLE_NAME_CONTACT, null, contentValues);
        //db.close();
        //-------------UPDATING CONTACT NUMBER------------------------


        //---------------INSERTING CONTENTS OF PERSONAL FRAGMENT INTO LOCAL DB-------------

        if(MainActivity.ENROL_GIRL) {
            // log.d(mess,"Inside GIRL");
            contentValues = new ContentValues();
           /* String MarrYesNo = "";
            if (PersonalFragment.ISMARRIED)
                MarrYesNo = "YES";
            else MarrYesNo = "NO";*/

            contentValues.put(COL_P_NAME, PersonalFragment.Name);
            contentValues.put(COL_P_MOTHER, PersonalFragment.Mother);
            contentValues.put(COL_P_M_MEMBER, PersonalFragment.MotherMem);
            contentValues.put(COL_P_FATHER, PersonalFragment.Father);
            contentValues.put(COL_P_F_MEMBER, PersonalFragment.FatherMem);
            contentValues.put(COL_P_DOB, PersonalFragment.DOB);
            contentValues.put(COL_P_MARRYEAR, PersonalFragment.MarrYear);
            contentValues.put(COL_ID, id);

            db = this.getWritableDatabase();
            // log.d(mess, "Columns Are ->>" + COL_P_NAME + " AND " + COL_P_MOTHER + " AND " + COL_P_FATHER + " AND " + COL_P_DOB + " AND " + COL_P_ISMARR + "AND " + COL_P_MARRYEAR);
            db.insert(TABLE_NAME_PERSONAL, null, contentValues);
            //db.close();

            //---------------INSERTING CONTENTS OF PERSONAL FRAGMENT INTO LOCAL DB-------------

            //---------------INSERTING ADOL_SCHOOL GROUP INTO LOCAL DB-------------

            contentValues = new ContentValues();
            contentValues.put(COL_ID,id);
            contentValues.put(COL_GROUP_NAME,locationFragment.Group);
            contentValues.put(COL_GROUP_TYPE,locationFragment.GroupType);
            db = this.getWritableDatabase();
            // log.d(mess, "GIRLGROUP TABLE Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_GROUP_NAME + " -> " + locationFragment.Group+ COL_GROUP_TYPE + " -> " + locationFragment.GroupType);
            db.insert(TABLE_NAME_GIRLGROUP, null, contentValues);
            //db.close();

            //---------------INSERTING ADOL_SCHOOL GROUP INTO LOCAL DB-------------


            //---------------INSERTING CONTENTS OF EDUCATION FRAGMENT INTO LOCAL DB-------------

            contentValues = new ContentValues();
            contentValues.put(COL_E_EDUCATION, EducationWorkFragment.WORK);
            contentValues.put(COL_E_WORK, EducationWorkFragment.EDUCATION);
            contentValues.put(COL_E_STUDY, EducationWorkFragment.STUDY);
            contentValues.put(COL_ID, id);

            db = this.getWritableDatabase();
            // log.d(mess, "Columns Are ->>" + COL_E_EDUCATION + " AND " + COL_E_WORK);
            db.insert(TABLE_NAME_EDUCATION, null, contentValues);
            //db.close();


            //---------------INSERTING CONTENTS OF EDUCATION FRAGMENT INTO LOCAL DB-------------


            //---------------INSERTING CONTENTS OF HEALTH FRAGMENT INTO LOCAL DB-------------

            contentValues = new ContentValues();
            contentValues.put(COL_H_PUBERTY, HealthFragment.PubertyYear);
            contentValues.put(COL_H_HB, HealthFragment.HB);
            contentValues.put(COL_H_HEIGHT, HealthFragment.HEIGHT);
            contentValues.put(COL_H_WEIGHT, HealthFragment.WEIGHT);
            contentValues.put(COL_ID, id);

            db = this.getWritableDatabase();
            // log.d(mess, "Columns Are ->>" + COL_H_PUBERTY + " AND " + COL_H_HB + " AND " + COL_H_HEIGHT + " AND " + COL_H_WEIGHT);
            db.insert(TABLE_NAME_HEALTH, null, contentValues);
            //db.close();

            //---------------INSERTING CONTENTS OF HEALTH FRAGMENT INTO LOCAL DB-------------
        }

        if(MainActivity.ENROL_ANTE) {
            // log.d(mess, "Inside PREG");
            //---------------INSERTING CONTENTS OF PREG_PREGNANCY INTO LOCAL DB-------------
            contentValues = new ContentValues();
            contentValues.put(COL_PP_NAME, antePersonal.Name);
            contentValues.put(COL_PP_HUSBAND, antePersonal.Husband);
            contentValues.put(COL_PP_H_MEMBER, antePersonal.HusbandMem);
            contentValues.put(COL_PP_M_MEMBER, antePersonal.WomanMem);
            contentValues.put(COL_PP_DOB, antePersonal.DOB);
            contentValues.put(COL_PP_EDUCATION, antePersonal.Education);
            contentValues.put(COL_ID, id);

            db = this.getWritableDatabase();
            db.insert(TABLE_NAME_PREG_PERSONAL, null, contentValues);
            // log.d(mess, "Columns Are ->>" + COL_PP_NAME + " AND " + COL_PP_HUSBAND + " AND " + COL_PP_DOB + " AND " + COL_PP_EDUCATION);
            //db.close();

            //---------------INSERTING CONTENTS OF PREG_PREGNANCY INTO LOCAL DB-------------


            //---------------INSERTING CONTENTS OF PREGNANCY INTO LOCAL DB-------------

            contentValues = new ContentValues();
            contentValues.put(COL_PD_LMP, antePreDelivery.LMP);
            contentValues.put(COL_PD_EDD, antePreDelivery.EDD);
            contentValues.put(COL_PD_GRAVIDA, antePreDelivery.GRAVIDA);
            contentValues.put(COL_PD_PHC, antePreDelivery.PHC);
            contentValues.put(COL_PD_MONTH, antePreDelivery.MONTHPREG);
            contentValues.put(COL_ID, id);
            db = this.getWritableDatabase();
            db.insert(TABLE_NAME_PREGNANCY, null, contentValues);
            //db.close();

            // log.d(mess, "Columns Are ->>" + COL_PD_LMP + " AND " + COL_PD_EDD + " AND " + COL_PD_GRAVIDA + " AND " + COL_PD_PHC + " AND " + COL_PD_MONTH);
            // log.d(mess,"PHC->"+antePreDelivery.PHC);
            //---------------INSERTING CONTENTS OF PREGNANCY INTO LOCAL DB-------------
        }

            if(ENROL_DELL) {
                mess+="ENROL_DATA";
                // log.d(mess, "Inside DELL");
                //---------------INSERTING CONTENTS OF PREG_PREGNANCY INTO LOCAL DB-------------

                contentValues = new ContentValues();

                contentValues.put(COL_PP_NAME, pregPersonal.Name);
                contentValues.put(COL_PP_HUSBAND, pregPersonal.Husband);
                contentValues.put(COL_PP_H_MEMBER, pregPersonal.HusbandMem);
                contentValues.put(COL_PP_M_MEMBER, pregPersonal.WomanMem);
                contentValues.put(COL_PP_DOB, pregPersonal.DOB);
                contentValues.put(COL_PP_EDUCATION, pregPersonal.Education);
                contentValues.put(COL_ID, id);

                db = this.getWritableDatabase();
                db.insert(TABLE_NAME_PREG_PERSONAL, null, contentValues);
                //db.close();
                // log.d(mess, "Columns Are ->>" + COL_PP_NAME + " AND " + COL_PP_HUSBAND + " AND " + COL_PP_DOB + " AND " + COL_PP_EDUCATION);

                //---------------INSERTING CONTENTS OF PREG_PREGNANCY INTO LOCAL DB-------------


                //-------------------INSERTING CONTENTS OF DELIVERY INTO LOCAL DB---------------

                contentValues = new ContentValues();
                contentValues.put(COL_DD_PLACE, delivery.Place);
                contentValues.put(COL_DD_STATUS, delivery.Status);
                contentValues.put(COL_DD_COLO, delivery.Colostrum);
                contentValues.put(COL_DD_ABOR_MONTH, delivery.aborMonth);
                contentValues.put(COL_DD_DOD, delivery.EDD);
                contentValues.put(COL_DD_GRAVIDA, delivery.Gravida);
                contentValues.put(COL_DD_OUTCOME, delivery.Outcome);
                contentValues.put(COL_DD_TWINS, delivery.twins);
                contentValues.put(COL_ID, id);
                db = this.getWritableDatabase();
                db.insert(TABLE_NAME_DELIVERY, null, contentValues);
                //db.close();
                // log.d(mess, "Columns Are ->>" + COL_DD_PLACE + " AND " + COL_DD_STATUS + " AND " + COL_DD_COLO);

                //---------------INSERTING CONTENTS OF DELIVERY INTO LOCAL DB-------------

                String dd=delivery.Outcome;
               Log.d(mess, "Value of delivery.Outcome->"+dd);
                if((delivery.Outcome.contains("CB"))&&!deliveryApplicant.ID_WHO.equals("MOTH"))
                {
                    String MomID=id;
                     Log.d(mess, "Inside dell->CB");
                    if(delivery.twins.equals("NO")) {

                        // log.d(mess, "Inside dell->CB->twinNO");

                        random=new Random().nextInt(9999)+1;
                        id = locationFragment.FEDCODE +"C"+random.toString();
                        Log.d(mess,"child-ID->"+id);

                        //----------TRACKING EMP WHO HAS FILLED THE FORM------------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_EMP_TRACK,getEmpRegData()[0]);
                        contentValues.put(COL_ID,id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_EMP_TRACK, null, contentValues);

                        //----------TRACKING EMP WHO HAS FILLED THE FORM------------------

                        //---------------UPDATING ADDMOD TABLE ------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, id);
                        contentValues.put(COL_STATUS, "ADD");
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();

                        //---------------UPDATING ADDMOD TABLE ------------

                        //---------------UPDATING MOMCHILD TABLE ------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, MomID);
                        contentValues.put(COL_CID,id);
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + MomID + " AND " + COL_CID + id);
                        db.insert(TABLE_NAME_MOMCHILD, null, contentValues);
                        //db.close();

                        //---------------UPDATING ADDMOD TABLE ------------



                        //----------LOCATION DETAILS FOR CHILD---------

                        contentValues = new ContentValues();
                        contentValues.put(COL_L_VILLAGE, locationFragment.VILLAGE);
                        contentValues.put(COL_L_STATE, locationFragment.STATE);
                        contentValues.put(COL_L_DISTRICT, locationFragment.DISTRICT);
                        contentValues.put(COL_L_FED, locationFragment.FED);
                        contentValues.put(COL_L_FEDCODE, locationFragment.FEDCODE);
                        contentValues.put(COL_L_PANCH, locationFragment.PANCH);
                        contentValues.put(COL_ID, id);

                        db = this.getWritableDatabase();
                        // log.d(mess, "Columns Are ->>" + COL_L_FED + " AND " + COL_L_PANCH + " AND " + COL_L_VILLAGE + " AND " + COL_L_REGION + " AND " + COL_ID);
                        //// log.d(mess,"Columns Are ->>"+COL_L_FED+" AND "+COL_L_PANCH+" AND "+COL_L_VILLAGE+" AND "+COL_L_REGION+" AND "+COL_L_ID+" AND "+COL_L_SENT);
                        db.insert(TABLE_NAME_LOCATION, null, contentValues);
                        //db.close();

                        //----------LOCATION DETAILS FOR CHILD---------

                        //-------------UPDATING CONTACT NUMBER FOR CHILD------------------------
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID,id);
                        contentValues.put(COL_CONTACT,locationFragment.Contact);
                        db = this.getWritableDatabase();
                        // log.d(mess, "CONTACT TABLE Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_CONTACT + " -> " + locationFragment.Contact);
                        db.insert(TABLE_NAME_CONTACT, null, contentValues);
                        //db.close();
                        //-------------UPDATING CONTACT NUMBER FOR CHILD------------------------

                        //----------UPDATING TIMESTAMP FOR LOCAL TIME------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_ID,id);
                        contentValues.put(COL_TIME,String.valueOf(new LocalDateTime()));

                        db = this.getWritableDatabase();
                        // log.d(mess, "TimeStamp Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_TIME + " -> " + String.valueOf(new LocalDateTime()));
                        db.insert(TABLE_NAME_TIMESTAMP, null, contentValues);
                        //db.close();

                        //----------UPDATING TIMESTAMP FOR LOCAL TIME------------



                        //---------------INSERTING CONTENTS OF PARENTS INTO LOCAL DB-------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_PAR_MOM, pregPersonal.Name);
                        contentValues.put(COL_PAR_M_MEMBER, pregPersonal.WomanMem);
                        contentValues.put(COL_PAR_DOB, pregPersonal.DOB);
                        contentValues.put(COL_PAR_DAD, pregPersonal.Husband);
                        contentValues.put(COL_PAR_D_MEMBER, pregPersonal.HusbandMem);
                        contentValues.put(COL_ID, id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_NAME_PARENTS, null, contentValues);
                        //db.close();
                        // log.d(mess, "Columns Are ->>" + COL_PAR_MOM + " AND " + COL_PAR_DOB + " AND " + COL_PAR_DAD);
                        // log.d(mess,"pregPersonal.DOB->"+pregPersonal.DOB);
                        //---------------INSERTING CONTENTS OF PARENTS INTO LOCAL DB-------------


                        //---------------INSERTING CONTENTS OF CHILDBIRTH INTO LOCAL DB-------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_BIR_NAME, pregPersonal.Name+"'s Child");
                        contentValues.put(COL_BIR_SEX, childBirthS.ChildGender);
                        contentValues.put(COL_BIR_WEIGHT, childBirthS.ChildWeight);
                        contentValues.put(COL_BIR_DOB, childBirthS.ChildDOB);
                        contentValues.put(COL_BIR_COLO, childBirthS.ChildCol);
                        contentValues.put(COL_ID, id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_NAME_CHILDBIRTH, null, contentValues);
                        //db.close();
                        // log.d(mess, "Columns Are ->>" + COL_BIR_NAME + " AND " + COL_BIR_SEX + " AND " + COL_BIR_WEIGHT + " AND " + COL_BIR_DOB + " AND " + COL_BIR_COLO);

                        //---------------INSERTING CONTENTS OF CHILDBIRTH INTO LOCAL DB-------------

                        //---------------INSERTING CONTENTS OF CHILDGROWTH INTO LOCAL DB-------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_GROW_ORDER, childGrowthS.Order);
                        contentValues.put(COL_GROW_HEIGHT, childGrowthS.Height);
                        contentValues.put(COL_GROW_WEIGHT, childGrowthS.Weight);
                       // contentValues.put(COL_GROW_AGE, childGrowthS.Age);
                        contentValues.put(COL_ID, id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_NAME_CHILDGROWTH, null, contentValues);
                        //db.close();
                        // log.d(mess, "Columns Are ->>" + COL_GROW_ORDER + " AND " + COL_GROW_HEIGHT + " AND " + COL_GROW_WEIGHT );

                        //---------------INSERTING CONTENTS OF CHILDGROWTH INTO LOCAL DB-------------
                    }

                    else
                    {
                        // log.d(mess, "Inside dell->CB->TwinYes");

                        //------------------------TWIN 1------------------------------
                        random=new Random().nextInt(9999)+1;
                        id = locationFragment.FEDCODE +"C"+random.toString();
                        // log.d(mess,"Twin1-ID->"+id);

                        //----------TRACKING EMP WHO HAS FILLED THE FORM------------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_EMP_TRACK,getEmpRegData()[0]);
                        contentValues.put(COL_ID,id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_EMP_TRACK, null, contentValues);

                        //----------TRACKING EMP WHO HAS FILLED THE FORM------------------

                        //---------------UPDATING ADDMOD TABLE ------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, id);
                        contentValues.put(COL_STATUS,"ADD");
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();

                        //---------------UPDATING ADDMOD TABLE ------------

                        //---------------UPDATING MOMCHILD TABLE ------------
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, MomID);
                        contentValues.put(COL_CID,id);
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + MomID + " AND " + COL_CID + id);
                        db.insert(TABLE_NAME_MOMCHILD, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------


                        //----------LOCATION DETAILS FOR CHILD---------
                        contentValues = new ContentValues();
                        contentValues.put(COL_L_VILLAGE, locationFragment.VILLAGE);
                        contentValues.put(COL_L_STATE, locationFragment.STATE);
                        contentValues.put(COL_L_DISTRICT, locationFragment.DISTRICT);
                        contentValues.put(COL_L_FED, locationFragment.FED);
                        contentValues.put(COL_L_FEDCODE, locationFragment.FEDCODE);
                        contentValues.put(COL_L_PANCH, locationFragment.PANCH);
                        contentValues.put(COL_ID, id);

                        db = this.getWritableDatabase();
                        // log.d(mess, "Columns Are ->>" + COL_L_FED + " AND " + COL_L_PANCH + " AND " + COL_L_VILLAGE + " AND " + COL_L_REGION + " AND " + COL_ID);
                        //// log.d(mess,"Columns Are ->>"+COL_L_FED+" AND "+COL_L_PANCH+" AND "+COL_L_VILLAGE+" AND "+COL_L_REGION+" AND "+COL_L_ID+" AND "+COL_L_SENT);
                        db.insert(TABLE_NAME_LOCATION, null, contentValues);
                        //db.close();

                        //----------LOCATION DETAILS FOR CHILD---------

                        //-------------UPDATING CONTACT NUMBER FOR CHILD------------------------
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID,id);
                        contentValues.put(COL_CONTACT,locationFragment.Contact);
                        db = this.getWritableDatabase();
                        // log.d(mess, "CONTACT TABLE Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_CONTACT + " -> " + locationFragment.Contact);
                        db.insert(TABLE_NAME_CONTACT, null, contentValues);
                        //db.close();
                        //-------------UPDATING CONTACT NUMBER FOR CHILD------------------------

                        //---------------UPDATING TIMESTAMP FOR LOCAL TIME------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_ID,id);
                        contentValues.put(COL_TIME,String.valueOf(new LocalDateTime()));

                        db = this.getWritableDatabase();
                        // log.d(mess, "TimeStamp Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_TIME + " -> " + String.valueOf(new LocalDateTime()));
                        db.insert(TABLE_NAME_TIMESTAMP, null, contentValues);
                        //db.close();

                        //---------------UPDATING TIMESTAMP FOR LOCAL TIME------------



                        //---------------INSERTING CONTENTS OF PARENTS INTO LOCAL DB-------------


                        contentValues = new ContentValues();
                        contentValues.put(COL_PAR_MOM, pregPersonal.Name);
                        contentValues.put(COL_PAR_M_MEMBER, pregPersonal.WomanMem);
                        contentValues.put(COL_PAR_DOB, pregPersonal.DOB);
                        contentValues.put(COL_PAR_D_MEMBER, pregPersonal.HusbandMem);
                        contentValues.put(COL_ID, id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_NAME_PARENTS, null, contentValues);
                        //db.close();
                        // log.d(mess, "Columns Are ->>" + COL_PAR_MOM + " AND " + COL_PAR_DOB + " AND " + COL_PAR_DAD);
                        // log.d(mess,"pregPersonal.DOB->"+pregPersonal.DOB);

                        //---------------INSERTING CONTENTS OF PARENTS INTO LOCAL DB-------------


                        //---------------INSERTING CONTENTS OF CHILDBIRTH INTO LOCAL DB-------------


                        // log.d("TWIN1","SEX"+childBirth1.ChildGender);
                        contentValues = new ContentValues();
                        contentValues.put(COL_BIR_NAME, pregPersonal.Name+"'s Child");
                        contentValues.put(COL_BIR_SEX, childBirth1.ChildGender);
                        contentValues.put(COL_BIR_WEIGHT, childBirth1.ChildWeight);
                        contentValues.put(COL_BIR_DOB, childBirth1.ChildDOB);
                        contentValues.put(COL_BIR_COLO, childBirth1.ChildCol);
                        contentValues.put(COL_ID, id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_NAME_CHILDBIRTH, null, contentValues);
                        //db.close();
                        // log.d(mess, "Columns Are ->>" + COL_BIR_NAME + " AND " + COL_BIR_SEX + " AND " + COL_BIR_WEIGHT + " AND " + COL_BIR_DOB + " AND " + COL_BIR_COLO);

                        //---------------INSERTING CONTENTS OF CHILDBIRTH INTO LOCAL DB-------------

                        //---------------INSERTING CONTENTS OF CHILDGROWTH INTO LOCAL DB-------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_GROW_ORDER, childGrowth1.Order);
                        contentValues.put(COL_GROW_HEIGHT, childGrowth1.Height);
                        contentValues.put(COL_GROW_WEIGHT, childGrowth1.Weight);
                       // contentValues.put(COL_GROW_AGE, childGrowth1.Age);
                        contentValues.put(COL_ID, id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_NAME_CHILDGROWTH, null, contentValues);
                        //db.close();
                        // log.d(mess, "Columns Are ->>" + COL_GROW_ORDER + " AND " + COL_GROW_HEIGHT + " AND " + COL_GROW_WEIGHT );

                        //---------------INSERTING CONTENTS OF CHILDGROWTH INTO LOCAL DB-------------

                        //------------------------TWIN 1------------------------------



                        //------------------------TWIN 2------------------------------
                        random=new Random().nextInt(9999)+1;
                        id = locationFragment.FEDCODE + "C" +random.toString();
                        // log.d(mess,"Twin2-ID->"+id);

                        //----------TRACKING EMP WHO HAS FILLED THE FORM------------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_EMP_TRACK,getEmpRegData()[0]);
                        contentValues.put(COL_ID,id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_EMP_TRACK, null, contentValues);

                        //----------TRACKING EMP WHO HAS FILLED THE FORM------------------


                        //---------------UPDATING ADDMOD TABLE ------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, id);
                        contentValues.put(COL_STATUS,"ADD");
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_STATUS + " ->ADD");
                        db.insert(TABLE_NAME_ADDMOD, null, contentValues);
                        //db.close();

                        //---------------UPDATING ADDMOD TABLE ------------

                        //---------------UPDATING MOMCHILD TABLE ------------
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID, MomID);
                        contentValues.put(COL_CID,id);
                        db = this.getWritableDatabase();
                        // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + MomID + " AND " + COL_CID + id);
                        db.insert(TABLE_NAME_MOMCHILD, null, contentValues);
                        //db.close();
                        //---------------UPDATING ADDMOD TABLE ------------

                        //----------LOCATION DETAILS FOR CHILD---------

                        contentValues = new ContentValues();
                        contentValues.put(COL_L_VILLAGE, locationFragment.VILLAGE);
                        contentValues.put(COL_L_STATE, locationFragment.STATE);
                        contentValues.put(COL_L_DISTRICT, locationFragment.DISTRICT);
                        contentValues.put(COL_L_FED, locationFragment.FED);
                        contentValues.put(COL_L_FEDCODE, locationFragment.FEDCODE);
                        contentValues.put(COL_L_PANCH, locationFragment.PANCH);
                        contentValues.put(COL_ID, id);

                        db = this.getWritableDatabase();
                        // log.d(mess, "Columns Are ->>" + COL_L_FED + " AND " + COL_L_PANCH + " AND " + COL_L_VILLAGE + " AND " + COL_L_REGION + " AND " + COL_ID);
                        //// log.d(mess,"Columns Are ->>"+COL_L_FED+" AND "+COL_L_PANCH+" AND "+COL_L_VILLAGE+" AND "+COL_L_REGION+" AND "+COL_L_ID+" AND "+COL_L_SENT);
                        db.insert(TABLE_NAME_LOCATION, null, contentValues);
                        //db.close();

                        //----------LOCATION DETAILS FOR CHILD---------

                        //-------------UPDATING CONTACT NUMBER FOR CHILD------------------------
                        contentValues = new ContentValues();
                        contentValues.put(COL_ID,id);
                        contentValues.put(COL_CONTACT,locationFragment.Contact);
                        db = this.getWritableDatabase();
                        // log.d(mess, "CONTACT TABLE Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_CONTACT + " -> " + locationFragment.Contact);
                        db.insert(TABLE_NAME_CONTACT, null, contentValues);
                        //db.close();
                        //-------------UPDATING CONTACT NUMBER FOR CHILD------------------------

                        //---------------UPDATING TIMESTAMP FOR LOCAL TIME------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_ID,id);
                        contentValues.put(COL_TIME,String.valueOf(new LocalDateTime()));

                        db = this.getWritableDatabase();
                        // log.d(mess, "TimeStamp Values Are ->>" + COL_ID + " -> " + id + " AND " + COL_TIME + " -> " + String.valueOf(new LocalDateTime()));
                        db.insert(TABLE_NAME_TIMESTAMP, null, contentValues);
                        //db.close();

                        //---------------UPDATING TIMESTAMP FOR LOCAL TIME------------



                        //---------------INSERTING CONTENTS OF PARENTS INTO LOCAL DB-------------
                        //String[] dob = pregPersonal.DOB.split("-");
                        //int Year = Calendar.getInstance().get(Calendar.YEAR);
                        //int age = Integer.parseInt(dob[2]) - Year;
                       // // log.d(mess, "age->" + age);
                        contentValues = new ContentValues();
                        contentValues.put(COL_PAR_MOM, pregPersonal.Name);
                        contentValues.put(COL_PAR_M_MEMBER, pregPersonal.WomanMem);
                        contentValues.put(COL_PAR_DOB, pregPersonal.DOB);
                        contentValues.put(COL_PAR_D_MEMBER, pregPersonal.HusbandMem);
                        contentValues.put(COL_ID, id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_NAME_PARENTS, null, contentValues);
                        //db.close();
                        // log.d(mess, "Columns Are ->>" + COL_PAR_MOM + " AND " + COL_PAR_DOB + " AND " + COL_PAR_DAD);

                        //---------------INSERTING CONTENTS OF PARENTS INTO LOCAL DB-------------


                        //---------------INSERTING CONTENTS OF CHILDBIRTH INTO LOCAL DB-------------
                        // log.d("TWIN2","SEX"+childBirth2.ChildGender);
                        contentValues = new ContentValues();
                        contentValues.put(COL_BIR_NAME, pregPersonal.Name+"'s Child");
                        contentValues.put(COL_BIR_SEX, childBirth2.ChildGender);
                        contentValues.put(COL_BIR_WEIGHT, childBirth2.ChildWeight);
                        contentValues.put(COL_BIR_DOB, childBirth2.ChildDOB);
                        contentValues.put(COL_BIR_COLO, childBirth2.ChildCol);
                        contentValues.put(COL_ID, id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_NAME_CHILDBIRTH, null, contentValues);
                        //db.close();
                        // log.d(mess, "Columns Are ->>" + COL_BIR_NAME + " AND " + COL_BIR_SEX + " AND " + COL_BIR_WEIGHT + " AND " + COL_BIR_DOB + " AND " + COL_BIR_COLO);

                        //---------------INSERTING CONTENTS OF CHILDBIRTH INTO LOCAL DB-------------

                        //---------------INSERTING CONTENTS OF CHILDGROWTH INTO LOCAL DB-------------

                        contentValues = new ContentValues();
                        contentValues.put(COL_GROW_ORDER, childGrowth2.Order);
                        contentValues.put(COL_GROW_HEIGHT, childGrowth2.Height);
                        contentValues.put(COL_GROW_WEIGHT, childGrowth2.Weight);
                       // contentValues.put(COL_GROW_AGE, childGrowth2.Age);
                        contentValues.put(COL_ID, id);
                        db = this.getWritableDatabase();
                        db.insert(TABLE_NAME_CHILDGROWTH, null, contentValues);
                        //db.close();
                        // log.d(mess, "Columns Are ->>" + COL_GROW_ORDER + " AND " + COL_GROW_HEIGHT + " AND " + COL_GROW_WEIGHT );

                        //---------------INSERTING CONTENTS OF CHILDGROWTH INTO LOCAL DB-------------

                        //------------------------TWIN 2------------------------------


                    }

                }
            }

        if(MainActivity.ENROL_CHILD)
        {
            mess="ADD-Data->ENROL_CHILD";
            // log.d(mess,"Inside CHILD");

            contentValues = new ContentValues();
            //contentValues.put(COL_ID, childApplicant.jsonObject.get("ID").toString());
            contentValues.put(COL_ID,"U:"+Parents.MomId);
            contentValues.put(COL_CID, id);
            db = this.getWritableDatabase();
            // log.d(mess, "MOMCHILD Values Are ->>" + COL_ID + " -> " + Parents.MomId + " AND " + COL_CID + id);
            db.insert(TABLE_NAME_MOMCHILD, null, contentValues);
            //db.close();

            //---------------UPDATING MOMCHILD TABLE ------------
            /*if(childApplicant.ID_ENTERED)
            {
                try {
                contentValues = new ContentValues();
                //contentValues.put(COL_ID, childApplicant.jsonObject.get("ID").toString());
                contentValues.put(COL_ID,"U:"+Parents.MomId);
                contentValues.put(COL_CID, id);
                db = this.getWritableDatabase();
                // log.d(mess, "ADDMOD Values Are ->>" + COL_ID + " -> " + Parents.MomId + " AND " + COL_CID + id);
                db.insert(TABLE_NAME_MOMCHILD, null, contentValues);
            }catch (JSONException j)
            {
                j.printStackTrace();
            }

            }
            else if(!childApplicant.ID_ENTERED) {
            //---------------UPDATING MOMCHILD TABLE ------------
                contentValues = new ContentValues();
                contentValues.put(COL_ID,"U:"+Parents.MomId);
                contentValues.put(COL_CID, id);
                db = this.getWritableDatabase();
                Log.d(mess, "MOMCHILD Values Are ->>" + COL_ID + " -> " + Parents.MomId + " AND " + COL_CID + id);
                db.insert(TABLE_NAME_MOMCHILD, null, contentValues);
            }
*/
            //---------------UPDATING MOMCHILD TABLE ------------

            //---------------INSERTING CONTENTS OF PARENTS INTO LOCAL DB-------------

            contentValues = new ContentValues();
            contentValues.put(COL_PAR_MOM, Parents.MomName);
            contentValues.put(COL_PAR_M_MEMBER, Parents.MomMem);
            contentValues.put(COL_PAR_DOB, Parents.MomDoB);
            contentValues.put(COL_PAR_DAD, Parents.FatherName);
            contentValues.put(COL_PAR_D_MEMBER, Parents.DadMem);
            contentValues.put(COL_ID, id);
            db = this.getWritableDatabase();
            db.insert(TABLE_NAME_PARENTS, null, contentValues);
            //db.close();
            // log.d(mess, "Columns Are ->>" + COL_PAR_MOM + " AND " + COL_PAR_DOB + " AND " + COL_PAR_DAD );

            //---------------INSERTING CONTENTS OF PARENTS INTO LOCAL DB-------------


            //---------------INSERTING CONTENTS OF CHILDBIRTH INTO LOCAL DB-------------

            contentValues = new ContentValues();
            contentValues.put(COL_BIR_NAME,childBirth.ChildName );
            contentValues.put(COL_BIR_SEX,childBirth.ChildGender );
            contentValues.put(COL_BIR_WEIGHT,childBirth.ChildWeight );
            contentValues.put(COL_BIR_DOB, childBirth.ChildDOB);
            contentValues.put(COL_BIR_COLO,childBirth.ChildCol );
            contentValues.put(COL_ID, id);
            db = this.getWritableDatabase();
            db.insert(TABLE_NAME_CHILDBIRTH, null, contentValues);
            //db.close();
            // log.d(mess, "Columns Are ->>" + COL_BIR_NAME + " AND " + COL_BIR_SEX + " AND " + COL_BIR_WEIGHT+ " AND " +COL_BIR_DOB+ " AND " +COL_BIR_COLO );

            //---------------INSERTING CONTENTS OF CHILDBIRTH INTO LOCAL DB-------------

            //---------------INSERTING CONTENTS OF CHILDGROWTH INTO LOCAL DB-------------

            contentValues = new ContentValues();
            contentValues.put(COL_GROW_ORDER,childGrowth.Order );
            contentValues.put(COL_GROW_HEIGHT,childGrowth.Height );
            contentValues.put(COL_GROW_WEIGHT,childGrowth.Weight );
            //contentValues.put(COL_GROW_AGE,childGrowth.Age );
            contentValues.put(COL_ID, id);
            db = this.getWritableDatabase();
            db.insert(TABLE_NAME_CHILDGROWTH, null, contentValues);
            //db.close();
            // log.d(mess, "Columns Are ->>" + COL_GROW_ORDER + " AND " + COL_GROW_HEIGHT + " AND " + COL_GROW_WEIGHT );

            //---------------INSERTING CONTENTS OF CHILDGROWTH INTO LOCAL DB-------------

        }

        //db.close();

    }

    public boolean rowPresent()
    {
        Cursor cursor;

        String countQuery="Select * from "+ TABLE_NAME_LOCATION;
        // log.d("ROW-PRESENT","QUERY is->"+countQuery);
        SQLiteDatabase db=this.getReadableDatabase();
       try
       {
            cursor=db.rawQuery(countQuery,null);
          // //db.close();

       }catch (Exception e)
       {
           return false;
       }

        if(cursor.getCount()<=0)
        {
            cursor.close();
            return false;
        }
        cursor.close();
        //db.close();
        return true;
    }

    public String[] getIds()
    {
        String mess="GET IDS";
        String query="SELECT "+COL_ID+" FROM "+TABLE_NAME_LOCATION ;//+" where "+COL_L_SENT+" = ?";
       // String query="SELECT "+COL_L_ID+" FROM "+TABLE_NAME+" where "+COL_L_SENT+" = ?";
        // log.d(mess,"QUERY IS->"+query);
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
       // Cursor cursor=db.rawQuery(query,new String[]{"N"});
        String StoreID[]=new String[cursor.getCount()];
        for (int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToNext();
            // log.d(mess,"Cursor is pointing at->"+String.valueOf(cursor.getString(0)));
            StoreID[i]=String.valueOf(cursor.getString(0));
           // updateSentStatus(StoreID[i],"Y");
        }
        cursor.close();
        //db.close();
        return StoreID;
    }

  /*  public void updateSentStatus(String id,String sent)
    {
       // String query="UPDATE "+COL_L_ID+" FROM "+TABLE_NAME+";";
        ContentValues content= new ContentValues();
        content.put(COL_L_SENT,sent);
        SQLiteDatabase db=this.getReadableDatabase();
        db.update(TABLE_NAME,content,COL_L_ID+"=?",new String[]{id});
        //db.close();
    }
*/

    public String[] uploadData(String id) {

        String mess="UPLOAD-DATA";

       // String result = "";
        //String query = "Select * from " + TABLE_NAME_LOCATION +" where "+COL_ID+" = ?";
        String query = "select L.STATE ,L.DISTRICT,L.FED_CODE, L.PANCHAYAT,L.FEDERATION," +
                "L.VILLAGE,L.ID,P.GNAME,P.MOTHER,P.FATHER,P.DOB," +
                "P.M_MEMBER,P.F_MEMBER,P.MARRIAGE_YEAR," +
                "E.EDUCATION,E.WORK,E.STUDY,H.YEAR_PUBERTY,H.HB,H.HEIGHT,H.WEIGHT,T.TIME,AM.STATUS,GG.GROUPNAME,GG.GROUPTYPE,C.CONTACT,TR.OLD,ET.EMPID FROM LOCATIONTABLE L JOIN PERSONALTABLE P " +
                "ON L.ID=P.ID JOIN HEALTHTABLE H ON H.ID=L.ID  JOIN EDUCATIONTABLE E ON E.ID=L.ID JOIN CONTACT C ON C.ID=L.ID " +
                "JOIN GIRLGROUP GG ON GG.ID=L.ID JOIN TIMESTAMP T ON T.ID=L.ID " +
                "JOIN ADDMOD AM ON AM.ID=L.ID JOIN EMPTRACK ET ON ET.ID=L.ID LEFT JOIN TRANSITION TR on TR.NEW=L.ID where L."+COL_ID+"=?";


        final int  COL_LENGTH=28;
        final int  COL_LENGTH_DELL=26;
        final int  COL_LENGTH_CHILD=26;
        final int  COL_LENGTH_ANTE=23;

        // log.d(mess,"QUERY IS->"+query);
        // log.d(mess,"ID IS->"+id);
        SQLiteDatabase db = this.getWritableDatabase();
        String args[]=new String[1];
        args[0]=id;
        Cursor cursor;
        String result[];
        cursor= db.rawQuery(query, args);
        result=new String[COL_LENGTH+1];
        result[0]="GIRL";


      //  String result[]=new String[5];

        //CODE for Involving support for PREG & GIRL
        if (cursor.getCount()==0) {
            // log.d(mess, "Girl Cursor is empty");

            String PREGquery =  "select L.STATE ,L.DISTRICT,L.FED_CODE, L.PANCHAYAT,L.FEDERATION,L.VILLAGE,L.ID,"+
                    "P.NAME,P.M_MEMBER,P.HUSBAND,P.H_MEMBER, P.DOB ,P.EDUCATION ," +
                    "PG.LMP ,PG.EDD ,PG.GRAVIDA ,PG.WHEN_REGISTERED ,PG.MONTH_PREGNANT,T.TIME,AM.STATUS,C.CONTACT,TR.OLD,ET.EMPID" +
                    " FROM LOCATIONTABLE L JOIN PREGPERSONAL P ON L.ID=P.ID JOIN PREGNANCY PG " +
                    "ON PG.ID=L.ID JOIN TIMESTAMP T ON T.ID=L.ID JOIN ADDMOD AM ON AM.ID=L.ID JOIN CONTACT C ON C.ID=L.ID " +
                    "JOIN EMPTRACK ET ON ET.ID=L.ID "+
                   "LEFT JOIN TRANSITION TR on TR.NEW=L.ID WHERE L." + COL_ID + "=?";

            // log.d(mess, "QUERY IS->" + PREGquery);
            cursor = db.rawQuery(PREGquery, args);
            result = new String[COL_LENGTH_ANTE + 1];
            result[0] = "PREG";

            if ((cursor.getCount() == 0)) {
                // log.d(mess, "PREG Cursor is empty");
                query = "select L.STATE ,L.DISTRICT,L.FED_CODE, L.PANCHAYAT,L.FEDERATION,L.VILLAGE,L.ID,"+
                        "P.NAME,P.M_MEMBER,P.HUSBAND,P.H_MEMBER, P.DOB ,P.EDUCATION ," +
                        "D.PLACE,D.STATUS,D.COLOSTRUM,D.TWINS,D.OUTCOME,D.ABORTION_MONTH,D.DOD,D.GRAVIDA,T.TIME,AM.STATUS,C.CONTACT,TR.OLD,ET.EMPID"+
                        " FROM LOCATIONTABLE L JOIN PREGPERSONAL P ON L.ID=P.ID JOIN DELIVERY D ON D.ID=L.ID JOIN TIMESTAMP T ON T.ID=L.ID JOIN ADDMOD AM ON AM.ID=L.ID " +
                        "JOIN CONTACT C ON C.ID=L.ID JOIN EMPTRACK ET ON ET.ID=L.ID LEFT JOIN TRANSITION TR on TR.NEW=L.ID WHERE L." + COL_ID + "=?";


                // log.d(mess, "QUERY IS->" + query);
                cursor = db.rawQuery(query, args);
                result = new String[COL_LENGTH_DELL + 1];
                result[0] = "DELL";

            if ((cursor.getCount() == 0)) {
                 Log.d(mess, "DEL Cursor is empty");

                String CHILDquery ="select L.STATE ,L.DISTRICT,L.FED_CODE, L.PANCHAYAT,L.FEDERATION,L.VILLAGE,L.ID,"+
                        "P.MOM ,P.M_MEMBER,P.MOM_DOB ,P.DAD,P.D_MEMBER," +
                        "CB.NAME ,CB.SEX ,CB.WEIGHT ,CB.DOB ,CB.COLOSTRUM," +
                        "CG.ORDERBIRTH ,CG.HEIGHT ,CG.WEIGHT,T.TIME,AM.STATUS,MC.ID,C.CONTACT,TR.OLD,ET.EMPID FROM" +
                        " LOCATIONTABLE L JOIN PARENTS P ON L.ID=P.ID JOIN CHILDBIRTH CB ON CB.ID=L.ID JOIN CHILDGROWTH CG " +
                        "ON CG.ID=L.ID JOIN TIMESTAMP T ON T.ID=L.ID JOIN ADDMOD AM ON AM.ID=L.ID JOIN MOMCHILD MC ON MC.CID=L.ID " +
                        "JOIN CONTACT C ON C.ID=L.ID JOIN EMPTRACK ET ON ET.ID=L.ID LEFT JOIN TRANSITION TR on TR.NEW=L.ID WHERE L." + COL_ID + "=?";

                // log.d(mess, "QUERY IS->" + CHILDquery);

                cursor = db.rawQuery(CHILDquery, args);
                result = new String[COL_LENGTH_CHILD + 1];
                result[0] = "CHILD";

                if ((cursor.getCount() == 0)) {
                    // log.d(mess, "CHILD Cursor is empty");
                    return null;

                } //else
                    // log.d(mess, "CHILD Cursor is NOT empty");

            } //else
               // // log.d(mess, "ANTE Cursor is NOT empty");
        }
            //else
               // // log.d(mess, "PREG Cursor is NOT empty");
        }
       // else
            //// log.d(mess, "Girl Cursor is NOT empty");


        //String resultPREG[]=new String[COL_LENGTH_PREG];
        //String resultCHILD[]=new String[COL_LENGTH_CHILD];

        //cursor.moveToFirst();
            while (cursor.moveToNext()) {
               // // log.d(mess, "Entered cursor extraction");
                for (int i = 1; i < result.length; i++) {
                    result[i] = String.valueOf(cursor.getString(i-1));
                  //  // log.d(mess, "COL is->" + result[i]);
                }
            }

        cursor.close();
       // //db.close();
        return result;

    }

    public void deleteData(String id)
    {
        // log.d("DELETE DATA","ID to be deleted is->"+id);
        SQLiteDatabase db = this.getWritableDatabase();
        //String query = "delete from " + TABLE_NAME +" where COL_L_ID="+id+";";
        String arg[]=new String[1];
        arg[0]=id;
        db.delete(TABLE_NAME_LOCATION,COL_ID+"=?",arg);
        db.delete(TABLE_NAME_PERSONAL,COL_ID+"=?",arg);
        db.delete(TABLE_NAME_HEALTH,COL_ID+"=?",arg);
        db.delete(TABLE_NAME_EDUCATION,COL_ID+"=?",arg);

        db.delete(TABLE_NAME_PREG_PERSONAL,COL_ID+"=?",arg);
        db.delete(TABLE_NAME_PREGNANCY,COL_ID+"=?",arg);
        db.delete(TABLE_NAME_DELIVERY,COL_ID+"=?",arg);

        db.delete(TABLE_NAME_PARENTS,COL_ID+"=?",arg);
        db.delete(TABLE_NAME_CHILDBIRTH,COL_ID+"=?",arg);
        db.delete(TABLE_NAME_CHILDGROWTH,COL_ID+"=?",arg);

        db.delete(TABLE_NAME_TIMESTAMP,COL_ID+"=?",arg);

        db.delete(TABLE_NAME_ADDMOD,COL_ID+"=?",arg);

        db.delete(TABLE_NAME_MOMCHILD,COL_CID+"=?",arg);

        db.delete(TABLE_NAME_CONTACT,COL_ID+"=?",arg);

        db.delete(TABLE_EMP_TRACK,COL_ID+"=?",arg);

        db.delete(TABLE_NAME_TRANSITION,COL_NEW+"=?",arg);

       // //db.close();

    }

    public void deleteEmpReg()
    {
        // log.d("DELETE EmpReg->","Lets Delete Emp");
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_EMPREG,null,null);
        ////db.close();

    }

}

