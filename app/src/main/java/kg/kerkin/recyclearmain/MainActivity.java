package kg.kerkin.recyclearmain;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import kg.kerkin.recyclearmain.databinding.ActivityFormaBrendsforAgentsBinding;

public class MainActivity extends AppCompatActivity {

    RecyclerView_Adapter_ViewHolder_Agents adapterAgents;
    RecyclerView_Adapter_ViewHolder_Brends adapterBrends;
    ArrayList<ListAdapterSimple_Agents> listAgents = new ArrayList<>();
    ArrayList<ListAdapterSimple_Brends> listBrends = new ArrayList<>();
    private ActivityFormaBrendsforAgentsBinding binding;
    private static final String APP_PREFERENCES = "kg.roman.Mobile_Torgovla_preferences";
    SharedPreferences mSettings;
    public Context context_Activity;
    StringBuilder stringBuilder = new StringBuilder();
    SortedMap<String, String> arrayList = new TreeMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFormaBrendsforAgentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(android.R.drawable.arrow_down_float);
        getSupportActionBar().setTitle("Форма для создания брендов");
        getSupportActionBar().setSubtitle("");

        mSettings = getApplication().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        context_Activity = getApplication().getBaseContext();
        CreateAdapterAgents();

        binding.tvwAgentName.setText("выбор агента для брендов");

        listBrends.clear();
        Loading_Adapter();
        adapterBrends = new RecyclerView_Adapter_ViewHolder_Brends(getBaseContext(), listBrends, null, OnCheckClockPosition());
        binding.recyclerView2.setAdapter(adapterBrends);
        adapterBrends.notifyDataSetChanged();

        binding.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            stringBuilder = new StringBuilder();
            arrayList.clear();
            adapterBrends.toggleSelection(isChecked);

            if (isChecked) {
                binding.textView.setText("Все бренды");
                for (ListAdapterSimple_Brends sl : listBrends) {
                    arrayList.put(sl.prefix, sl.brend);
                    stringBuilder.append(sl.prefix).append(",");
                }
            } else {
                binding.textView.setText("");
                stringBuilder = new StringBuilder();
                arrayList.clear();
            }
            binding.tvwBrendName.setText(stringBuilder);

        });

    }

    private void CreateAdapterAgents() {
        listAgents.clear();

        listAgents.add(new ListAdapterSimple_Agents("Roman", "uid-1"));
        listAgents.add(new ListAdapterSimple_Agents("Lena", "uid-2"));
        listAgents.add(new ListAdapterSimple_Agents("Lena2", "uid-2"));
        listAgents.add(new ListAdapterSimple_Agents("Lenae", "uid-2"));
        listAgents.add(new ListAdapterSimple_Agents("Lenfa", "uid-2"));
        listAgents.add(new ListAdapterSimple_Agents("Lenfa", "uid-2"));
        listAgents.add(new ListAdapterSimple_Agents("Lenfa", "uid-2"));
        listAgents.add(new ListAdapterSimple_Agents("Lenfa", "uid-2"));

        Log.e("TEST", "CreateAdapterAgentsCount: " + listAgents.size());
        adapterAgents = new RecyclerView_Adapter_ViewHolder_Agents(getBaseContext(), listAgents, OnClickAgentPosition());
        binding.recyclerViewAgents.setAdapter(adapterAgents);
        adapterAgents.notifyDataSetChanged();
    }

    ///// Функция нажатия на позицию, Редактирование
    protected RecyclerView_Adapter_ViewHolder_Agents.OnStateClickListener OnClickAgentPosition() {
        return (list_zakaz, position) ->
            binding.tvwAgentName.setText(list_zakaz.name);
    }


    protected RecyclerView_Adapter_ViewHolder_Brends.OnStateCheckClick OnCheckClockPosition() {
        return (list, position, isChecked) ->
        {
            stringBuilder = new StringBuilder();
            if (isChecked) {
                Log.e("Click", "Click");
                arrayList.put(list.prefix, list.brend);
            } else {
                Log.e("NoClick", "NoClick");
                arrayList.remove(list.prefix, list.brend);
            }
            for (Map.Entry<String, String> map : arrayList.entrySet()) {
                Log.e("Test", map.getKey() + ", ");
                stringBuilder.append(map.getKey()).append(",");
            }
            binding.tvwBrendName.setText(stringBuilder);
        };
    }


    protected void Loading_Adapter() {
        try {
            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("sunbell_base_db.db3", MODE_PRIVATE, null);

            String query = "SELECT * FROM base_in_brends_id ORDER BY brends ASC;";
            final Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String brend = cursor.getString(cursor.getColumnIndexOrThrow("brends"));
                String prefix = cursor.getString(cursor.getColumnIndexOrThrow("prefic"));
                listBrends.add(new ListAdapterSimple_Brends(brend, prefix));
                cursor.moveToNext();
            }

            cursor.close();
            db.close();
        } catch (Exception e) {
            Toast.makeText(context_Activity, "Ошибка обработка адаптера", Toast.LENGTH_SHORT).show();
            Log.e("Test", "Ошибка обработка адаптера");
        }

    }
}