package fi.linuxbox.gradle.cdk.base

import groovy.transform.CompileStatic
import org.gradle.api.Plugin
import org.gradle.api.Project

@CompileStatic
class CdkBasePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        // This plugin doesn't do anything at the moment.
        // Builds will apply this plugin, if they want to use the CdkCmdlineTask,
        // but do not want any of the CDK bootstrap or CDK application stuff.
    }
}
