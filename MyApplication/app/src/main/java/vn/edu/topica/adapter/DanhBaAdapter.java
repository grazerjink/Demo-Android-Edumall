package vn.edu.topica.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.topica.listviewnangcao.R;
import vn.edu.topica.model.DanhBa;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by KJ on 20/01/2017.
 */

public class DanhBaAdapter extends ArrayAdapter<DanhBa> {

    private int mResources;
    private Context mContext;
    private List<DanhBa> mListDanhBa;

    public DanhBaAdapter(Context context, int resource, List<DanhBa> listDB) {
        super(context, resource, listDB);
        mResources = resource;
        mContext = context;
        mListDanhBa = listDB;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView != null) {
//
//        } else {
//
//        }
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(mResources,parent);

        TextView tvTen = (TextView) v.findViewById(R.id.tvTen);
        TextView tvDt = (TextView) v.findViewById(R.id.tvDt);

        return convertView;
    }
}
