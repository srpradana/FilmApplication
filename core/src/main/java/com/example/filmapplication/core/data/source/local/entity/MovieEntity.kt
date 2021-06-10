package com.example.filmapplication.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movie_entities")
data class MovieEntity (
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "posterUrl")
    var posterPath: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "vote")
    var vote: Double? = null,

    @ColumnInfo(name = "favCheck")
    var favCheck: Boolean = false
)