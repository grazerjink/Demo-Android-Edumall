package vn.edu.topica.tigiahoidoai.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.edu.topica.tigiahoidoai.R;
import vn.edu.topica.tigiahoidoai.model.TiGia;

/**
 * Created by KJ Mok on 01/02/2017.
 */

public class TiGiaAdapter extends ArrayAdapter<TiGia>{

    Activity context;
    int resource;
    List<TiGia> objects;

    public TiGiaAdapter(Activity context, int resource, List<TiGia> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(resource,null);
        TiGia tiGia = objects.get(position);

        ImageView imgHinh = (ImageView) item.findViewById(R.id.imgHinh);
        TextView tvNuoc = (TextView) item.findViewById(R.id.tvNuoc);
        TextView tvMuaCK = (TextView) item.findViewById(R.id.tvMuaCK);
        TextView tvMuaTM = (TextView) item.findViewById(R.id.tvMuaTM);
        TextView tvBanCK = (TextView) item.findViewById(R.id.tvBanCK);
        TextView tvBanTM = (TextView) item.findViewById(R.id.tvBanTM);

        imgHinh.setImageBitmap(tiGia.getBitmap());
        tvNuoc.setText(tiGia.getType());
        tvBanCK.setText(tiGia.getBanck());
        tvBanTM.setText(tiGia.getBantienmat());
        tvMuaCK.setText(tiGia.getMuack());
        tvMuaTM.setText(tiGia.getMuatienmat());

        return item;
    }
}
