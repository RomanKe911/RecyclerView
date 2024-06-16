package kg.kerkin.recyclearmain.DayWeek;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import kg.kerkin.recyclearmain.R;
public class SpinnerAdapter_DayWeek extends BaseAdapter {

    Context context;
    ArrayList<SpinnerAdapterSimple_DayWeek> objects;
    ArrayList<SpinnerAdapterSimple_DayWeek> filterList;
    public Context context_Activity;
    public TextView name, name_image;

    public SpinnerAdapter_DayWeek(Context ctx, ArrayList<SpinnerAdapterSimple_DayWeek> login) {
        // TODO Auto-generated constructor stub
        this.context = ctx;
        this.objects = login;
        this.filterList = login;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objects.size();
    }

    @Override
    public Object getItem(int pos) {
        // TODO Auto-generated method stub
        return objects.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        // TODO Auto-generated method stub
        return objects.indexOf(getItem(pos));
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        //чтение данных: имя сервера
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.spinner, null);

        name = convertView.findViewById(R.id.spinner_day_of_week);
        name.setText(objects.get(pos).getName());


        return convertView;
    }

}