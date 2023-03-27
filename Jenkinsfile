
pipeline {
    agent any
    
    tools {
        // Adicione as ferramentas necessárias para o pipeline
        maven 'local_maven'
    }

    stages {
        stage('Build Backend') {
            // Defina as etapas necessárias para a construção do backend
            steps {
                // Clone o repositório do backend
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Fransualdo-Lopes/osworks-api.git']]])
                // Execute o comando Maven inserindo o caminho do pom.xml para construir o backend 
                sh 'chmod -R 777 osworks-api'
                sh 'mvn -f osworks-api/osworks/pom.xml clean package'
            }
            post {
                success {
                    // Arquive os artefatos do backend
                    archiveArtifacts artifacts: 'target/*.jar'
                }
            }
        }
    }
}
