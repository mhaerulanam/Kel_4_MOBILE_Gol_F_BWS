package com.def.dokternak.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.def.dokternak.data.Model.users.User;

public class Preferences {

    /** Pendeklarasian key-data berupa String, untuk sebagai wadah penyimpanan data.
     * Jadi setiap data mempunyai key yang berbeda satu sama lain */
    static final String KEY_USER_TEREGISTER ="user", KEY_PASS_TEREGISTER ="pass";
    static final String KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in";
    static final String KEY_STATUS_SEDANG_LOGIN = "Status_logged_in";

    /** Pendlakarasian Shared Preferences yang berdasarkan paramater context */
    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void simpanData(){
    }

    public static String getImagePicture(Context context) {
        SharedPreferences pref = context.getSharedPreferences("DATA_PREFERENCES", Context.MODE_PRIVATE);
        String gambar = pref.getString("foto_peternak", null);
        return gambar;
    }

    public static String getAlamat(Context context){
        SharedPreferences pref = context.getSharedPreferences("DATA_PREFERENCES", Context.MODE_PRIVATE);
        String alamat = pref.getString("alamat", null);
        return alamat;
    }

    public static String getNama(Context context){
        SharedPreferences pref = context.getSharedPreferences("DATA_PREFERENCES", Context.MODE_PRIVATE);
        String nama = pref.getString("nama_peternak", null);
        return nama;
    }

    public static String getEmail(Context context){
        SharedPreferences pref = context.getSharedPreferences("DATA_PREFERENCES", Context.MODE_PRIVATE);
        String email = pref.getString("email", null);
        return email;
    }

    public static String getNoHp(Context context){
        SharedPreferences pref = context.getSharedPreferences("DATA_PREFERENCES", Context.MODE_PRIVATE);
        String hp = pref.getString("no_hp", null);
        return hp;
    }

    public static String getJenisKelamin(Context context){
        SharedPreferences pref = context.getSharedPreferences("DATA_PREFERENCES", Context.MODE_PRIVATE);
        String Jk = pref.getString("jenis_kelamin", null);
        return Jk;
    }

    public static  int getIdPeternak(Context context) {
        SharedPreferences pref = context.getSharedPreferences("DATA_PREFERENCES", Context.MODE_PRIVATE);
        int id_peternak = pref.getInt("id_peternak", 0);
        return  id_peternak;
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key isi KEY_USER_TEREGISTER dengan parameter username */
    public static void setRegisteredUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_TEREGISTER, username);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_USER_TEREGISTER berupa String */
    public static String getRegisteredUser(Context context){
        return getSharedPreference(context).getString(KEY_USER_TEREGISTER,"");
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_PASS_TEREGISTER dengan parameter password */
    public static void setRegisteredPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS_TEREGISTER, password);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_PASS_TEREGISTER berupa String */
    public static String getRegisteredPass(Context context){
        return getSharedPreference(context).getString(KEY_PASS_TEREGISTER,"");
    }

    public static void saveData(Context context, User user) {
        SharedPreferences pref = context.getSharedPreferences("DATA_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("id", user.getId());
        editor.putInt("id_peternak",user.getId_peternak());
        editor.putString("nama_peternak", user.getNamaUser());
        editor.putString("namadepan_peternak",user.getNamaPeternak());
        editor.putString("namabelakang_peternak",user.getNamaBelakangPeternak());
        editor.putString("email",user.getEmail());
        editor.putString("is_admin", user.getRole());
        editor.putString("email_peternak", user.getEmailPeternak());
        editor.putString("no_hp",user.getNoHp());
        editor.putString("jenis_kelamin",user.getJenisKelamin());
        editor.putString("alamat", user.getAlamat());
        editor.putString("foto_peternak",user.getFotoPeternak());
        editor.putString("password", user.getPassword());
//        editor.putString(KEY_USERNAME_SEDANG_LOGIN, user.getEmail());
//        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN, true);
        editor.apply();
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_USERNAME_SEDANG_LOGIN dengan parameter username */
    public static void setLoggedInUser(Context context, String email){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, email);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String */
    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME_SEDANG_LOGIN,"");
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_STATUS_SEDANG_LOGIN dengan parameter status */
    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN,status);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_STATUS_SEDANG_LOGIN berupa boolean */
    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(KEY_STATUS_SEDANG_LOGIN,false);
    }

    /** Deklarasi Edit Preferences dan menghapus data, sehingga menjadikannya bernilai default
     *  khusus data yang memiliki key KEY_USERNAME_SEDANG_LOGIN dan KEY_STATUS_SEDANG_LOGIN */
    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERNAME_SEDANG_LOGIN);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);

        SharedPreferences pref = context.getSharedPreferences("DATA_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = pref.edit();
        editor2.remove("id");
        editor2.remove("id_peternak");
        editor2.remove("nama_peternak");
        editor2.remove("namadepan_peternak");
        editor2.remove("namabelakang_peternak");
        editor.remove("email");
        editor2.remove("email");
        editor2.remove("is_admin");
        editor2.remove("email_peternak");
        editor2.remove("no_hp");
        editor2.remove("jenis_kelamin");
        editor2.remove("alamat");
        editor2.remove("foto_peternak");
        editor2.remove("password");
        editor.apply();
        editor2.apply();
    }
}
