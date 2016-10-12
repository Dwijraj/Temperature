package temperature.com.temperature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 1405214 on 09-06-2016.
 * This class is used to parse the response
 * we get from the web and excapsulate them
 * in an object of model class
 * which is further used to display
 */
public class JSONPARSER {

    public static Model Parse(JSONObject jsonObject) throws JSONException {
        Model model=new Model();

        DecimalFormat decimalFormat=new DecimalFormat("#.#");       //To get the dateformat

        JSONObject main=Utils.getJSONObject("main",jsonObject);
        float temp=Utils.getFloat("temp",main)-273;
        float temp_min=Utils.getFloat("temp_min",main)-273;         //Convert the temperature
        float temp_max=Utils.getFloat("temp_max",main)-273;
        model.temp=""+decimalFormat.format(temp);
        model.tempmin=""+decimalFormat.format(temp_min);
        model.tempmax=""+decimalFormat.format(temp_max);
        model.volume=""+Utils.getDouble("humidity",main);

        long dv = jsonObject.getLong("dt")*1000;// its need to be in milisecond
        Date df = new java.util.Date(dv);
        String vv = new SimpleDateFormat("dd /MM/ yyyy  hh:mma").format(df);
        model.lastupdated=vv;



       /* DateFormat dateFormat=DateFormat.getTimeInstance();
        String str=dateFormat.format(new Date(jsonObject.getLong("dt")).getTime());
        model.lastupdated=str;*/


        JSONArray jsonArray=Utils.getJsonArray("weather",jsonObject);
        JSONObject jsonObject1=jsonArray.getJSONObject(0);
        String descp=Utils.getString("description",jsonObject1);
        String icon=Utils.getString("icon",jsonObject1);
        model.descp=descp;
        model.icon="http://openweathermap.org/img/w/"+icon+".png";



        return model;
    }
}
