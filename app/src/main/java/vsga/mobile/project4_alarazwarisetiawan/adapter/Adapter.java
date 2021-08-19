package vsga.mobile.project4_alarazwarisetiawan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vsga.mobile.project4_alarazwarisetiawan.R;
import vsga.mobile.project4_alarazwarisetiawan.model.Data;

public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (converView == null) {
            converView = inflater.inflate(R.layout.list_row, null);
        }

        TextView id = (TextView) converView.findViewById(R.id.id);
        TextView name = (TextView) converView.findViewById(R.id.name);
        TextView address = (TextView) converView.findViewById(R.id.address);
        TextView contact = (TextView) converView.findViewById(R.id.contact);

        Data data = items.get(position);
        id.setText(data.getId());
        name.setText(data.getName());
        address.setText(data.getAddress());
        contact.setText(data.getContact());

        return converView;
    }
}
