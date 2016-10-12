package temperature.com.temperature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 1405214 on 09-06-2016.
 * Functions to extracts
 * the required fields
 * from the response we get
 */
public class Utils {
    public static JSONObject getJSONObject(String str,JSONObject jsonObject)throws JSONException
    {
        JSONObject jsonObject1=jsonObject.getJSONObject(str);
        return jsonObject1;
    }
    public static String getString(String str,JSONObject jsonObject)throws JSONException
    {
        return jsonObject.getString(str);
    }
    public static float getFloat(String str,JSONObject jsonObject)throws JSONException
    {
        return (float)jsonObject.getDouble(str);
    }
    public static double getDouble(String str,JSONObject jsonObject)throws JSONException
    {
        return jsonObject.getDouble(str);
    }
    public static JSONArray getJsonArray(String str,JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONArray(str);
    }
}
