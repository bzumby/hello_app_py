node {

		stage ('GIT Fetch & PreMerge') {
			checkout ([
			$class: 'GitSCM',
				url: 'https://github.com/bzumby/hello_app_py.git',
				branches: [[name: 'master']],
				extensions: [
					[$class: 'PruneStaleBranch'],
					[$class: 'CleanCheckout'],
					[$class: 'PreBuildMerge',
						options: [
							fastForwardMode: 'FF_ONLY',
							mergeRemote: 'origin',
							mergeTarget: 'master'
						]	
					]
				]
			])
		}


		stage ('Docker Build') {
			def image = docker.build("bzumby/hello_app_py:${env.BUILD_ID}")
				withDockerRegistry([credentialsId: 'docker_hub_bz', url: 'https://index.docker.io/v1/']) {
            	image.push() }
		}
	}
