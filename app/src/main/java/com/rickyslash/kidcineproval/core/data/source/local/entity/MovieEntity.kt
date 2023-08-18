package com.rickyslash.kidcineproval.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="movieTable")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    var id: Int,

    @ColumnInfo(name="title")
    var title: String,

    @ColumnInfo(name="releaseDate")
    var date: String,

    @ColumnInfo(name="genre")
    var genre: Int,

    @ColumnInfo(name="backdrop")
    var backdrop: String,

    @ColumnInfo(name="voteAverage")
    var voteAverage: Double,

    @ColumnInfo(name="overview")
    var overview: String,

    @ColumnInfo(name="isFavorite")
    var favorite: Boolean = false
): Parcelable