package com.tracker.trackerv2.datasource.providers.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tracker.trackerv2.datasource.providers.local.*
import com.tracker.trackerv2.datasource.providers.room.dao.*
import com.tracker.trackerv2.datasource.providers.room.entity.*

@Database(
    entities = [
        TokenEntity::class,
        UserEntity::class,
        TeamEntity::class,
        ProjectEntity::class,
        SprintEntity::class,
        TaskEntity::class,
        WorklogEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun tokensDao(): TokensDao
    abstract fun usersDao(): UsersDao
    abstract fun teamsDao(): TeamsDao
    abstract fun projectsDao(): ProjectsDao
    abstract fun sprintsDao(): SprintsDao
    abstract fun tasksDao(): TasksDao
    abstract fun worklogsDao(): WorklogsDao

    fun getTokensProvider(): ITokensProvider = TokensProvider(tokensDao())
    fun getUsersProvider(): IUsersProvider = UsersProvider(usersDao())
    fun getTeamsProvider(): ITeamsProvider = TeamsProvider(teamsDao())
    fun getProjectsProvider(): IProjectsProvider = ProjectsProvider(projectsDao())
    fun getSprintsProvider(): ISprintsProvider = SprintsProvider(sprintsDao())
    fun getTasksProvider(): ITasksProvider = TasksProvider(tasksDao())
    fun getWorklogsProvider(): IWorklogsProvider = WorklogsProvider(worklogsDao())

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tracker_v2_local_database"
                ).build()
                return INSTANCE!!
            }
        }
    }
}