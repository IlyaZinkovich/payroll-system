pipeline {
    agent any
    tools { 
        maven 'M3'
    }
    environment {
        committer = ""
    }
    stages {
        stage('Preparation') { 
            steps {
                git 'https://github.com/IlyaZinkovich/payroll-system.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn org.jacoco:jacoco-maven-plugin:prepare-agent -Dmaven.test.failure.ignore=true install'
            }
        }
        stage('Code Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=ilyazinkovich-github -Dsonar.login=69d0b4c520dbc64a13ab384691f9302996a18be6'
            }
        }
    }
    post {
        success {
            archive 'target/*.jar'
            emailext body: 'Congratulations', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Build success'
        }
    }
}
