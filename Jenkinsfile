node {

    currentBuild.result = "SUCCESS"

    try {

        stage('Checkout'){
            checkout scm
        }

        stage('Build Maven'){
             withMaven( maven: 'maven-3') {
                sh "mvn -B -DskipTests clean package"
             }
        }

        stage('Notification'){
            // send to email
            emailext (
                subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                to: "${params.EMAIL_DEV}",
                body: """<p>SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
            <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
        )
        }

    }
    catch (err) {

        currentBuild.result = "FAILURE"

        emailext (
            subject: "FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            to: "${params.EMAIL_DEV}",
            body: """<p>FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
        recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )

        throw err
    }

}