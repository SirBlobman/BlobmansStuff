pipeline {
    agent any

    options {
        githubProjectProperty(projectUrlStr: "https://github.com/SirBlobman/BlobmansStuff")
    }

    triggers {
        githubPush()
    }

    tools {
        jdk "JDK 17"
    }

    stages {
        stage("Gradle: Build") {
            steps {
                withGradle {
                    sh("./gradlew clean build --no-daemon")
                }
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'build/libs/blobmanstuff-*.jar', fingerprint: true
        }
    }
}
