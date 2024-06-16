package kg.kerkin.recyclearmain.DayWeek;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import kg.kerkin.recyclearmain.PreferencesWrite;

public class Async_ViewModel_Clients extends AndroidViewModel {

    private static final String APP_PREFERENCES = "kg.roman.Mobile_Torgovla_preferences";
    SharedPreferences mSettings;

    String logeTAG = "ViewModel_Clients";

    public Async_ViewModel_Clients(@NonNull Application application) {
        super(application);
        // Context context = getApplication().getApplicationContext();
    }


    private MutableLiveData<ArrayList<ListAdapterSimple_Klients>> livedata_simple;

    public LiveData<ArrayList<ListAdapterSimple_Klients>> getValues() {
        if (livedata_simple == null)
            livedata_simple = new MutableLiveData<>();
        return livedata_simple;
    }

    /////////////////////////
    private MutableLiveData<String> messegeStatus = new MutableLiveData<>("");

    public LiveData<String> getMessegeStatus() {
        if (messegeStatus == null)
            messegeStatus = new MutableLiveData<>(null);
        return messegeStatus;
    }
    ////////////////////////


    ////////////////////////
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public LiveData<Boolean> getLoadingStatus() {
        if (isLoading == null)
            isLoading = new MutableLiveData<>(false);
        return isLoading;
    }
    ////////////////////////

    public void execute() {
        ArrayList<ListAdapterSimple_Klients> arrayList_clients = new ArrayList<>();
        isLoading.postValue(true);
        Runnable runnable = () -> {
            try {

                mSettings = getApplication().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

                String agent, region;
                agent = mSettings.getString("PEREM_AG_UID", "");  // Переменая номер накладной
                region = mSettings.getString("PEREM_AG_UID_REGION", "");  // Переменая номер накладной


                String dayWeekWork = mSettings.getString("dayWeekWork", "");  // Переменая номер накладной

                Log.e(logeTAG, "Загрузка потока данными");
                //  pDialog.setMessage("Загрузка продуктов. Подождите...");

                SQLiteDatabase db = getApplication().getBaseContext().openOrCreateDatabase("sunbell_base_db.db3", MODE_PRIVATE, null);
               // String query = "SELECT * FROM const_contragents WHERE uid_agent = '" + agent + "' AND roaduid = '" + region + "'";
                String query = "SELECT const_contragents.roadname, const_contragents.uid_agent, const_contragents.k_agent, " +
                        "const_contragents.uid_k_agent, const_contragents.adress, const_contragents.client_inn, base_workdayweek.day_week  \n" +
                        "FROM const_contragents\n" +
                        "LEFT JOIN base_workdayweek ON const_contragents.uid_k_agent = base_workdayweek.client_uid";
                final Cursor cursor = db.rawQuery(query, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String uid = cursor.getString(cursor.getColumnIndexOrThrow("uid_k_agent")); // код клиента
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("k_agent"));
                    String adress = cursor.getString(cursor.getColumnIndexOrThrow("adress"));
                    String dayWeek = cursor.getString(cursor.getColumnIndexOrThrow("day_week"));
                 //   String roadname = cursor.getString(cursor.getColumnIndexOrThrow("roadname")); // имя группы
                 //   String roadUid = cursor.getString(cursor.getColumnIndexOrThrow("roaduid")); // код папки группы

                    Integer im = getApplication().getResources().getIdentifier("user_shop_05", "drawable", getApplication().getPackageName());
                  //  arrayList_clients.add(new ListAdapterSimple_Klients(name, uid, adress, im, SQL_Debet_List(uid)));
                    arrayList_clients.add(new ListAdapterSimple_Klients(name, uid, adress, "", dayWeek));
                    livedata_simple.postValue(arrayList_clients);
                    Log.e(logeTAG, "client: "+name);
                    cursor.moveToNext();
                    Thread.sleep(10);
                }
                cursor.close();
                db.close();
                Log.e(logeTAG, "Загрузка потока данными, конец");
            } catch (Exception es) {
                Log.e(logeTAG, "Ошибка, не возможно отобразить список данных: " + es.getMessage());
                messegeStatus.postValue("Ошибка, не возможно отобразить список данных");
/*                backup_list.clear();
                backup_livedata.postValue(backup_list);*/
            }

            isLoading.postValue(false);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


    protected String SQL_Debet_List(String l_uid) {
        String debet = "";
        try {
            PreferencesWrite preferencesWrite = new PreferencesWrite(getApplication());

            SQLiteDatabase db_debet = getApplication().getBaseContext().openOrCreateDatabase(preferencesWrite.PEREM_DB3_RN, MODE_PRIVATE, null);
            String query_debet = "SELECT * FROM otchet_debet WHERE d_kontr_uid = '" + l_uid + "' AND d_summa > 0;";
            final Cursor cursor_debet = db_debet.rawQuery(query_debet, null);
            cursor_debet.moveToFirst();
            if (cursor_debet.getCount() > 0 & cursor_debet != null) {
                String summa = cursor_debet.getString(cursor_debet.getColumnIndexOrThrow("d_summa")); // код клиента
                debet = summa;
            } else debet = "нет задолжностей";
            cursor_debet.close();
            db_debet.close();
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Ошибка задолжностей", Toast.LENGTH_SHORT).show();
            Log.e(logeTAG, "Ошибка задолжностей");
            debet = "ОШИБКА ЗАГРУЗКА ДОЛГОВ";
        }

        return debet;
    }


}