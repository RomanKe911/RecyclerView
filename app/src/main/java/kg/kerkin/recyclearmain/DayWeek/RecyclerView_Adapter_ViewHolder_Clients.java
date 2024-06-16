package kg.kerkin.recyclearmain.DayWeek;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kg.kerkin.recyclearmain.ListAdapterSimple_Brends;
import kg.kerkin.recyclearmain.R;
import kg.kerkin.recyclearmain.RecyclerView_Adapter_ViewHolder_Brends;

public class RecyclerView_Adapter_ViewHolder_Clients
        extends RecyclerView.Adapter<RecyclerView_Adapter_ViewHolder_Clients.ViewHolder> {


    public interface OnStateClickListener {
        void onStateClick(ListAdapterSimple_Klients clientClick, int position);
    }

    public interface OnStateCheckClick {
        void onStateCheckClick(ListAdapterSimple_Klients lientClick, int position, boolean isChecked);
    }



    private final LayoutInflater inflater;
    private final List<ListAdapterSimple_Klients> imagelist;    
    List<ListAdapterSimple_Klients> filterList;
    List<ListAdapterSimple_Klients> filterListFull;
    private final OnStateCheckClick onStateCheckClick;
    private final SparseBooleanArray checkBoxStateArray = new SparseBooleanArray();

    public void toggleSelection(boolean isChecked) {
        Log.e(logeTAG, "ClickSize: " + imagelist.size());
        if (imagelist.size() > 0) {
            int i = 0;
            checkBoxStateArray.clear();
            while (i < imagelist.size()) {
                imagelist.get(i).setSelected(isChecked);
                checkBoxStateArray.put(i, isChecked);
                Log.e(logeTAG, "Click: " + isChecked + "__" + i + "-" + imagelist.get(i).getName());
                i++;
            }
            Log.e(logeTAG, "ClickNon: ");
        }
        //don't forget to notify
        notifyDataSetChanged();
    }

    List<Integer> imageList = new ArrayList<>();

    private final OnStateClickListener onClickListener;

    String logeTAG = "RecAdapter";
    Context context;
    private static final String APP_PREFERENCES = "kg.roman.Mobile_Torgovla_preferences";
    SharedPreferences mSettings;

    public RecyclerView_Adapter_ViewHolder_Clients(Context context,
                                                   List<ListAdapterSimple_Klients> backup_list,
                                                   OnStateClickListener onClickListener,
                                                   OnStateCheckClick onStateCheckClick) {
        this.context = context;
        this.imagelist = backup_list;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
        this.filterList = backup_list;
        filterListFull = new ArrayList<>(backup_list);
        this.onStateCheckClick = onStateCheckClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mt_list_klients_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ListAdapterSimple_Klients listID = imagelist.get(position);
        holder.clientUID.setText(listID.getUID());
        holder.clientName.setText(listID.getName());
        holder.clientAdress.setText(listID.getAdress());
        holder.clientINN.setText(listID.getClintINN());

        holder.clientName.setOnClickListener(v -> onClickListener.onStateClick(listID, position));
        mSettings = context.getApplicationContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String dayWeekWork = mSettings.getString("dayWeekWork", "");  // Переменая номер накладной
        Log.e("TAG", "onBindViewHolder: Click" );

        if (listID.getDayWeek() != null && listID.getDayWeek().contains(dayWeekWork))
        {
            holder.checkBox.setChecked(true);
            checkBoxStateArray.put(position, true);
        }


        if (!checkBoxStateArray.get(position, false)) {//checkbox unchecked.
            holder.checkBox.setChecked(false);
        } else {//checkbox checked
            holder.checkBox.setChecked(true);
        }


        holder.checkBox.setOnClickListener(v -> {
            boolean status;
            if (!checkBoxStateArray.get(position, false)) {
                holder.checkBox.setChecked(true);
                checkBoxStateArray.put(position, true);
                status = true;
            } else {
                holder.checkBox.setChecked(false);
                checkBoxStateArray.put(position, false);
                status = false;
            }

            onStateCheckClick.onStateCheckClick(listID, position, status);
        });


    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView clientUID, clientName, clientAdress, clientINN;
        final CheckBox checkBox;

        ViewHolder(View view) {
            super(view);
            clientName = view.findViewById(R.id.listDayWeek_ClientName);
            clientUID = view.findViewById(R.id.listDayWeek_ClientUID);
            clientAdress = view.findViewById(R.id.listDayWeek_ClientAdress);
            clientINN = view.findViewById(R.id.listDayWeek_INN);
            checkBox = view.findViewById(R.id.listDayWeek_CheckBox);
        }
    }
}