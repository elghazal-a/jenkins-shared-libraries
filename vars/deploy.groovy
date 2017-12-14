def call(String env, String serviceName) {
  sh "ansible-playbook deployment.pb.yml -i inventories/deployment/inventory -e env_deployment=${env} -e serviceName=${serviceName}"
}