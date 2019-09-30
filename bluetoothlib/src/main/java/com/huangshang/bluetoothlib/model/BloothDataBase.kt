package com.huangshang.bluetoothlib.model

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.huangshang.bluetoothlib.inteface.UserInfoDao


@Database(entities =[UserInfo::class], version = 1)
@TypeConverters(DateConverter::class)
 abstract class BloothDataBase(startVersion: Int) :RoomDatabase() {
companion object{
    var database:BloothDataBase?=null
    fun getInteface(context: Context):BloothDataBase?{
        if(database==null){
            synchronized(BloothDataBase::class){
                if(database==null){
                    database= Room.databaseBuilder(context.applicationContext,BloothDataBase::class.java,"bluetooth_database")
                        .addCallback(object : RoomDatabase.Callback() {
                            //第一次创建数据库时调用，但是在创建所有表之后调用的
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                            }
                            //当数据库被打开时调用
                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                            }
                        })
                        .allowMainThreadQueries()//允许在主线程执行
//                        .addMigrations(MIGRATION_1_2,MIGRATION_2_3)//数据库迁移时候使用（按照数据库版本顺序迁移）
//                        .fallbackToDestructiveMigration()//迁移数据库失败时候会重新创建不会奔溃
                        .build()
                }
            }
        }
        return database
    }
    //数据库迁移插入字段（当添加的数据类型为 int 时，需要添加默认值 "ALTER TABLE department " + " ADD COLUMN phone_num INTEGER NOT NULL DEFAULT 0"）
    var MIGRATION_1_2 :Migration= object :Migration(1,2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE UserInfo  ADD COLUMN phone_num TEXT")
        }

    }
    //修改字段
    var MIGRATION_2_3 :Migration= object :Migration(2,3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            //创建表
            database.execSQL(
                "CREATE TABLE UserInfo_new (student_id TEXT, student_name TEXT, phone_num INTEGER, PRIMARY KEY(student_id))");
            //复制表
            database.execSQL(
                "INSERT INTO UserInfo_new (student_id, student_name, phone_num) SELECT student_id, student_name, phone_num FROM UserInfo");
            //删除表
            database.execSQL("DROP TABLE UserInfo");
            //修改表名称
            database.execSQL("ALTER TABLE UserInfo_new RENAME TO UserInfo");
        }

    }
    //跳跃式升级数据库（通过重新创建新表）
    var MIGRATION_1_4 :Migration= object :Migration(1,4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            //创建表
            database.execSQL(
                "CREATE TABLE UserInfo_new (student_id TEXT, student_name TEXT, phone_num INTEGER, PRIMARY KEY(student_id))");
            //复制表
            database.execSQL(
                "INSERT INTO UserInfo_new (student_id, student_name, phone_num) SELECT student_id, student_name, phone_num FROM UserInfo");
            //删除表
            database.execSQL("DROP TABLE UserInfo");
            //修改表名称
            database.execSQL("ALTER TABLE UserInfo_new RENAME TO UserInfo");
        }

    }
}



    override fun clearAllTables() {

    }

    abstract fun getUserInfoDao(): UserInfoDao

}