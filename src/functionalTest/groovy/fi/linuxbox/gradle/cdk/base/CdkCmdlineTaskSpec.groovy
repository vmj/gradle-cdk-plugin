package fi.linuxbox.gradle.cdk.base

import fi.linuxbox.gradle.GradleSpecification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class CdkCmdlineTaskSpec extends GradleSpecification {

    void 'it should detect the CDK version'() {
        given:
        buildScript << """
        // tag::base-plugin-example[]
        plugins {
            id 'fi.linuxbox.cdk.base' version '<version>'
            id 'fi.linuxbox.npm-global' version '0.2.0'
        }
        
        npmGlobal {
            install 'cdk', version: '2.41.0'
        }
        
        import fi.linuxbox.gradle.cdk.base.CdkCmdlineTask
        
        tasks.register('cdkVersion', CdkCmdlineTask) {
            description = 'Shows the version of the CDK CLI'
            args = ['--version']
        }
        // end::base-plugin-example[]
        """

        when:
        final build = gradle(latestGradleVersion, '--debug', 'cdkVersion').build()

        then:
        build.task(':cdkVersion').outcome == SUCCESS
        build.output.contains "Detected CDK version '2.41.0'"
    }

}
