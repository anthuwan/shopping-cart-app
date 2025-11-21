ARG AWSACTID=
FROM --platform=linux/amd64 ${AWSACTID}.dkr.ecr.eu-west-1.amazonaws.com/n1/java19:awscli

ARG AWS_REGION=eu-west-1
ENV AWS_REGION=${AWS_REGION}

COPY start-service.sh target/ /home/
RUN chmod +x /home/start-service.sh

WORKDIR /home/

