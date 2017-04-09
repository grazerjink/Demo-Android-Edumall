package vn.edu.topica.model;

import java.io.Serializable;

/**
 * Created by KJ on 20/01/2017.
 */

public class DanhBa implements Serializable {
    private int ma;
    private String ten;
    private String sdt;

    public DanhBa(int ma, String ten, String sdt) {
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
    }

    public DanhBa() {
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
