package com.truxall.domain.interactor.browse

import com.truxall.domain.executor.PostExecutionThread
import com.truxall.domain.interactor.ObservableUseCase
import com.truxall.domain.model.Project
import com.truxall.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetProjects @Inject constructor (private val projectsRepository: ProjectsRepository,
                                       postExecutionThread: PostExecutionThread): ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}