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
	    steps{
    		docker.image('python:3.11').inside {
        		sh 'python3 dataGenerator.py'
    		}
	    }
	}
    }
}