package fi.linuxbox.gradle.cdk.base

import fi.linuxbox.gradle.npm.global.NpmPackage
import fi.linuxbox.gradle.npm.global.NpmPackageCmdlineTask
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic

import java.nio.file.Path

@CompileStatic
class CdkCmdlineTask extends NpmPackageCmdlineTask {
    protected String cdkVersion

    CdkCmdlineTask() {
        super()
        group = 'CDK tasks'
        description = 'Runs cdk with --args'
    }

    @Override
    protected boolean providesEntrypoint(NpmPackage pkg) {
        pkg.pkg.get() == 'cdk'
    }

    @Override
    protected Path resolveEntrypoint(Path nodeModuleDirectory) {
        detectCdkVersion(nodeModuleDirectory)

        nodeModuleDirectory.resolve('bin').resolve('cdk')
    }

    private void detectCdkVersion(Path nodeModuleDirectory) {
        final packageJson = readPackageJson(nodeModuleDirectory)
        assert packageJson.name == 'cdk'
        cdkVersion = packageJson.version
        logger.debug "Detected CDK version ${cdkVersion.inspect()}"
    }

    private static Map readPackageJson(Path nodeModuleDirectory) {
        final packageJson = nodeModuleDirectory.resolve('package.json').toFile()
        assert packageJson.exists()

        final parser = new JsonSlurper()
        final object = parser.parse(packageJson)
        object as Map
    }
}
