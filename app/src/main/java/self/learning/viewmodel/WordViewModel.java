package self.learning.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import self.learning.entities.Word;
import self.learning.repo.WordRepository;

/*
* Acts as interface between UI and Repository.
* Survives lifecycle and device configuration changes
* */
public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private LiveData<List<Word>> words;


    /*
    * Caution: Never pass context into viewmodel instances
    * View model is not replacement for onSaveInstanceState(), it does not
    * survive process shutdown
    * */
    public WordViewModel(Application application) {
        super(application);
        WordRepository wordRepository = new WordRepository(application);
        this.words = wordRepository.getWords();
    }

    public LiveData<List<Word>> getWords() {
        return words;
    }

    public void insertWord(Word word) {
        wordRepository.insertWord(word);
    }
}
