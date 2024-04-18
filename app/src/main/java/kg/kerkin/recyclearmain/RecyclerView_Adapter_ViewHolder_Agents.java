package kg.kerkin.recyclearmain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kg.kerkin.recyclearmain.R;

public class RecyclerView_Adapter_ViewHolder_Agents
        extends RecyclerView.Adapter<RecyclerView_Adapter_ViewHolder_Agents.ViewHolder> {


    public interface OnStateClickListener {
        void onStateClick(ListAdapterSimple_Agents clientClick, int position);
    }

    private final LayoutInflater inflater;
    private final List<ListAdapterSimple_Agents> listSimple;

    private final OnStateClickListener onClickListener;

    String logeTAG = "RecAdapter";
    Context context;

    public RecyclerView_Adapter_ViewHolder_Agents(Context context, List<ListAdapterSimple_Agents> list, OnStateClickListener onClickListener) {
        this.context = context;
        this.listSimple = list;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.forma_brends_agent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ListAdapterSimple_Agents listID = listSimple.get(position);
        holder.tvwAgent.setText(listID.getName());
        holder.tvwAgentUID.setText(listID.getUID());


        holder.tvwAgentUID.setVisibility(View.GONE);

        holder.tvwAgent.setOnClickListener(v -> onClickListener.onStateClick(listID, position));
        //
    }




    @Override
    public int getItemCount() {
        return listSimple.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvwAgent, tvwAgentUID;

        ViewHolder(View view) {
            super(view);
            tvwAgent = view.findViewById(R.id.tvwBrendsAgentName);
            tvwAgentUID = view.findViewById(R.id.tvwBrendsAgentUID);


        }
    }

    private void ToastOverride(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }



}