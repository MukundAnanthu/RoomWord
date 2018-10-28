package self.learning.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import self.learning.dao.WordDao;
import self.learning.db.WordRoomDatabase;
import self.learning.entities.Word;

/*
* Not a library, rather a design to
* ensure decoupling of UI and DB
* */
public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> words;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDataBase(application);
        this.wordDao = db.getWordDao();
        words = wordDao.getAllWords();
    }

    public LiveData<List<Word>> getWords() {
        return words;
    }

    public void insertWord(Word word) {
        new InsertWordAsyncTask(wordDao).execute();
    }

    private class InsertWordAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        public InsertWordAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insert(words[0]);
            return null;
        }
    }
}
