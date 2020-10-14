package com.example.managefield.data.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my_club_manage";
    private static final int VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private final String DROP_PLAYER = "DROP TABLE IF EXISTS player";
    private final String CREATE_PLAYER = "CREATE TABLE player(" +
            "id  TEXT PRIMARY KEY ," +
            "name TEXT," +
            "age INTEGER," +
            "height REAL," +
            "weight REAL," +
            "phone TEXT," +
            "email TEXT," +
            "urlavatar TEXT," +
            "position TEXT )";

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String createPostion = "CREATE TABLE position(" +
//                "id TEXT PRIMARY KEY ," +
//                "position TEXT)";
//        db.execSQL(createPostion);


//                "FOREIGN KEY (id) REFERENCES position(id))";
        db.execSQL(CREATE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_PLAYER);
        db.execSQL(CREATE_PLAYER);
    }


//    public long insertPlayer(Player player) {
//        ContentValues values = new ContentValues();
////        values.put("id", player.getId());
////        values.put("name", player.getName());
////        values.put("age", player.getAge());
////        values.put("height", player.getHeight());
////        values.put("weight", player.getWeight());
////        values.put("phone", player.getPhone());
////        values.put("email", player.getEmail());
////        values.put("urlavatar", player.getUrlAvatar());
////        values.put("position", player.getPosition());
//        return getWritableDatabase().insert("player", null, values);
//
//    }
//
//    public List<Player> getAllPlayer() {
//        List<Player> players = new ArrayList<>();
//        Cursor cursor = getReadableDatabase()
//                .query("player", null, null,
//                        null, null, null, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
////                String id = cursor.getString(cursor.getColumnIndex("id"));
////                String name = cursor.getString(cursor.getColumnIndex("name"));
////                int age = cursor.getInt(cursor.getColumnIndex("age"));
////                double height = cursor.getInt(cursor.getColumnIndex("height"));
////                double weight = cursor.getInt(cursor.getColumnIndex("weight"));
////                String email = cursor.getString(cursor.getColumnIndex("email"));
////                String phone = cursor.getString(cursor.getColumnIndex("phone"));
////                String urlAvatar=cursor.getString(cursor.getColumnIndex("urlavatar"));
////                String position=cursor.getString(cursor.getColumnIndex("position"));
////                Player player = new Player();
////                players.add(player);
//            }
//            cursor.close();
//        }
//        return players;
//
//    }
//
//    public long updatePlayer(Player player) {
//        ContentValues values = new ContentValues();
//        values.put("id", player.getId());
////        values.put("name", player.getName());
////        values.put("age", player.getAge());
////        values.put("height", player.getHeight());
////        values.put("weight", player.getWeight());
////        values.put("phone", player.getPhone());
////        values.put("email", player.getEmail());
////        values.put("urlavatar", player.getUrlAvatar());
////        values.put("position", player.getPosition());
////        String whereClause = "id=?";
////
////        String[] whereArgs = {player.getId()};
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        return sqLiteDatabase.update("player", values, whereClause, whereArgs);
//    }
//
//
//    public long deletePlayer(String isbn) {
//        String whereClause = "id = ?";
//        String[] args = {isbn};
//        return getWritableDatabase().delete("player", whereClause, args);
//    }
//
//
//    public Player getPlayerbyName(String namePlayer){
//        String where = "name = ?";
//        String[] args = {namePlayer};
//        Cursor cursor = getReadableDatabase().query("player", null, where,
//                args, null, null, null);
//        if (cursor != null && cursor.moveToNext()) {
////            String id = cursor.getString(cursor.getColumnIndex("id"));
////            String name = cursor.getString(cursor.getColumnIndex("name"));
////            int age = cursor.getInt(cursor.getColumnIndex("age"));
////            float height = cursor.getInt(cursor.getColumnIndex("height"));
////            float weight = cursor.getInt(cursor.getColumnIndex("weight"));
////            String phone = cursor.getString(cursor.getColumnIndex("phone"));
////            String email = cursor.getString(cursor.getColumnIndex("email"));
////            String urlAvatar=cursor.getString(cursor.getColumnIndex("urlavatar"));
////            String position=cursor.getString(cursor.getColumnIndex("position"));
////            Player player = new Player(id,name,age,height,weight,phone,email,urlAvatar,position);
////            return  player;
//        }
//        return null;
//    }
//
//    public List<Player> getListPlayerbyName(String namePlayer){
//        List<Player> players = new ArrayList<>();
//        String where = "name like ?";
//        String[] args = {"%"+namePlayer+"%"};
//        Cursor cursor = getReadableDatabase().query("player", null, where,
//                args, null, null, null);
//        while (cursor != null && cursor.moveToNext()) {
////            String id = cursor.getString(cursor.getColumnIndex("id"));
////            String name = cursor.getString(cursor.getColumnIndex("name"));
////            int age = cursor.getInt(cursor.getColumnIndex("age"));
////            float height = cursor.getInt(cursor.getColumnIndex("height"));
////            float weight = cursor.getInt(cursor.getColumnIndex("weight"));
////            String phone = cursor.getString(cursor.getColumnIndex("phone"));
////            String email = cursor.getString(cursor.getColumnIndex("email"));
////            String urlAvatar=cursor.getString(cursor.getColumnIndex("urlavatar"));
////            String position=cursor.getString(cursor.getColumnIndex("position"));
////            Player player = new Player(id,name,age,height,weight,phone,email,urlAvatar,position);
////            players.add(player);
//        }
//        return players;
//    }
//
//    public Player getPlayerbyID(String idPlayer){
//        Log.d("Meme", "getPlayer: ");
//        String where = "id= ?";
//        String[] args = {idPlayer};
//        Cursor cursor = getReadableDatabase().query("player", null, where,
//                args, null, null, null);
//        while (cursor != null && cursor.moveToNext()) {
////            String id = cursor.getString(cursor.getColumnIndex("id"));
////            String name = cursor.getString(cursor.getColumnIndex("name"));
////            int age = cursor.getInt(cursor.getColumnIndex("age"));
////            float height = cursor.getInt(cursor.getColumnIndex("height"));
////            float weight = cursor.getInt(cursor.getColumnIndex("weight"));
////            String phone = cursor.getString(cursor.getColumnIndex("phone"));
////            String email = cursor.getString(cursor.getColumnIndex("email"));
////            String urlAvatar=cursor.getString(cursor.getColumnIndex("urlavatar"));
////            String position=cursor.getString(cursor.getColumnIndex("position"));
////            Player player = new Player(id,name,age,height,weight,phone,email,urlAvatar,position);
////            return  player;
//        }
//        return null;
//    }
//
//    public Player getPlayerbyPhone(String phonePlayer){
//        String where = "phone= ?";
//        String[] args = {phonePlayer};
//        Cursor cursor = getReadableDatabase().query("player", null, where,
//                args, null, null, null);
//        while (cursor != null && cursor.moveToNext()) {
////            String id = cursor.getString(cursor.getColumnIndex("id"));
////            String name = cursor.getString(cursor.getColumnIndex("name"));
////            int age = cursor.getInt(cursor.getColumnIndex("age"));
////            float height = cursor.getInt(cursor.getColumnIndex("height"));
////            float weight = cursor.getInt(cursor.getColumnIndex("weight"));
////            String email = cursor.getString(cursor.getColumnIndex("email"));
////            String urlAvatar=cursor.getString(cursor.getColumnIndex("urlavatar"));
////            String phone = cursor.getString(cursor.getColumnIndex("phone"));
////            String position=cursor.getString(cursor.getColumnIndex("position"));
////            Player player = new Player(id,name,age,height,weight,phone,email,urlAvatar,position);
//            return  player;
//        }
//        return null;
//    }
//
//    public Player getPlayerbyEmail(String emailPlayer){
//        String where = "email= ?";
//        String[] args = {emailPlayer};
//        Cursor cursor = getReadableDatabase().query("player", null, where,
//                args, null, null, null);
//        while (cursor != null && cursor.moveToNext()) {
////            String id = cursor.getString(cursor.getColumnIndex("id"));
////            String name = cursor.getString(cursor.getColumnIndex("name"));
////            int age = cursor.getInt(cursor.getColumnIndex("age"));
////            float height = cursor.getInt(cursor.getColumnIndex("height"));
////            float weight = cursor.getInt(cursor.getColumnIndex("weight"));
////            String phone = cursor.getString(cursor.getColumnIndex("phone"));
////            String email = cursor.getString(cursor.getColumnIndex("email"));
////            String urlAvatar=cursor.getString(cursor.getColumnIndex("urlavatar"));
////            String position=cursor.getString(cursor.getColumnIndex("position"));
////            Player player = new Player(id,name,age,height,weight,phone,email,urlAvatar,position);
////            return  player;
//        }
//        return null;
//    }
//
//    public long resetPlayer() {
//        return getWritableDatabase().delete("player", null, null);
//    }

}
