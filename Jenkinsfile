pipeline {
    agnet any
    tools {
       Maven 'Maven 3.6.3'
       jdk 'JDK 11'
}
Stages {
    stage('Checkout') {
            steps {
                git branch : 'main', url : 'https://github.com/barathict/MyTestingProjectRepository'
            }
        }

    stage('Build') {
        steps {
            sh 'mvn clean install'
        }
    }
    stage('Test') {
        steps {
            sh 'mvn test'
        }
    }
   // stage('Deploy') {
   //     steps {
    //        sh 'mvn deploy'
     //   }
   // }


}