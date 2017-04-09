package vn.edu.topica.hocksoapapi_bai1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;

import vn.edu.topica.hocksoapapi_bai1.config.Configuration;

public class MainActivity extends AppCompatActivity {
    EditText edtNhap;
    Button btnF;
    TextView tvF;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDoF();
            }
        });
    }

    private void xuLyDoF() {
        new CToFTask().execute(edtNhap.getText().toString());
    }

    class CToFTask extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvF.setText("");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvF.setText(s);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
            try
            {
                String c = params[0];
                SoapObject request = new SoapObject(Configuration.NAME_SPACE,Configuration.METHOD_C_TO_F);
                //Có giá trị truyền vào nên phải addProperty
                request.addProperty(Configuration.PARAM_C_TO_F_CELSIUS,c);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(Configuration.SERVER_URL);
                httpTransportSE.call(Configuration.SOAPACTION_C_TO_F,envelope);

                SoapPrimitive data = (SoapPrimitive) envelope.getResponse();
                return data.toString();
            }
            catch (Exception ex)
            {
                Log.e("LOI", ex.toString());
            }
            return null;
        }
    }

    private void addControls() {
        edtNhap = (EditText) findViewById(R.id.edtNhap);
        btnF = (Button) findViewById(R.id.btnF);
        tvF = (TextView) findViewById(R.id.tvF);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang xử lỹ...");
        progressDialog.setCanceledOnTouchOutside(false);
    }
}
