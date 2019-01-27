node {
		stage ('Git Clone Source') {
			git url: 'https://github.com/bzumby/hello_app_py.git',
				branch: "master"
	    }

		stage ('Just Check') {
			withEnv(["PATH+DOCKER=/usr/local/bin"]) {
				sh 'echo $PATH'
				sh 'whoami'
				sh 'pwd'
				sh 'which docker'
			}
		}

		stage ('Docker Build') {
			def image = docker.build("bzumby/hello_app_py:${env.BUILD_ID}")
				withDockerRegistry([credentialsId: 'docker_hub_bz', url: 'https://index.docker.io']) {
            	image.push() }
		}
	}
