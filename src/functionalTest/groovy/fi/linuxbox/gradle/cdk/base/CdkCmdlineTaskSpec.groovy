package fi.linuxbox.gradle.cdk.base

import fi.linuxbox.gradle.GradleSpecification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class CdkCmdlineTaskSpec extends GradleSpecification {

    void 'it should detect the CDK version'() {
        given:
        buildScript << """
        plugins {
            id 'fi.linuxbox.npm-global' version '0.2.0'
        }
        
        npmGlobal {
            install 'cdk', version: '2.41.0'
        }
        
        import fi.linuxbox.gradle.cdk.base.CdkCmdlineTask
        
        tasks.register('cdkVersion', CdkCmdlineTask) {
            args = ['--version']
        }
        """

        when:
        final build = gradle(latestGradleVersion, '--debug', 'cdkVersion').build()

        then:
        build.task(':cdkVersion').outcome == SUCCESS
        build.output.contains "Detected CDK version '2.41.0'"
    }

}
