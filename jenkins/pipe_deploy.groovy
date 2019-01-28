node ('master') {
    def BUILD_JOB = 'hello_app_py_pipe'
    ws("$JENKINS_HOME/workspace/${BUILD_JOB}") {
	def buildNum = Jenkins.instance.getItem("${BUILD_JOB}").lastSuccessfulBuild.number
    sh 'pwd'
    sh "kubectl set image -f kubernetes/deployment_hello_py_k8s.yaml hello-app-py-ctr=bzumby/hello_app_py:v1.${buildNum}"
}
}