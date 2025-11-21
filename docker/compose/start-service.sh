#!/bin/bash

sleep 1

export AWS_PAGER="" 

#display env parameters
env | grep ENV

unset AWS_ACCESS_KEY_ID
unset AWS_SECRET_ACCESS_KEY

#fetch secrets and set it as env parameters
mkdir -p /secret
if [[ -z "${SECRETSMANAMGER_URL}" ]]; then
  aws secretsmanager get-secret-value --secret-id $SECRET_PATH | jq -r .SecretString | jq -r 'to_entries|map("\(.key)=:=\(.value|tostring)")|.[]'  | awk -F=:= -v quote="'" '{print "export "$1"=" quote $2 quote}' > /secret/secret.env
else 
  aws --endpoint-url=$SECRETSMANAMGER secretsmanager get-secret-value --secret-id $SECRET_PATH | jq -r .SecretString | jq -r 'to_entries|map("\(.key)=:=\(.value|tostring)")|.[]'  | awk -F=:= -v quote="'" '{print "export "$1"=" quote $2 quote}' > /secret/secret.env
fi

. /secret/secret.env

case "$1" in
  shopping-cart-app)
    JARNAME=$(ls -lrt shopping-cart-app*.jar | tail -n 1 | awk '{print $NF}')
    java -Xms$JVM_MAX_HEAP -Xmx$JVM_MAX_HEAP -Dspring.profiles.active=$ENV -jar $JARNAME
  ;;
esac
