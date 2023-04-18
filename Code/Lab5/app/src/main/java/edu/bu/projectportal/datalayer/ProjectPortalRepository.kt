package edu.bu.projectportal.datalayer

import androidx.lifecycle.LiveData
import edu.bu.projectportal.datalayer.Project
import edu.bu.projectportal.datalayer.ProjectDao
import java.util.concurrent.Executors

class ProjectPortalRepository (
    private val projectDao: ProjectDao,
) {
    val executor =  Executors.newSingleThreadExecutor()

    suspend fun addProject(project: Project){
            projectDao.addProject(project)
    }


    suspend fun delProject(project: Project) {

            projectDao.delProject(project)

    }

    suspend fun editProject(project: Project) {
            projectDao.editProject(project)
    }


    fun getAllProjects(): LiveData<List<Project>> {
        return projectDao.getAllProjects()
    }

    fun searchProject(projId: Long): LiveData<Project> {
        return projectDao.searchProjectById(projId)
    }

    fun searchProjectsbyTitle(projTitle:String): LiveData<List<Project>> {
        return projectDao.searchProjectsByTitle(projTitle)
    }

    fun count(): LiveData<Int> {
        return projectDao.count()
    }
}
