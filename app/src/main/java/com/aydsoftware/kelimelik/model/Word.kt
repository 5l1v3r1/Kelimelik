package com.aydsoftware.kelimelik.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Entity(tableName = "words")
@Parcelize
data class Word(
    @PrimaryKey(autoGenerate = true)
    var uid: Int,

    @ColumnInfo(name = "article")
    @Expose
    var article: String,

    @ColumnInfo(name = "german")
    @Expose
    var german: String,

    @ColumnInfo(name = "turkish")
    @Expose
    var turkish: String,

    @ColumnInfo(name = "mistake")
    var mistake: Boolean
): Parcelable