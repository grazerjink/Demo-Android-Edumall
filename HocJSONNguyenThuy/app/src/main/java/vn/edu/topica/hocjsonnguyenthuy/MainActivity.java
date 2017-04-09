package vn.edu.topica.hocjsonnguyenthuy;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView lvContacts;
    ArrayList<Contact> dsContacts;
    ArrayAdapter<Contact> adpContact;

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
        lvContacts = (ListView) findViewById(R.id.lvContact);
        dsContacts = new ArrayList<>();
        adpContact = new ArrayAdapter<Contact>(this,android.R.layout.simple_list_item_1,dsContacts);
        lvContacts.setAdapter(adpContact);
        new ContactTask().execute();
    }

    class ContactTask extends AsyncTask<Void, Void, ArrayList<Contact>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adpContact.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Contact> contacts) {
            super.onPostExecute(contacts);
            adpContact.clear();
            adpContact.addAll(contacts);
            adpContact.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Contact> doInBackground(Void... params) {
            ArrayList<Contact> ds = new ArrayList<>();
            try
            {
                URL url = new URL("http://www.w3schools.com/website/customers_mysql.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader is = new InputStreamReader(urlConnection.getInputStream(),"UTF-8");
                BufferedReader bfr = new BufferedReader(is);

                StringBuilder builder = new StringBuilder();
                String line = bfr.readLine();
                while(line!=null) {
                    builder.append(line);
                    line = bfr.readLine();
                }
                JSONArray jsonArray = new JSONArray(builder.toString());
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Contact contact = new Contact();
                    if(obj.has("Name"))
                        contact.setName(obj.getString("Name"));
                    if(obj.has("City"))
                        contact.setCity(obj.getString("City"));
                    if(obj.has("Country"))
                        contact.setcountry(obj.getString("Country"));
                    ds.add(contact);
                }
            }
            catch(Exception ex) {
                Log.e("LOI",ex.toString());
            }
            return ds;
        }
    }
}
