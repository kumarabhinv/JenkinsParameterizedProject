def call() {

	pipeline {
	
	agent any
	
	tools {
		maven 'Maven';
	}
	
	parameters {
		string(name: "fileToRun", defaultValue: "Jenkins.PracticeJenkins.*", description: "Provide the package plus file name to run, example: Jenkins.PracticeJenkins.Test")
		string(name: "mavenCommand", defaultValue: "clean test", description: "Provide the command for Maven")
	}
	
	stages {
		stage("Configuring") {
			steps {
				echo "$fileToRun"
				echo "$mavenCommand"
			}
		}
		stage("Test") {
			steps {
				script {
					echo "Running goals -> mvn $mavenCommand -Dtest=$fileToRun"
					sh "mvn $mavenCommand -Dtest=$fileToRun"
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
}
