package global;

import android.content.Context;
import android.content.SharedPreferences;

public class userPrefs {
    private static final String PREFS_Name = "com.our.package.UserPrefs";
    public static SharedPreferences settings;
    public static SharedPreferences.Editor editor;

    public userPrefs(Context ctx){
        if (settings == null){
            settings = ctx.getSharedPreferences(PREFS_Name, Context.MODE_PRIVATE);
        }
        editor = settings.edit();
    }
}
