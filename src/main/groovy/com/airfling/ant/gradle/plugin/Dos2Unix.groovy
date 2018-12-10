package com.airfling.ant.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject
import java.nio.file.Files
import java.nio.file.Paths

/**
 * @author dzg
 * @since 2018/12/10
 */
class Dos2Unix extends DefaultTask {

    @Inject
    Dos2Unix() {
    }
    @Optional
    String sourceDir = "."

    @Optional
    String targetDir = "build" + File.separator + "dos2unix"

    @Optional
    String[] suffixExclude = []

    @Optional
    String[] suffixInclude = []

    @TaskAction
    def make() {
        recursionFiles(sourceDir, targetDir)
    }

    def recursionFiles(String source, String target) {
        File file = new File(source)
        File targetFile = new File(target)
        if (!targetFile.exists()){
            targetFile.mkdirs()
        }
        file.eachFile {
            def tmpFile = it
            suffixExclude.each {
                if (tmpFile.name.endsWith(it)) {
                    return
                }
            }
            suffixInclude.each {
                if (tmpFile.name.endsWith(it)) {
                    convert(tmpFile.absolutePath, target + File.separator + tmpFile.name)
                }
            }
        }
        file.eachDir {
            recursionFiles(source + File.separator + it.name, target + File.separator + it.name)
        }

    }

    def convert(String source, String target) {
        File srcFile = new File(source)
        File targetFile = new File(target)
        StringBuilder content = new StringBuilder()
        srcFile.eachLine { content.append(it).append("\n") }
        targetFile.write(content.toString())
    }

}

