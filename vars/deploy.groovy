def call(String env, String serviceName) {
  sh "ansible-playbook deployment.pb.yml -e env_deployment=${env} -e serviceName=${serviceName}"
}