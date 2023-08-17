package com.rickyslash.kidcineproval.core.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="movie")
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
    var backdrop: Int,

    @ColumnInfo(name="voteAverage")
    var voteAverage: Double,

    @ColumnInfo(name="overview")
    var overview: String
): Parcelable