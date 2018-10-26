#!/usr/bin/env groovy

def call(String buildResult, String msg) {

  // def colorCode = '#FF0000'

  // if ( buildResult == "STARTED" ) {
  //   colorCode = 'good'
  //   if (env.gitlabUserName){
  //     msg = "$msg (By ${env.gitlabUserName})"
  //   }
  // }
  // else if( buildResult == "PAUSED" ) { 
  //   colorCode = 'warning'
  // }
  // else if ( buildResult == null || buildResult == 'SUCCESS' ) {
  //   colorCode = 'good'
  // }
  // else if( buildResult == "FAILURE" ) { 
  //   colorCode = 'danger'
  // }
  // else if( buildResult == "UNSTABLE" ) { 
  //   colorCode = 'warning'
  // }
  // else if( buildResult == "ABORTED" ) { 
  //   colorCode = 'danger'
  // }
  // else {
  //   colorCode = 'danger'
  // }

  dir('../devops') {
    git(
      changelog: false, 
      credentialsId: 'ssh_bitbucket', 
      poll: false, 
      url: 'git@bitbucket.org:geniousphp/devops.git'
    )

    dir('./ansible') {
      ansiColor('xterm') {
        ansiblePlaybook(
          inventoryPath: 'inventories/null.yml',
          playbook: 'common/helpers/notif_telegram.yml', 
          extras: "-e notif_msg=${msq} -e deploy_service=${service} -e notif_msg_format='plain'",
          vaultCredentialsId: 'ansible_vault_password',
          colorized: true)
      }
    }
  }
}