package temperature.com.temperature;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private int n=1;
    private Model model;
    private TextView location;
    private TextView temp;
    private TextView tempmax;
    private TextView tempmin;
    private TextView descp;
    private TextView lastupdated;
    private TextView volume;
    private ImageView imageView;
    private RequestQueue requestQueue;
    private EditText editText;
    private Button button;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Test","Work1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.v("Test","Work1");
       temp=(TextView)findViewById(R.id.temp);
        tempmax=(TextView)findViewById(R.id.tempmax);
        tempmin=(TextView)findViewById(R.id.tempmin);
        descp=(TextView)findViewById(R.id.descp);
        location=(TextView)findViewById(R.id.location);
        lastupdated=(TextView)findViewById(R.id.lastupdated);
        volume=(TextView)findViewById(R.id.volume);
        imageView=(ImageView) findViewById(R.id.imageView);
        editText=(EditText)findViewById(R.id.editText2);
        button=(Button)findViewById(R.id.button2);
        if(n==1)
        {
            String str="http://api.openweathermap.org/data/2.5/weather?q=Bongaigaon,India&appid=48b076c80a0b064d62117d19015db014"; //Main link to fetch the info
            MainTask(str);
            n++;




        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string=(editText.getText().toString().trim());
                String str2="http://api.openweathermap.org/data/2.5/weather?q="+string;
                String str=str2+"&appid=48b076c80a0b064d62117d19015db014";
                MainTask(str);


            }
        });
    }
    public void MainTask(String str3)
    {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, str3, null,   //gets the JSON object from str3 link
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            model=JSONPARSER.Parse(response);           //Gets the response in Model object
                            temp.setText(" "+model.temp);
                            tempmax.setText(" "+model.tempmax+"ºC");
                            tempmin.setText(" "+model.tempmin+"ºC");
                            descp.setText(model.descp);
                            lastupdated.setText(model.lastupdated);
                            volume.setText(" "+model.volume);
                            Picasso.with(getApplicationContext())       //Picasso to load images
                                    .load(model.icon)
                                    .into(imageView);
                            location.setText(response.getString("name"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //model=JSONPARSER.Parse("NO ACTIVE INTERNET CONNECTION");
                temp.setText("NO ACTIVE INTERNET CONNECTION");
                tempmax.setText("NO ACTIVE INTERNET CONNECTION");
                tempmin.setText("NO ACTIVE INTERNET CONNECTION");
                descp.setText("NO ACTIVE INTERNET CONNECTION");
                lastupdated.setText("NO ACTIVE INTERNET CONNECTION");
                volume.setText("NO ACTIVE INTERNET CONNECTION");
                location.setText("NO ACTIVE INTERNET CONNECTION");
                Toast.makeText(getApplicationContext(),"NO ACTIVE INTERNET CONNECTION",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue= Volley.newRequestQueue(getApplicationContext());  //Queues the tasks if other tassks are being performed
        requestQueue.add(jsonObjectRequest);                            //Adds to the queue
    }
}
