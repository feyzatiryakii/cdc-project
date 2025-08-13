pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/kullaniciadi/cdc-project.git', branch: 'main'
            }
        }

        stage('build Kafka-producer') {
            steps {
                dir('kafka-producer') {
                    script {
                        docker.build("cdc/kafka-producer")
                    }
                }
            }
        }

        stage('build Kafka-consumer') {
            steps {
                dir('kafka-consumer') {
                    script {
                        docker.build("cdc/kafka-consumer")
                    }
                }
            }
        }

        stage('run containers') {
            steps {
                script {
                    sh 'docker run -d --rm --name producer cdc/kafka-producer'
                    sh 'docker run -d --rm --name consumer cdc/kafka-consumer'
                }
            }
        }
    }
}
