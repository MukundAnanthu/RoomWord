package self.learning.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import self.learning.entities.Word;

@Dao
public interface WordDao {


    /*
    * Can provide Conflict strategy when inserting into DB.
    * Use @Insert(onConflict = OnConflictStrategy.REPLACE)
    * */
    @Insert
    void insert(Word word);

    @Query("DELETE FROM WORD_TABLE")
    void deleteAll();

    @Query("SELECT * FROM WORD_TABLE ORDER BY word ASC")
    List<Word> getAllWords();
}
