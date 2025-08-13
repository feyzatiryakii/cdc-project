pipeline{
    
    agent any
    
    tools{
        maven 'maven3'
    }
    
    stages{
        stage('build kafka-producer'){
            steps {
                dir ('kafka-producer'){
                     sh 'mvn clean package -DskipTests'
                }
            }
        }
        
        stage('build kafka-consumer'){
            steps {
                dir ('kafka-consumer'){
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        
        stage('build data-generator') {
            agent {
                docker {
                    image 'python:3.11'
                }
            }
            steps {
                // python script çalışma dizini doğru olmalı
                dir('/workspace') {
                    sh 'python3 dataGenerator.py'
                }
            }
        }
    }
}