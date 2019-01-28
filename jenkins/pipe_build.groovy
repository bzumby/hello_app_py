node ('master'){

		stage ('GIT Fetch & PreMerge') {
			checkout ([
			$class: 'GitSCM',
				extensions: [
					[$class: 'PruneStaleBranch'],
					[$class: 'CleanCheckout'],
					[$class: 'PreBuildMerge',
						options: [
							fastForwardMode: 'FF_ONLY',
							mergeRemote: 'hello_remote',
							mergeTarget: 'master'
						]	
					]
				],
				userRemoteConfigs: [
				    [name: 'hello_remote',
					 url: 'https://github.com/bzumby/hello_app_py.git'
					]
				]
			])
		}

		stage ('Docker Build') {
			def image = docker.build("bzumby/hello_app_py:${env.BUILD_ID}")
				withDockerRegistry([credentialsId: 'docker_hub_bz', url: 'https://index.docker.io/v1/']) {
            	image.push("v1.${env.BUILD_ID}")
            	image.push('latest') }
		}
	}
