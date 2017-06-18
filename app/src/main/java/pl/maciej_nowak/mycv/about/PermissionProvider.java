package pl.maciej_nowak.mycv.about;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Maciej on 19.06.2017.
 */

public final class PermissionProvider {

    public static final int REQUEST_PERMISSION = 100;
    public static final int REQUEST_LOCATION_CODE = 101;
    public static final int REQUEST_CALL_CODE = 102;
    public static final int PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED;
    public static final String LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String CALL = Manifest.permission.CALL_PHONE;

    public static String[] requiredPermissions(Activity activity, int code) {
        String[] permissions = new String[0];
        List<String> permissionsList = new ArrayList<>();

        if(code == REQUEST_LOCATION_CODE) {
            if(ContextCompat.checkSelfPermission(activity, LOCATION) != PERMISSION_GRANTED)
                permissionsList.add(LOCATION);
            if((ContextCompat.checkSelfPermission(activity, STORAGE) != PERMISSION_GRANTED))
                permissionsList.add(STORAGE);
        }
        else if(code == REQUEST_CALL_CODE) {
            if(ContextCompat.checkSelfPermission(activity, CALL) != PERMISSION_GRANTED)
                permissionsList.add(CALL);
        }
        if(permissionsList.isEmpty())
            permissionsList = Collections.emptyList();

        return permissionsList.toArray(permissions);
    }

    public static boolean isRequiredVersion() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean arePermissionsGranted(Activity activity, int permissionCode) {
        if (permissionCode == REQUEST_LOCATION_CODE) {
            return (ContextCompat.checkSelfPermission(activity, LOCATION) == PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, STORAGE) == PERMISSION_GRANTED);
        } else if (permissionCode == REQUEST_CALL_CODE) {
            return (ContextCompat.checkSelfPermission(activity, CALL) == PERMISSION_GRANTED);
        } else
            return true;
    }

    public static boolean arePermissionsProvided(int[] grantResults) {
        boolean areProvided = true;
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                areProvided = false;
                break;
            }
        }
        return areProvided;
    }
}
