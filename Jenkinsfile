pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/feyzatiryakii/cdc-project.git', branch: 'main'
            }
        }

        stage('build Kafka-producer') {
            steps {
                dir('kafka-producer') {
                    script {
                        sh 'docker build -t cdc-project/kafka-producer .'
                    }
                }
            }
        }

        stage('build Kafka-consumer') {
            steps {
                dir('kafka-consumer') {
                    script {
                        sh 'docker build -t cdc-project/kafka-consumer .'
                    }
                }
            }
        }

        stage('run containers') {
            steps {
                script {
                    sh 'docker run -d --rm --name producer cdc-project/kafka-producer'
                    sh 'docker run -d --rm --name consumer cdc-project/kafka-consumer'
                }
            }
        }
    }
}
