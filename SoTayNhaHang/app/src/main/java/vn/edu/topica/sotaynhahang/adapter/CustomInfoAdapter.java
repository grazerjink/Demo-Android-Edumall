package vn.edu.topica.sotaynhahang.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import vn.edu.topica.sotaynhahang.R;
import vn.edu.topica.sotaynhahang.model.NhaHang;

/**
 * Created by KJ Mok on 02/02/2017.
 */

public class CustomInfoAdapter implements GoogleMap.InfoWindowAdapter {
    Activity context;
    NhaHang nhaHang;

    public CustomInfoAdapter(Activity context, NhaHang nhaHang) {
        this.context = context;
        this.nhaHang = nhaHang;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.item, null);
        ImageView imgHinh = (ImageView) v.findViewById(R.id.imgHinh);
        TextView tvTen = (TextView) v.findViewById(R.id.tvTen);

        imgHinh.setImageResource(nhaHang.getHinh());
        tvTen.setText(nhaHang.getTen());
        return v;
    }
}
