package com.airfling.ant.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class Dos2UnixPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.tasks.create("dos2Unix", Dos2Unix)
    }
}