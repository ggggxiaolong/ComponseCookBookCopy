package com.chuntian.data.db

import android.content.Context
import androidx.room.*

@Entity
data class Theme(
    @PrimaryKey val id: Int = 1,
    @ColumnInfo(name = "is_dark") val isDark: Int = 0,
    @ColumnInfo(name = "pallet") val pallet: Int = 0,
){
    @Ignore
    constructor():this(0)
}

@Dao
interface ThemeDao {
    @Query("SELECT * FROM theme WHERE id = 1")
    fun getTheme(): Theme?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(theme: Theme)
}

@Database(entities = [Theme::class], version = 1)
abstract class DB: RoomDatabase(){
    abstract fun themeDao(): ThemeDao

    companion object{
        @Volatile
        private var INSTANCE: DB? = null

        fun init(context: Context){
            INSTANCE = buildDatabase(context)
        }

        fun instance() = INSTANCE!!

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DB::class.java, "compose.db"
            ).build()
    }
}