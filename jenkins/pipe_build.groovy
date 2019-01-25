node {
		stage ('Git Clone Source') {
			git url: 'https://github.com/bzumby/hello_app_py.git',
				branch: "master"
	    }
		stage ('Just Check') {
			sh 'echo $PATH'
			sh 'whoami'
			sh 'pwd'
			sh 'which docker'
		}
		stage ('Docker Build') {
			docker.withTool('local') {
				def customImage = docker.build("hello_app_py:${env.BUILD_ID}")
        }
    	}
		stage ('Just Check 2') {
			sh 'echo $PATH'
			sh 'whoami'
			sh 'pwd'
			sh 'which docker'
		}
}



// pipeline {
// 	agent { label 'docker_slave'
// 	}
// 	stages {
// 		stage('Git Clone Source') {
// 			steps {
// 				git url: 'https://github.com/bzumby/hello_app_py.git',
// 					branch: "master"
// 			}
// 		}
// 		stage('Docker Build') {
// 			steps {
// 				sh 'docker build -t ${repo}/hello_app_py:${BUILD_NUMBER} -t ${repo}/hello_app_py:latest . '				
// 			}
// 		}
// 	}
// }