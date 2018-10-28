package self.learning.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
                dbInstance = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database")
                        .addCallback(sRoomDatabaseCallback)
                        .build();
            }
        }
        return dbInstance;
    }

    // delete all entries, add 2 words in DB on each app start
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(dbInstance).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.getWordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }

}
