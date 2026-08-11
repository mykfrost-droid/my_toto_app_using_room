package co.ke.eurochef.android.mytodos.data;

import android.content.Context;

import androidx.room.Room;

public class DatabaseProvider {
    private static AppDatabase database;

    public static AppDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "todo_database").build();
        }
        return database;
    }
}
