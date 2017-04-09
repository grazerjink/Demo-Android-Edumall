package vn.edu.topica.tygiagson.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vn.edu.topica.tygiagson.R;
import vn.edu.topica.tygiagson.adapter.TyGiaAdapter;
import vn.edu.topica.tygiagson.model.Item;
import vn.edu.topica.tygiagson.model.TyGia;

public class MainActivity extends AppCompatActivity {

    ListView lvTyGia;
    ArrayList<Item> itemList;
    TyGiaAdapter tyGiaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        lvTyGia = (ListView) findViewById(R.id.lvTyGia);
        itemList = new ArrayList<>();
        tyGiaAdapter = new TyGiaAdapter(this, R.layout.item, itemList);
        lvTyGia.setAdapter(tyGiaAdapter);
        new TyGiaTask().execute();
    }

    class TyGiaTask extends AsyncTask<Void, Void, List<Item>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tyGiaAdapter.clear();
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            super.onPostExecute(items);
            tyGiaAdapter.clear();
            tyGiaAdapter.addAll(items);
            tyGiaAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<Item> doInBackground(Void... params) {
            try {
                URL url = new URL("http://dongabank.com.vn/exchange/export");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Content-type", "application/json; charset=utf-8");
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
                httpURLConnection.setRequestProperty("Accept", "*/*");
                InputStreamReader reader = new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();
                StringBuilder builder = new StringBuilder();
                while (line != null) {
                    builder.append(line);
                    line = br.readLine();
                }
                String json = builder.toString();
                json = json.replace("(", "");
                json = json.replace(")", "");
                TyGia tyGia = new Gson().fromJson(json, TyGia.class);
                for (int i = 0; i < tyGia.getItems().size(); i++) {
                    url = new URL(tyGia.getItems().get(i).getImageurl());
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
                    httpURLConnection.setRequestProperty("Accept", "*/*");
                    Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                    tyGia.getItems().get(i).setBitmap(bitmap);
                }
                return tyGia.getItems();
            } catch (Exception ex) {
                Log.d("ERROR_MESSAGE", ex.toString());
            }
            return null;
        }
    }
}
