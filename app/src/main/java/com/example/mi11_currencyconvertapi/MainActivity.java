package com.example.mi11_currencyconvertapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tryText;
    TextView cadText;
    TextView usdText;
    TextView jpyText;
    TextView chfText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tryText = findViewById(R.id.tryText);
        cadText = findViewById(R.id.cadText);
        usdText = findViewById(R.id.usdText);
        jpyText = findViewById(R.id.jpyText);
        chfText = findViewById(R.id.chfText);
    }

    public void getRates(View view){

    }


    private class DownloadData extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0){

                    char character = (char) data;
                    result += character;

                    data = inputStreamReader.read();
                }

                return result;

            }catch (Exception e){
                return null;
            }

        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //System.out.println("alınan data: " +s);

            try {

                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("base");

                String rates = jsonObject.getString("rates");


                JSONObject jsonObject1 = new JSONObject(rates);
                String turkishlira = jsonObject1.getString("TRY");
                tryText.setText("TRY: " + turkishlira);

                String usd = jsonObject1.getString("USD");
                tryText.setText("USD: " + usd);

                String cad = jsonObject1.getString("CAD");
                tryText.setText("CAD: " + cad);

                String chf = jsonObject1.getString("CHF");
                tryText.setText("CHF: " + chf);

                String jpy = jsonObject1.getString("JPY");
                tryText.setText("JPY: " + jpy);



            }catch (Exception e){

            }
        }
    }
}
