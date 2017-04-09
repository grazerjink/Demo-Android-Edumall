package vn.edu.topica.sotaynhahang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.topica.sotaynhahang.model.FakeNhaHang;
import vn.edu.topica.sotaynhahang.model.NhaHang;

public class MainActivity extends AppCompatActivity {

    ListView lvNhaHang;
    ArrayList<NhaHang> dsNhaHang;
    ArrayAdapter<NhaHang> adpNhaHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvNhaHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhaHang nhaHang = dsNhaHang.get(position);
                Intent i = new Intent(MainActivity.this,MapsActivity.class);
                i.putExtra("NHAHANG",nhaHang);
                startActivity(i);
            }
        });
    }

    private void addControls() {
        lvNhaHang = (ListView) findViewById(R.id.lvNhaHang);
        dsNhaHang = FakeNhaHang.dsNhaHang();
        adpNhaHang = new ArrayAdapter<NhaHang>(this, android.R.layout.simple_list_item_1, dsNhaHang);
        lvNhaHang.setAdapter(adpNhaHang);
    }
}
