package self.learning.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String word;

    //required
    public Word(@NonNull String word) {
        this.word = word;
    }

    //required
    @NonNull
    public String getWord() {
        return word;
    }
}
