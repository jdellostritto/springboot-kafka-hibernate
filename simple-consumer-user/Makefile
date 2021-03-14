.PHONY: deploy all build test clean package tags image push run stop down checkout-deploy

IMAGE ?= acme.io/simple-consumer-user
BUILD ?= latest

DOCKER_COMPOSE ?= docker-compose
RUN_CONFIG ?= -f docker-compose.yml
COMPILE_CONFIG ?= -f docker-compose.compile.yml
BUILD_CONFIG ?= -f docker-compose.build.yml
COMPOSE ?= $(DOCKER_COMPOSE) $(RUN_CONFIG)
MAVEN ?= $(COMPOSE) $(COMPILE_CONFIG) run --rm -w /usr/src/app app mvn

all: package
build:
	$(MAVEN) compile
	$(COMPOSE) stop
	$(COMPOSE) rm -f

clean:
	$(MAVEN) clean
	$(COMPOSE) stop
	$(COMPOSE) rm -f

package:
	$(MAVEN) package -e -Dmaven.test.skip=true
	$(COMPOSE) stop
	$(COMPOSE) rm -f

test:
	$(MAVEN) test
	$(COMPOSE) stop
	$(COMPOSE) rm


image: package
	docker build -t $(IMAGE):$(BUILD) .

delete:
	docker image rm $(IMAGE)

# Kubernetes via `Docker-Desktop` and `Minikube` Only supports Linux Containers.
# These `make` targets will only run with linux container builds.
# The Linux image must be available in the Minikube or Docker-Desktop Respository.
# Before running these check for the image by running `docker images` command.
# To switch to Minikube use the following command in powershell to switch the context
# . to use minikube. To return to the docker-desktop kubernetes context close and reopen
# . powershell and/or bash.

# *NIX/Bash.
# . Run command below first for minikube and make sure the image is available.
# eval $(minikube -p minikube docker-env)
kube-apply-bash:
	envsubst < ./kubernetes/kubernetes.tmpl > ./kubernetes/$(PROJECT).yml
	kubectl apply -f ./kubernetes/$(PROJECT).yml
	
kube-delete-bash:
	kubectl delete -f ./kubernetes/$(PROJECT).yml
	rm ./kubernetes/$(PROJECT).yml

# Windows powershell run.
# Need to run the .\kubernetes\envar.ps1 script to set the environment variables.
# . Run command below first for minikube and make sure the image is available.
# minikube docker-env | Invoke-Expression
kube-apply-ps:
	envsubst < ./kubernetes/kubernetes.tmpl > ./kubernetes/$(PROJECT).yml
	kubectl apply -f ./kubernetes/$(PROJECT).yml
	
kube-delete-ps:
	kubectl delete -f ./kubernetes/$(PROJECT).yml
	del .\kubernetes\$(PROJECT).yml


# Prune
prune:
	docker system prune -f
	docker volume prune -f
	docker network prune -f
