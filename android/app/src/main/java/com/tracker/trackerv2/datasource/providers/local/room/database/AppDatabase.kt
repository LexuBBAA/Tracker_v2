package com.tracker.trackerv2.datasource.providers.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tracker.trackerv2.datasource.providers.local.IProjectsProvider
import com.tracker.trackerv2.datasource.providers.local.ISprintsProvider
import com.tracker.trackerv2.datasource.providers.local.ITasksProvider
import com.tracker.trackerv2.datasource.providers.local.ITeamsProvider
import com.tracker.trackerv2.datasource.providers.local.ITokensProvider
import com.tracker.trackerv2.datasource.providers.local.IUserTeamProvider
import com.tracker.trackerv2.datasource.providers.local.IUsersProvider
import com.tracker.trackerv2.datasource.providers.local.IWorklogsProvider
import com.tracker.trackerv2.datasource.providers.local.ProjectsProvider
import com.tracker.trackerv2.datasource.providers.local.SprintsProvider
import com.tracker.trackerv2.datasource.providers.local.TasksProvider
import com.tracker.trackerv2.datasource.providers.local.TeamsProvider
import com.tracker.trackerv2.datasource.providers.local.TokensProvider
import com.tracker.trackerv2.datasource.providers.local.UserTeamProvider
import com.tracker.trackerv2.datasource.providers.local.UsersProvider
import com.tracker.trackerv2.datasource.providers.local.WorklogsProvider
import com.tracker.trackerv2.datasource.providers.local.room.dao.ProjectsDao
import com.tracker.trackerv2.datasource.providers.local.room.dao.SprintsDao
import com.tracker.trackerv2.datasource.providers.local.room.dao.TasksDao
import com.tracker.trackerv2.datasource.providers.local.room.dao.TeamsDao
import com.tracker.trackerv2.datasource.providers.local.room.dao.TokensDao
import com.tracker.trackerv2.datasource.providers.local.room.dao.UserTeamDao
import com.tracker.trackerv2.datasource.providers.local.room.dao.UsersDao
import com.tracker.trackerv2.datasource.providers.local.room.dao.WorklogsDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.ProjectEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.TokenEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.WorklogEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.utils.UserTeamEntity

@Database(
    entities = [
        TokenEntity::class,
        UserEntity::class,
        TeamEntity::class,
        UserTeamEntity::class,
        ProjectEntity::class,
        SprintEntity::class,
        TaskEntity::class,
        WorklogEntity::class
    ],
    version = 1
)
@TypeConverters(com.tracker.trackerv2.datasource.providers.local.room.database.TypeConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun tokensDao(): TokensDao
    abstract fun usersDao(): UsersDao
    abstract fun teamsDao(): TeamsDao
    abstract fun userTeamDao(): UserTeamDao
    abstract fun projectsDao(): ProjectsDao
    abstract fun sprintsDao(): SprintsDao
    abstract fun tasksDao(): TasksDao
    abstract fun worklogsDao(): WorklogsDao

    fun getTokensProvider(): ITokensProvider = TokensProvider(tokensDao())
    fun getUsersProvider(): IUsersProvider = UsersProvider(usersDao())
    fun getTeamsProvider(): ITeamsProvider = TeamsProvider(teamsDao())
    fun getUserTeamProvider(): IUserTeamProvider = UserTeamProvider(userTeamDao())
    fun getProjectsProvider(): IProjectsProvider = ProjectsProvider(projectsDao())
    fun getSprintsProvider(): ISprintsProvider = SprintsProvider(sprintsDao())
    fun getTasksProvider(): ITasksProvider = TasksProvider(tasksDao())
    fun getWorklogsProvider(): IWorklogsProvider = WorklogsProvider(worklogsDao())

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tracker_v2_local_database"
                ).addCallback(object: Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        DbPrePopulator(getDatabase(context)).execute()
                    }
                }).build()
                return INSTANCE!!
            }
        }
    }
}