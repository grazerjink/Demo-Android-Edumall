package vn.edu.topica.tigiahoidoai;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import vn.edu.topica.tigiahoidoai.adapter.TiGiaAdapter;
import vn.edu.topica.tigiahoidoai.model.TiGia;

public class MainActivity extends AppCompatActivity {

    ListView lvTiGia;
    ArrayList<TiGia> dsTiGia;
    TiGiaAdapter tiGiaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvTiGia = (ListView) findViewById(R.id.lvTiGia);
        dsTiGia = new ArrayList<>();
        tiGiaAdapter = new TiGiaAdapter(this, R.layout.item, dsTiGia);
        lvTiGia.setAdapter(tiGiaAdapter);
        new TiGiaTask().execute();
    }

    class TiGiaTask extends AsyncTask<Void, Void, ArrayList<TiGia>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tiGiaAdapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<TiGia> tiGias) {
            super.onPostExecute(tiGias);
            tiGiaAdapter.clear();
            tiGiaAdapter.addAll(tiGias);
            tiGiaAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<TiGia> doInBackground(Void... params) {
            ArrayList<TiGia> ds = new ArrayList<>();
            try {
                URL url = new URL("http://dongabank.com.vn/exchange/export");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Content-type", "application/json; charset=utf-8");
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
                httpURLConnection.setRequestProperty("Accept", "*/*");

                //Lay du lieu ma server tra ve
                InputStream is = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String line = br.readLine();
                StringBuilder builder = new StringBuilder();
                while (line != null) {
                    builder.append(line);
                    line = br.readLine();
                }
                String json = builder.toString();
                json = json.replace("(", "");
                json = json.replace(")", "");

                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    TiGia tiGia = new TiGia();
                    if(item.has("type"))
                        tiGia.setType(item.getString("type"));
                    if(item.has("imageurl")) {
                        tiGia.setImageurl(item.getString("imageurl"));
                        url=new URL(tiGia.getImageurl());
                        httpURLConnection= (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                        httpURLConnection.setRequestProperty("Accept", "*/*");
                        Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                        tiGia.setBitmapOfImageurl(bitmap);
                    }
                    if(item.has("muatienmat"))
                        tiGia.setMuatienmat(item.getString("muatienmat"));
                    if(item.has("muack"))
                        tiGia.setMuack(item.getString("muack"));
                    if(item.has("bantienmat"))
                        tiGia.setBantienmat(item.getString("bantienmat"));
                    if(item.has("banck"))
                        tiGia.setBanck(item.getString("banck"));
                    ds.add(tiGia);
                }
                Log.d("JSON_DONGA",json);
            } catch (Exception ex) {
                Log.e("LOI", ex.toString());
            }
            return ds;
        }
    }
}
