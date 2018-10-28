package self.learning.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import self.learning.dao.WordDao;
import self.learning.entities.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    //provide abstract methods for each DAO that will be used by this DB
    public abstract WordDao getWordDao();

    // ensure this class is singleton to prevent multiple
    // expensive instances of the db being opened at the same time
    private static volatile WordRoomDatabase dbInstance;

    public static WordRoomDatabase getDataBase(final Context context) {
        if(dbInstance == null) {
            synchronized (WordRoomDatabase.class) {
                dbInstance = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database").build();
            }
        }
        return dbInstance;
    }
}
