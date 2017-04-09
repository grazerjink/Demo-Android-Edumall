package vn.edu.topica.hocassets;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txtFont;
    ListView lvFonts;
    List<String> dsFonts;
    ArrayAdapter<String> fontAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvFonts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xuLyDoiFontChu(position);
            }
        });
    }

    private void xuLyDoiFontChu(int position) {
        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/"+dsFonts.get(position));
        txtFont.setTypeface(typeface);
    }

    private void addControls() {
        txtFont = (TextView) findViewById(R.id.tvTitle);
        lvFonts = (ListView) findViewById(R.id.lvFont);
        dsFonts = new ArrayList<>();
        fontAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dsFonts);
        lvFonts.setAdapter(fontAdapter);

        try {
            AssetManager assetManager = getAssets();
            String[] arrayFontName = assetManager.list("font");
            dsFonts.addAll(Arrays.asList(arrayFontName));
            fontAdapter.notifyDataSetChanged();
        } catch (IOException e) {
            Log.e("LOI_FONT", e.toString());
        }
    }
}
