#!/usr/bin/env groovy

script {
    // Allows usage of this repo for testing by jenkins shared libra
    String branchName = params.SHARED_PIPELINE_BRANCH ? "@${params.SHARED_PIPELINE_BRANCH}" : "@FT-3701-automation"
    library identifier: "jenkins-pipeline-library${branchName}"
}

librarySimpleQuarkusPipeline(
        repositoryName: 'lf-api-cuke-test',
        team: ['lf'],
        majorVersion: '0'
)