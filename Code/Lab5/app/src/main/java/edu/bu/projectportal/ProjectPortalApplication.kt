package edu.bu.projectportal

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.bu.projectportal.datalayer.Project
import edu.bu.projectportal.datalayer.ProjectPortalDatabase
import edu.bu.projectportal.datalayer.ProjectPortalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
This class defines an application class
Define the application name as ProjectPortalApplication
in the manifest file under the application tag.

 */
class ProjectPortalApplication: Application() {
    lateinit var projectportalDatabase: ProjectPortalDatabase
    lateinit var projectPortalRepository: ProjectPortalRepository
    override fun onCreate() {
        super.onCreate()
        projectportalDatabase =
            Room.databaseBuilder(
                applicationContext, ProjectPortalDatabase::class.java,
                "projectportal-db"
            )
                // add a callback to modify onCreate() to
                // add some initial projects.
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        addInitProjects()
                    }
                })
                .build()

        projectPortalRepository =
            ProjectPortalRepository(projectportalDatabase.projectDao())

    }


    // this is only used to add some initial projects
    // when the database is first created.
    // this is done through
    fun addInitProjects() {

        CoroutineScope(Dispatchers.IO).launch {

            projectPortalRepository.addProject(
                Project(
                    0,
                    "Weather Forecast",
                    "Weather Forcast is an app that report your weather",
                    "William Kathi",
                    "www.final.com",
                    true,
                    "weather report",
                    "good for outdoor"
                ),
            )
            projectPortalRepository.addProject(
                Project(
                    1,
                    "Connect Me",
                    "Connect Me is an app that connect your agent",
                    "Goth, Kenth",
                    "www.draft.com",
                    false,
                    "Contact",
                    "call me"
                ),
            )
            projectPortalRepository.addProject(
                Project(
                    2,
                    "What to Eat",
                    "What to Eat is an app that rate restaurants",
                    "Tash Goodman",
                    "www.ffod.com",
                    true,
                    "restaurant",
                    "Bay area"
                ),
            )
        }
    }
}
