pipeline{
    
    agent any
    
    tools{
        maven 'maven3'
    }
    
    stages{
        stage('build kafka-producer'){
            steps {
                dir ('kafka-producer'){
                     sh 'mvn clean package'
                }
            }
        }
        
        stage('build kafka-consumer'){
            steps {
                dir ('kafka-consumer'){
                    sh 'mvn clean package'
                }
            }
        }
        
        stage('build data-generator'){
            steps{
                sh 'python3 dataGenerator.py'
            }
        }
    }
}