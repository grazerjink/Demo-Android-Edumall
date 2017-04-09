package vn.edu.topica.tygiagson.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.edu.topica.tygiagson.R;
import vn.edu.topica.tygiagson.model.Item;

/**
 * Created by KJ Mok on 01/02/2017.
 */

public class TyGiaAdapter extends ArrayAdapter<Item> {

    List<Item> itemList;
    Context context;
    int resource;

    public TyGiaAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        itemList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgHinh = (ImageView) convertView.findViewById(R.id.imgHinh);
            viewHolder.tvNuoc = (TextView) convertView.findViewById(R.id.tvNuoc);
            viewHolder.tvBanCK = (TextView) convertView.findViewById(R.id.tvBanCK);
            viewHolder.tvBanTM = (TextView) convertView.findViewById(R.id.tvBanTM);
            viewHolder.tvMuaCK = (TextView) convertView.findViewById(R.id.tvMuaCK);
            viewHolder.tvMuaTM = (TextView) convertView.findViewById(R.id.tvMuaTM);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imgHinh.setImageBitmap(itemList.get(position).getBitmap());
        viewHolder.tvNuoc.setText(itemList.get(position).getType());
        viewHolder.tvBanCK.setText(itemList.get(position).getBanck());
        viewHolder.tvBanTM.setText(itemList.get(position).getBantienmat());
        viewHolder.tvMuaCK.setText(itemList.get(position).getMuack());
        viewHolder.tvMuaTM.setText(itemList.get(position).getMuatienmat());
        return convertView;
    }

    class ViewHolder {
        ImageView imgHinh;
        TextView tvNuoc, tvMuaCK, tvMuaTM, tvBanCK, tvBanTM;
    }
}