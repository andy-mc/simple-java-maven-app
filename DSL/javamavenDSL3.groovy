job('Java Maven App DSL 3') {
    description('Java Maven App con DSL para el curso de Jenkins cambio super bien')
    scm {
        git('https://github.com/andy-mc/simple-java-maven-app.git', 'master') { node ->
            node / gitConfigName('andy-mc')
            node / gitConfigEmail('quemasandy@gmail.com')
        }
    }
    triggers {
    	githubPush()
    }    
    steps {
        maven {
          mavenInstallation('mavenjenkins')
          goals('-B -DskipTests clean package')
        }
        maven {
          mavenInstallation('mavenjenkins')
          goals('test')
        }
        shell('''
          echo "Entrega: Desplegando la aplicación" 
          java -jar "/var/jenkins_home/workspace/Java Maven App DSL 3/target/my-app-1.0-SNAPSHOT.jar"
        ''')  
    }
    publishers {
        archiveArtifacts('target/*.jar')
        archiveJunit('target/surefire-reports/*.xml')
	      slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
       }
    }
}

job('Job test Hola Mundo') {
	description('Aplicacion Hola Mundo de Prueba yaaa')
	scm {
		git('https://github.com/andy-mc/simple-java-maven-app.git', 'master') { node ->
		    node / gitConfigName('andy-mc')
		    node / gitConfigEmail('quemasandy@gmail.com')
		}
	}
	triggers {
    		githubPush()
    	}    
	steps {
		shell('''
			echo "Hola Mundo kkkk funciono 22222 333 hay que actualizar el seed!!!!"
		''')
	}
}
