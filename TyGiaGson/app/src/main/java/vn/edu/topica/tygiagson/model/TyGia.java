package vn.edu.topica.tygiagson.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KJ Mok on 01/02/2017.
 */

public class TyGia implements Serializable {
    private List<Item> items;

    public TyGia() {
    }

    public TyGia(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
