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

        stage('run data-generator') {
    	    steps {
               sh '''
                   docker run --rm \
                      -v $PWD:/app \
                      -w /app \
                      python:3.11 \
                      python3 dataGenerator.py 5
               '''
            }
         }
    }
}