package vn.edu.topica.sotaynhahang.model;

import java.util.ArrayList;

import vn.edu.topica.sotaynhahang.R;

/**
 * Created by KJ Mok on 02/02/2017.
 */

public class FakeNhaHang {
    public static ArrayList<NhaHang> dsNhaHang() {
        ArrayList<NhaHang> ds = new ArrayList<>();
        ds.add(new NhaHang("Nhà hàng Ái Huệ", R.drawable.aihue,10.752155, 106.659666));
        ds.add(new NhaHang("Nhà hàng Ái Huệ 2", R.drawable.aihue2,10.752637, 106.664470));
        ds.add(new NhaHang("Nhà hàng Bạch Kim", R.drawable.bachkim,10.782890, 106.642668));
        ds.add(new NhaHang("Nhà hàng Quảng Đông - Royal Garden", R.drawable.quangdong,10.755549, 106.663368));
        return ds;
    }
}
