package kg.kerkin.recyclearmain.DayWeek;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import kg.kerkin.recyclearmain.R;
import kg.kerkin.recyclearmain.RecyclerView_Adapter_ViewHolder_Agents;
import kg.kerkin.recyclearmain.RecyclerView_Adapter_ViewHolder_Brends;
import kg.kerkin.recyclearmain.databinding.ActivityFormaBrendsforAgentsBinding;
import kg.kerkin.recyclearmain.databinding.ActivityRouteDateAgentBinding;

public class RouteDateAgent extends AppCompatActivity implements CustomSpinner.OnSpinnerEventsListener{
    public ArrayList<SpinnerAdapterSimple_DayWeek> adapterSimpleDayWeek = new ArrayList<>();
    private ActivityRouteDateAgentBinding binding;
    RecyclerView_Adapter_ViewHolder_Clients adapter_clients;
    Async_ViewModel_Clients model;
    public String name_klients, name_adress, name_uid;
    TextView textViewIncludeClient, textViewIncludeClientUID, textViewIncludeClientAdress;
    ConstraintLayout panelSelectClient;
    StringBuilder stringBuilder = new StringBuilder();
    SortedMap<String, String> arrayList = new TreeMap<>();
    public SharedPreferences mSettings;
    public SharedPreferences.Editor ed;
    private static final String APP_PREFERENCES = "kg.roman.Mobile_Torgovla_preferences";

    String getName_DayWeek = "Понедельник";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_route_date_agent);
        binding = ActivityRouteDateAgentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        name_klients = null;
        name_uid = null;
        name_adress = null;


        model = new ViewModelProvider(this).get(Async_ViewModel_Clients.class);
        model.getValues().observe(this, list_clients ->
        {
            RecyclerView_Adapter_ViewHolder_Clients.OnStateClickListener stateClickListener = (clientClick, position) -> {
                name_klients = clientClick.getName();
                name_uid = clientClick.getUID();
                name_adress = clientClick.getAdress().replaceAll("\\s+$", "");

/*                panelSelectClient.setVisibility(View.VISIBLE);
                textViewIncludeClient.setText(name_klients);
                textViewIncludeClientAdress.setText(name_adress);
                textViewIncludeClientUID.setText(name_uid);*/
                Log.e("LogeError", "контрагент: " + name_klients);
            };


            adapter_clients = new RecyclerView_Adapter_ViewHolder_Clients(getBaseContext(), list_clients, stateClickListener, OnCheckClick());
            binding.workDayRecyclerViewWeek.setAdapter(adapter_clients);
            adapter_clients.notifyDataSetChanged();
        });
        model.getLoadingStatus().observe(this, status ->
        {
            if (status == true) {
                binding.workDayProgressBarViewWeek.setVisibility(View.VISIBLE);
            } else binding.workDayProgressBarViewWeek.setVisibility(View.INVISIBLE);
        });
        model.execute();


        adapterSimpleDayWeek.clear();
/*        adapterSimpleDayWeek.add(new SpinnerAdapterSimple_DayWeek("Понедельник"));
        adapterSimpleDayWeek.add(new SpinnerAdapterSimple_DayWeek("Вторник"));
        adapterSimpleDayWeek.add(new SpinnerAdapterSimple_DayWeek("Среда"));
        adapterSimpleDayWeek.add(new SpinnerAdapterSimple_DayWeek("Четверг"));
        adapterSimpleDayWeek.add(new SpinnerAdapterSimple_DayWeek("Пятница"));
        adapterSimpleDayWeek.add(new SpinnerAdapterSimple_DayWeek("Суббота"));
        adapterSimpleDayWeek.add(new SpinnerAdapterSimple_DayWeek("Воскресенье"));*/

        for (String mass : getResources().getStringArray(R.array.mass_DayWeek))
            adapterSimpleDayWeek.add(new SpinnerAdapterSimple_DayWeek(mass));


        binding.workDaySpinnerWeek.setSpinnerEventsListener(this);
        SpinnerAdapter_DayWeek adapterDayWeek = new SpinnerAdapter_DayWeek(RouteDateAgent.this, adapterSimpleDayWeek);
        binding.workDaySpinnerWeek.setAdapter(adapterDayWeek);


        binding.workDaySpinnerWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.e("loge", "onItemSelected: " + getResources().getStringArray(R.array.mass_DayWeek)[position]);
                getName_DayWeek = getResources().getStringArray(R.array.mass_DayWeek)[position];
                mSettings = getApplication().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                ed = mSettings.edit();
                ed.putString("dayWeekWork", getName_DayWeek);
                ed.commit();
                model.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



/*        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }


    protected RecyclerView_Adapter_ViewHolder_Clients.OnStateCheckClick OnCheckClick() {
        return (list, position, isChecked) ->
        {
            SQLiteDatabase db = getApplication().getBaseContext().openOrCreateDatabase("sunbell_base_db.db3", MODE_PRIVATE, null);

            stringBuilder = new StringBuilder();
            if (isChecked) {
                Log.e("Click", "Click");
                arrayList.put(list.name, list.adress);
                ContentValues contentValues = new ContentValues();
                contentValues.put("day_week", getName_DayWeek);
                contentValues.put("agent_name", "Керкин Роман");
                contentValues.put("agent_uid", "uid---");
                contentValues.put("client_name", list.getName());
                contentValues.put("client_inn", list.getClintINN());
                contentValues.put("client_adress", list.getAdress());
                contentValues.put("client_uid", list.getUID());
                db.insert("base_workdayweek", null, contentValues);

            } else {
                Log.e("NoClick", "NoClick");
                arrayList.remove(list.name, list.adress);
                db.delete("base_workdayweek", "agent_uid = ?", new String[]{list.getUID()});
            }
            for (Map.Entry<String, String> map : arrayList.entrySet()) {
                Log.e("Test", map.getKey() + ", ");
                stringBuilder.append(map.getKey()).append(",");
            }
            //binding.tvwBrendName.setText(stringBuilder);
            Log.e("Client", "onCreate: " + stringBuilder);
            db.close();


        };
    }


    protected void SpinnerAdapter()
    {

    }

    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        binding.workDaySpinnerWeek.setBackground(getResources().getDrawable(R.drawable.spinner_click_up));
    }

    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        binding.workDaySpinnerWeek.setBackground(getResources().getDrawable(R.drawable.spinner_click_dowm));

    }
}