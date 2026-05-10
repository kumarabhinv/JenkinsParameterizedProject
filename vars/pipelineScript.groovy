pipeline {
	
	agent any
	parameters {
		string(name: "fileToRun", defaultValue: "/src/test/java/Jenkins/PracticeJenkins", description: "Provide the package plus file name to run, example: /Jenkins/PracticeJenkins")
		string(name: "mavenCommand", defaultValue: "clean test", description: "Provide the command for Maven")
	}
	stages {
		stage("Configuring") {
			steps {
				echo "$fileToRun"
				echo "$mavenCommand"
				script {
					def runnerFile = sh "$fileToRun"
					def runnerCommand = sh "$mavenCommand"
				}
			}
		}
		stage("Test") {
			steps {
				script {
					sh "mvn"
					sh "$runnerCommand"
				}
			}
		}
	}
	
	post {
		success {
			echo "Test is successful."
		}
		failure {
			echo "Test failed."
		}
		always {
			echo "Pipeline execution complete."
		}
	}
	
}
