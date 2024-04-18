package kg.kerkin.recyclearmain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.List;

public class RecyclerView_Adapter_ViewHolder_Brends
        extends RecyclerView.Adapter<RecyclerView_Adapter_ViewHolder_Brends.ViewHolder> {


    public interface OnStateClickListener {
        void onStateClick(ListAdapterSimple_Brends clientClick, int position);
    }

    public interface OnStateCheckClick {
        void onStateCheckClick(ListAdapterSimple_Brends clientClick, int position, boolean isChecked);
    }

    private final LayoutInflater inflater;
    private final List<ListAdapterSimple_Brends> listSimple;

    private final OnStateClickListener onClickListener;
    private final OnStateCheckClick onStateCheckClick;
    private final SparseBooleanArray checkBoxStateArray = new SparseBooleanArray();

    public void toggleSelection(boolean isChecked) {
        Log.e(logeTAG, "ClickSize: " + listSimple.size());
        if (listSimple.size() > 0) {
            int i = 0;
            checkBoxStateArray.clear();
            while (i < listSimple.size()) {
                listSimple.get(i).setSelected(isChecked);
                checkBoxStateArray.put(i, isChecked);
                Log.e(logeTAG, "Click: " + isChecked + "__" + i + "-" + listSimple.get(i).getBrend());
                i++;
            }
            Log.e(logeTAG, "ClickNon: ");
        }
        //don't forget to notify
        notifyDataSetChanged();
    }

    String logeTAG = "RecAdapter";
    Context context;

    public RecyclerView_Adapter_ViewHolder_Brends(Context context, List<ListAdapterSimple_Brends> list, OnStateClickListener onClickListener, OnStateCheckClick onStateCheckClick) {
        this.context = context;
        this.listSimple = list;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
        this.onStateCheckClick = onStateCheckClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.forma_brends_brend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ListAdapterSimple_Brends listID = listSimple.get(position);
        holder.tvwBrendName.setText(listID.getBrend());
        holder.tvwBrendPrefix.setText(listID.getPrefix());
        holder.checkBox.setChecked(listID.isSelected());

        holder.tvwBrendName.setOnClickListener(v -> onClickListener.onStateClick(listID, position));


        if (!checkBoxStateArray.get(position, false)) {//checkbox unchecked.
            holder.checkBox.setChecked(false);
        } else {//checkbox checked
            holder.checkBox.setChecked(true);
        }


        holder.checkBox.setOnCheckedChangeListener(null);
        String filename = "icons_logo_menu_" + listID.getPrefix() + ".png";
        File file = new File(context.getFilesDir().getAbsolutePath() + "/Icons/" + filename);

        // icons_logo_menu
        Picasso.get() //передаем контекст приложения
                .load(file)
                .error(R.drawable.no_image)
                .into(holder.imageView); //ссылка на ImageView*/

/*
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                //  listSimple.get(position).setSelected(true);
                Toast.makeText(context,
                        "Checked : " + listSimple.get(position).getBrend()
                        , Toast.LENGTH_LONG).show();
            } else {
                // listSimple.get(position).setSelected(false);
                Toast.makeText(context,
                        "Unchecked : " + listSimple.get(position).getBrend()
                        , Toast.LENGTH_LONG).show();
            }
        });*/


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
        return listSimple.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvwBrendName, tvwBrendPrefix;
        final ImageView imageView;
        final CheckBox checkBox;

        ViewHolder(View view) {
            super(view);
            tvwBrendName = view.findViewById(R.id.tvwBrendName);
            tvwBrendPrefix = view.findViewById(R.id.tvwBrendPrefix);
            imageView = view.findViewById(R.id.imageBrends);
            checkBox = view.findViewById(R.id.checkBoxBrends);
        }
    }
}